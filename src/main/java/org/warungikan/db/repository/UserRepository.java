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
	
	@Query("SELECT c FROM User c JOIN c.roles r WHERE r.name = 'ROLE_USER' AND c.enable = true")
	public List<User> findAllCustomer();
	
	@Query("SELECT c FROM User c JOIN c.roles r WHERE r.name = 'ROLE_ADMIN' AND c.enable = true ")
	public List<User> findAllAgent();
	
	@Query("SELECT c FROM User c WHERE c.id= :oid AND c.enable = true")
	public User findCustomerById(@Param("oid") Long id);
	
	@Query("SELECT c FROM User c WHERE c.id= :oid AND c.enable = true")
	public User findAgentById(@Param("oid") Long id);
	
	@Query("SELECT c FROM User c WHERE c.email = :email AND c.enable = true")
	public User findUserByUserId(@Param("email") String email);
}
