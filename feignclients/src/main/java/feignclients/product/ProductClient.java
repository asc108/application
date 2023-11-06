package feignclients.product;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient("product")
public interface ProductClient {
	@GetMapping("api/product/name/{product}")
	public ProductResponse checkIfPresent(@PathVariable("product") String product);

}
  