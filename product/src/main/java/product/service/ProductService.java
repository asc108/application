package product.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.stereotype.Service;

import feignclients.product.InventoryResponse;
import feignclients.user.UserClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import product.controller.ProductRequest;
import product.model.Inventory;
import product.model.Product;
import product.repository.InventoryRepository;
import product.repository.ProductRepository;

@RequiredArgsConstructor
@Service
@Slf4j
public class ProductService {

	private final ProductRepository productRepository;
	private final InventoryRepository inventoryRepository;
	private final UserClient userClient;

	public void addProduct(ProductRequest request) {
		Product product = Product.builder().name(request.name()).description(request.description())
				.price(request.price()).build();
		if (productRepository.findByName(request.name()).isEmpty()) {
			productRepository.save(product);
			Inventory inventory = Inventory.builder().product(product)
					.skucode(new RandomStringUtils().randomAlphabetic(5)).quantity(new Random().nextInt(100)).build();
			inventoryRepository.save(inventory);
		} else {
			throw new IllegalArgumentException("Already exists");
		}
	}
	
	public List<Product> allProducts() {
		List<Product> products = productRepository.findAll();
		return products;

	}

	public Product productByName(String name) {
		return productRepository.findByName(name).get();
	}

	public void removeProduct(Integer id) {
		Product product = productRepository.findById(id).get();
		if (product != null) {
			log.info("found");
			productRepository.delete(product);
		} else {
			throw new IllegalArgumentException("Not found product with that id");
		}

	}

	public boolean checkIfPresent(String name) {
		return productRepository.findByName(name).isPresent();

	}

	public List<InventoryResponse> isInStock(List<String> names) {
		List<InventoryResponse> result = new ArrayList<>();
		for (String name : names) {
			boolean isInStock = stock(name);
			result.add(new InventoryResponse(name, true));

		}
		return result;

	}

	private boolean stock(String name) {
		Product product = productRepository.findByName(name).get();
		if (product != null && product.getInventory() != null) {
			return product.getInventory().getQuantity() > 0;
		}
		return false;

	}

	public void stockUpdate(String name, Integer quantity) {
		Product product = productByName(name);
		int update = product.getInventory().getQuantity() - quantity;
		product.getInventory().setQuantity(update);
		productRepository.save(product);

	}
	
	


	
	

}
