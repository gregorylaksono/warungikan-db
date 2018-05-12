package org.warungikan.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.warungikan.db.model.TopupWalletHistory;
import org.warungikan.db.model.Transaction;
import org.warungikan.db.model.User;

@Repository
public interface TopupWalletRepository extends JpaRepository<TopupWalletHistory, Long> {

	
	@Query("SELECT t FROM TopupWalletHistory t where t.user=:user")
	public List<TopupWalletHistory> findTopupsWalletByUser(@Param("user") User user);

	@Query("SELECT t FROM TopupWalletHistory t where t.top_up_id = :topupId")
	public TopupWalletHistory findTopupByTopupId(@Param("topupId") String topupId);
}
