package org.warungikan.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.warungikan.db.model.AgentData;
import org.warungikan.db.model.Role;
import org.warungikan.db.model.User;

@Repository
public interface AgentDataRepository extends JpaRepository<AgentData, Long>{

	@Query("SELECT r FROM AgentData r WHERE r.agent = :agent ")
	public AgentData findDataByUser(@Param("agent") User u);

//	@Query("SELECT r FROM Role r WHERE r.name = :name ")
//	public Role findByName(@Param("name") String name);
}
