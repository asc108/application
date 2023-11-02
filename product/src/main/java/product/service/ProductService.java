package product.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import product.controller.ProductRequest;
import product.model.Product;
import product.repository.ProductRepository;

@RequiredArgsConstructor
@Service
public class ProductService {

	private final ProductRepository productRepository;

	public void addProduct(ProductRequest request) {
		Product product = Product.builder().name(request.name()).description(request.description())
				.price(request.price()).build();

		productRepository.save(product);
	}

}
