package org.warungikan.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.warungikan.db.model.User;
@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {
	
	@Query("SELECT c FROM User c JOIN c.roles r WHERE r.name = 'ROLE_USER' ")
	public List<User> findAllCustomer();
	
	@Query("SELECT c FROM User c JOIN c.roles r WHERE r.name = 'ROLE_ADMIN' ")
	public List<User> findAllAgent();
	
	@Query("SELECT c FROM User c WHERE c.id= :oid")
	public User findCustomerById(@Param("oid") Long id);
	
	@Query("SELECT c FROM User c WHERE c.id= :oid")
	public User findAgentById(@Param("oid") Long id);
	
	@Query("SELECT c FROM User c WHERE c.userid = :userid")
	public User findUserByUserId(@Param("userid") String userId);
}
