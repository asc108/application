package product.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import product.controller.ProductRequest;
import product.model.Product;
import product.repository.ProductRepository;

@RequiredArgsConstructor
@Service
@Slf4j
public class ProductService {

	private final ProductRepository productRepository;

	public void addProduct(ProductRequest request) {
		Product product = Product.builder().name(request.name()).description(request.description())
				.price(request.price()).build();

		productRepository.save(product);
	}

	public List<Product> allProducts() {
		List<Product> products = productRepository.findAll();
		return products;
		
	}

	public Product productByName(String name) {
		return productRepository.findByName(name).get();
	}

	public void removeUser(Integer id) {
		Product product = productRepository.findById(id).get();
		if(product != null) { 
			log.info("found");
			productRepository.delete(product);
		} else {
			throw new IllegalArgumentException("Not found product with that id");
		}
		
	}

}
