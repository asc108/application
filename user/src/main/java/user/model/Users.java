package user.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Users {

	@Id
	@SequenceGenerator(name = "user_id_sequence", sequenceName = "user_id_sequence")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_sequence")
	private Integer id;
	@NotBlank(message = "Username cannot be empty!")
	private String username;
	@NotBlank(message = "Password cannot be empty!")
	private String password;
	@NotBlank(message = "Name cannot be empty!")
	private String firstName;
	@NotBlank(message = "Lastname cannot be empty!")
	private String lastName;
	@NotBlank(message = "Email cannot be empty!")
	private String email;
	private String role;

}
