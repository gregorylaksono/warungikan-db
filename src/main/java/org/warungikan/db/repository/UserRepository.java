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

	@Query("SELECT c FROM User c WHERE c.enable = true")
	public List<User> findAllUsersEnabled();

	@Query("SELECT s.agent FROM ShopItemStock s JOIN s.agent u WHERE "
			+ "s.item.id in (:items) GROUP BY s.agent.id, u.id HAVING count(s.id) >= :size")
	public List<User> findAgentByShopItem(@Param("items") List<Long> items, @Param("size") long size);

	@Query("SELECT c FROM User c WHERE c.randomConfirmationKey = :random")
	public User findUserByConfirmationKey(@Param("random") String random);

}
