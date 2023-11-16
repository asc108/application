package user;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import user.controller.UserRegistrationRequest;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControlTest {
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;

	
	@Test
	@WithMockUser(username = "asc")
	public void Test1() throws Exception {
		mockMvc.perform(get("/api/users/allUsers")).andExpect(status().isOk());

	}

	@Test
	@WithMockUser
    public void testRegisterNewUser() throws Exception {
		UserRegistrationRequest request = new UserRegistrationRequest("drasko", "petrovic", "d@gmail.com", "dk108", "11pd");
        String newUserJson = objectMapper.writeValueAsString(request);
        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/users/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(newUserJson))
                .andExpect(status().isCreated()); 
    }
	 

}