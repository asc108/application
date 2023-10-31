package user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import user.model.Users;

public interface UserRepository extends JpaRepository<Users, Integer>{
	
	public Users findByEmail(String email);

}
