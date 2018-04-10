package org.warungikan.db.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.warungikan.db.model.Transaction;
import org.warungikan.db.model.TransactionDetail;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>{

	@Query("SELECT t FROM TransactionDetail t WHERE t.transaction = :trx")
	List<TransactionDetail> findTransactionDetailByTransactionId(@Param("trx") Transaction t);

}
