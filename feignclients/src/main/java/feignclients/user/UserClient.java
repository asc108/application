package feignclients.user;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("user")
public interface UserClient {
	
	@GetMapping("api/users/user")
	public UserResponse findUser(@RequestParam("username") String username);
	

}
