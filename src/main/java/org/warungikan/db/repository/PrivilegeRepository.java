package org.warungikan.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.warungikan.db.model.Privilege;

@Repository
public interface PrivilegeRepository extends JpaRepository<Privilege, Long>{

	
	@Query("SELECT p FROM Privilege p WHERE p.name = :name")
	public Privilege findByName(@Param("name") String name);
}
