package order.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "t_order")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {
	@Id
	@GeneratedValue( strategy = GenerationType.SEQUENCE)
	private Integer id;
	private String orderNumber;
	@OneToMany(cascade = CascadeType.ALL)
    private List<OrderItems> orderItems;
	

}
