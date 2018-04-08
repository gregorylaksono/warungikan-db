package org.warungikan.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.warungikan.db.model.ShopItem;

public interface ShopItemRepository extends JpaRepository<ShopItem, Long> {

}
