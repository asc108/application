package product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import product.model.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Integer> {

}
