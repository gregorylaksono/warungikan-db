package org.warungikan.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.warungikan.db.model.ShopItem;
import org.warungikan.db.model.ShopItemStock;
import org.warungikan.db.model.User;

public interface ShopItemStockRepository extends JpaRepository<ShopItemStock, Long> {

	@Query("SELECT c FROM ShopItemStock c WHERE c.agent = :user ")
	List<ShopItemStock> findShopItemStockByAgentId(@Param("user") User s);

//	@Query("SELECT s FROM ShopItemStock s WHERE s.item = :item AND s.agent = :agent ")
	ShopItemStock findStockItemByAgentAndItem(User agent,ShopItem shopItem);

	@Query("SELECT s FROM ShopItemStock s WHERE s.item = :item AND s.agent = :agent AND s.amount >= :amount")
	ShopItemStock findStockItemByAgentAndItemAndCount(@Param("agent") User u, 
													  @Param("item") ShopItem shopItem, 
													  @Param("amount") Integer amount);
}
