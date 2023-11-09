package feignclients.product;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient("product")
public interface InventoryClient {
	@GetMapping("api/product/stock")
	public List<InventoryResponse> isInStock(@RequestParam("name") List<String> name);
	

	@PutMapping("api/product/update")
	public void stockUpdate(@RequestParam String name,@RequestParam Integer quantiy);
	
	@GetMapping("/{name}")
	public ProductResponse productByName(@PathVariable String name);
		
	

}
