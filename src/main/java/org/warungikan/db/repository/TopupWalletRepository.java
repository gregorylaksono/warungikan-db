package org.warungikan.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.warungikan.db.model.TopupWalletHistory;

@Repository
public interface TopupWalletRepository extends JpaRepository<TopupWalletHistory, Long> {

}
