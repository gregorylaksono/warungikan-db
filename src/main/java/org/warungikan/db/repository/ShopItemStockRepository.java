package org.warungikan.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.warungikan.db.model.ShopItemStock;
import org.warungikan.db.model.User;

public interface ShopItemStockRepository extends JpaRepository<ShopItemStock, Long> {

	@Query("SELECT c FROM ShopItemStock c WHERE c.agent = :user ")
	List<ShopItemStock> findShopItemStockByAgentId(@Param("user") User s);
}
