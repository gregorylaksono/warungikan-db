package org.warungikan.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.warungikan.db.model.Transaction;
import org.warungikan.db.model.TransactionDetail;
import org.warungikan.db.model.TransactionState;

@Repository
public interface TransactionStateRepository extends JpaRepository<TransactionState, Long> {

	@Query("SELECT t FROM TransactionState t WHERE t.transaction = :transaction")
	List<TransactionState> findTransactionStateByTransaction(@Param("transaction") Transaction t);
	
//	@Query("SELECT t FROM TransactionDetail t WHERE t.transaction = :trx")
//	List<TransactionDetail> findTransactionDetailByTransactionId(@Param("trx") Transaction t);
}
