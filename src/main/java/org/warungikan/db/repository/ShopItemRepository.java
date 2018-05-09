package org.warungikan.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.warungikan.db.model.ShopItem;
import org.warungikan.db.model.ShopItemStock;
import org.warungikan.db.model.User;

public interface ShopItemRepository extends JpaRepository<ShopItem, Long> {

	@Query("SELECT s FROM ShopItemStock s WHERE s.agent = :agent")
	List<ShopItemStock> findStockByAgent(@Param("agent") User agent);

	
}
