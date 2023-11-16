package product;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import product.controller.ProductRequest;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductTest {
	@Autowired
	private MockMvc mock;
	@Autowired
	private ObjectMapper objectMapper;
	
	@Test
	public void allProductTest() throws Exception {
		mock.perform(get("/api/product/all")).andExpect(status().isOk());
	}
	
	@Test
    public void addProductTest() throws Exception {
		ProductRequest request = new ProductRequest("SamsungS23","phone",BigDecimal.valueOf(1050));
        String newUserJson = objectMapper.writeValueAsString(request);
        mock.perform(MockMvcRequestBuilders
                .post("/api/product/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(newUserJson))
                .andExpect(status().isCreated()); 
    }
	@Test
	public void removeProduct() throws Exception {
		mock.perform(delete("/api/product/202")).andExpect(status().isOk());
		
	}
}
