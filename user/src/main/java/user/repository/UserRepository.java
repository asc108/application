package user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import user.model.Users;

public interface UserRepository extends JpaRepository<Users, Integer>{
	
	public Users findByEmail(String email);
	
	Optional<Users> findUserByUsername(String username);

}
