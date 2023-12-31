package product.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import feignclients.product.InventoryResponse;
import feignclients.product.ProductResponse;
import lombok.RequiredArgsConstructor;
import product.model.Product;
import product.service.ProductService;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

	private final ProductService productService;

	@PostMapping("/add")
	public ResponseEntity<String> addProduct(@RequestBody ProductRequest request) {
		try {
			productService.addProduct(request);
			return ResponseEntity.status(HttpStatus.CREATED).body("Added");
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

	}
	@GetMapping("/all")
	public List<Product> allProducts() {
		return productService.allProducts();
	}
	@GetMapping("/{name}")
	public Product productByName(@PathVariable String name) {
		return productService.productByName(name);
	}

	@GetMapping("/name/{product}")
	public ProductResponse checkIfPresent(@PathVariable("product") String product) {
		boolean isPresent = productService.checkIfPresent(product);
		return new ProductResponse(isPresent);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<String> removeProduct(@PathVariable Integer id) {
		try {
			productService.removeProduct(id);
			return ResponseEntity.ok("Sucssessfull");
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	@GetMapping("/stock")
	public List<InventoryResponse> isInStock(@RequestParam("name") List<String> name) {
		return productService.isInStock(name);
		}
	
	@PutMapping("/update")
	public ResponseEntity<String> stockUpdate(@RequestParam ("name") String name,@RequestParam("quantity") Integer quantity) {
		try {
			productService.stockUpdate(name,quantity);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("Successful");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad request");
		}
	}

}
