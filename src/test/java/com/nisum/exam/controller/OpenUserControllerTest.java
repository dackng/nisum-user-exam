package com.nisum.exam.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.nisum.exam.api.controller.OpenUserController;
import com.nisum.exam.security.JwtUtils;
import com.nisum.exam.service.UserService;
import com.nisum.exam.util.DataMockSet;

import net.minidev.json.JSONObject;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = OpenUserController.class)
@Import(JwtUtils.class)
@AutoConfigureMockMvc
public class OpenUserControllerTest {


	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService userService;
	
	@Test
	public void saveUser_whenAllIsOk_thenReturnUserCreated() throws Exception {
		JSONObject body = DataMockSet.getUserInfoJSONRequestMock();
		
		mockMvc.perform(post("/open-api/v1/users")
				.contentType(MediaType.APPLICATION_JSON)
				.content(body.toString()))
				.andExpect(status().isCreated());
	}
	
    @Test
    public void saveUser_whenEmailIsInvalid_thenReturnBadRequest() throws Exception {
    	JSONObject body = DataMockSet.getUserInfoJSONRequestMock();
    	body.put("email", "test@dominio.com");

    	mockMvc.perform(post("/open-api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body.toString()))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", Matchers.is("Correo debe tener el siguiente formato aaaaaaa@dominio.cl")));
    }

    @Test
    public void saveUser_whenPasswordIsInvalid_thenReturnBadRequest() throws Exception {
    	JSONObject body = DataMockSet.getUserInfoJSONRequestMock();
    	body.put("password", "password123");

    	mockMvc.perform(post("/open-api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body.toString()))
                .andExpect(status().isBadRequest())
                .andExpect(
                	jsonPath("$.message", Matchers.is("Contraseña debe tener una Mayuscula, letras minúsculas y dos numeros"))
                );
    }
    
    @Test
    public void saveUser_whenEmailIsInvalid_and_passwordIsInvalid_thenReturnBadRequest() throws Exception {
    	JSONObject body = DataMockSet.getUserInfoJSONRequestMock();
    	body.put("email", "test@dominio.com");
    	body.put("password", "password123");

    	mockMvc.perform(post("/open-api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body.toString()))
                .andExpect(status().isBadRequest())
                .andExpect(
                    jsonPath("$.message", Matchers.is("Contraseña debe tener una Mayuscula, letras minúsculas y dos numeros "
                    		+ "| Correo debe tener el siguiente formato aaaaaaa@dominio.cl"))
                );
    }
}
