package product.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {
	@Id
	@SequenceGenerator(name = "product_id_sequence", sequenceName = "product_id_sequence")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_id_sequence")
	private Integer id;
	private String name;
	private String description;
	private BigDecimal price;
	@JsonManagedReference
	 @OneToOne(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
	    private Inventory inventory;
}
