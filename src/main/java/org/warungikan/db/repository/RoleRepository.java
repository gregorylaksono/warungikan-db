package org.warungikan.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.warungikan.db.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{

	@Query("SELECT r FROM Role r WHERE r.name = :name ")
	public Role findByName(@Param("name") String name);
}
