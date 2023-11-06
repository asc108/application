package order.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import feignclients.product.InventoryClient;
import feignclients.product.InventoryResponse;
import feignclients.product.ProductClient;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import order.controller.OrderItemsDTO;
import order.controller.OrderRequest;
import order.model.Order;
import order.model.OrderItems;
import order.repository.OrderRepository;

@Service
@AllArgsConstructor
@Slf4j

public class OrderService {

	private final OrderRepository orderRepository;
	private final InventoryClient inventoryClient;

	public boolean placeOrder(OrderRequest request) {
		Order order = new Order();
		order.setOrderNumber(UUID.randomUUID().toString());
		List<OrderItems> orderItems = request.orderItems().stream().map(this::mapTo).toList();
		order.setOrderItems(orderItems);

		List<String> names = order.getOrderItems().stream().map(OrderItems::getName).toList();
		
		
	/*	for (String str : names) {
			ProductResponse check = productClient.checkIfPresent(str);
			if (!check.isPresent()) {
				throw new IllegalStateException();
			}
		}*/ 
		
		List<InventoryResponse> stockCheck = inventoryClient.isInStock(names);
		boolean allInStock = stockCheck.stream().allMatch(InventoryResponse::isInStock);
		if(allInStock == true) {
			orderRepository.save(order);
			
			return true;
		} else {
			throw new IllegalArgumentException("Product is not in stock currently,please try again soon!");
		}
	}

	private OrderItems mapTo(OrderItemsDTO request) {
		OrderItems orderItems = OrderItems.builder().name(request.name()).quantity(request.quantity()).build();
		return orderItems;
	}

	public List<Order> allOrders() {
		return orderRepository.findAll();
		
		
	}

}
