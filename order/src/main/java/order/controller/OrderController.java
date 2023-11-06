package order.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import order.model.Order;
import order.service.OrderService;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {
	
	private final OrderService orderService;
	
	@PostMapping("/buy")
	public ResponseEntity<String> placeOrder(@RequestBody OrderRequest request) {
		try {
			orderService.placeOrder(request);
			return new ResponseEntity<>("Order created",HttpStatus.CREATED);
			
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<String>("Not placed!",HttpStatus.BAD_REQUEST);
		}
		
		
	}
	@GetMapping("/all")
	public List<Order> allOrders() {
		return orderService.allOrders();
		
	}

}
