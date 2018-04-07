package org.warungikan.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.warungikan.db.model.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	public User findByEmail(String email);
}
