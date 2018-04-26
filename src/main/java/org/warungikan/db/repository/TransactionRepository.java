package org.warungikan.db.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.warungikan.db.model.Transaction;
import org.warungikan.db.model.TransactionDetail;
import org.warungikan.db.model.User;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>{

	@Query("SELECT t FROM Transaction t WHERE t.customer = :customer")
	List<Transaction> findTransactionCustomer(@Param("customer") User user);

	@Query("SELECT t FROM Transaction t WHERE t.agent = :agent")
	List<Transaction> findTransactionAgent(@Param("agent") User user);

}
