package order.controller;

import java.util.List;

public record OrderRequest(List<OrderItemsDTO> orderItems) {

}
