package order.service;

import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.Check;
import org.springframework.stereotype.Service;

import feignclients.product.ProductClient;
import feignclients.product.ProductResponse;
import lombok.RequiredArgsConstructor;
import order.controller.OrderItemsDTO;
import order.controller.OrderRequest;
import order.model.Order;
import order.model.OrderItems;
import order.repository.OrderRepository;

@Service
@RequiredArgsConstructor
public class OrderService {

	private final OrderRepository orderRepository;
	private final ProductClient productClient;

	public String placeOrder(OrderRequest request) {
		Order order = new Order();
		order.setOrderNumber(UUID.randomUUID().toString());
		List<OrderItems> orderItems = request.orderItems().stream().map(this::mapTo).toList();
		order.setOrderItems(orderItems);

		List<String> names = order.getOrderItems().stream().map(OrderItems::getName).toList();
		for (String str : names) {
			ProductResponse check = productClient.checkIfPresent(str);
			if (!check.isPresent()) {
				throw new IllegalStateException();
			}
		}

	}

	private OrderItems mapTo(OrderItemsDTO request) {
		OrderItems orderItems = OrderItems.builder().name(request.name()).quantity(request.quantity()).build();
		return orderItems;
	}

}
