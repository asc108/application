package product.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import product.model.Product;


public interface ProductRepository extends JpaRepository<Product, Integer> {
	
	Optional<Product> findByName(String name);

}
