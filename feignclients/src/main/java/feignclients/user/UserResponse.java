package feignclients.user;

public record UserResponse(String username,String password
		,String firstName,String lastName,String roles) {

}
