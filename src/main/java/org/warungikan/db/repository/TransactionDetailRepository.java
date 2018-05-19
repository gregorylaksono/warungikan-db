package org.warungikan.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.warungikan.db.model.Transaction;
import org.warungikan.db.model.TransactionDetail;
import org.warungikan.db.model.User;

@Repository
public interface TransactionDetailRepository extends JpaRepository<TransactionDetail, Long> {
	
	@Query("SELECT t FROM TransactionDetail t WHERE t.transaction = :trx")
	List<TransactionDetail> findTransactionDetailByTransactionId(@Param("trx") Transaction t);

	@Query("SELECT t FROM TransactionDetail t WHERE t.oid in (:ids)")
	List<TransactionDetail> findTransactionDetailByDetailsId(@Param("ids") Long[] ids);

//	@Query("SELECT t FROM TransactionDetail t JOIN t.transaction r WHERE r.agent = :agent AND r.item ")
//	List<TransactionDetail> findTransactionDetailByAgent(@Param("agent") User agent);
}
