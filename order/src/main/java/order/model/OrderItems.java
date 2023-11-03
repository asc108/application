package order.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "t_orderItems")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderItems {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private String id;

	private String name;
	private Integer quantity;

}
