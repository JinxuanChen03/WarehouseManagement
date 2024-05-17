package com.bjtu.warehousemanagebackend;

import com.bjtu.warehousemanagebackend.controller.LogController;
import com.bjtu.warehousemanagebackend.controller.dto.LoginDto;
import com.bjtu.warehousemanagebackend.domain.User;
import com.bjtu.warehousemanagebackend.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserServiceImpl userService;

    @Test
    public void testUserRegister() throws Exception {
        User newUser = new User();
        newUser.setName("testUser");
        newUser.setPassword("password");

        ResultActions resultActions = mockMvc.perform(post("/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\":\"testUser\",\"password\":\"password\"}"));

        resultActions.andExpect(status().isOk());
        // Add more assertions if needed
    }

    @Test
    public void testUserLogin() throws Exception {
        LoginDto loginDto = new LoginDto();
        loginDto.setName("testUser");
        loginDto.setPassword("password");

        ResultActions resultActions = mockMvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"testUser\",\"password\":\"password\"}"));

        resultActions.andExpect(status().isOk());
        // Add more assertions if needed
    }
}