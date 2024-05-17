package com.bjtu.warehousemanagebackend;

import com.bjtu.warehousemanagebackend.controller.AdminController;
import com.bjtu.warehousemanagebackend.domain.Admin;
import com.bjtu.warehousemanagebackend.service.impl.AdminServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.ResultActions;

import java.io.FileWriter;
import java.io.IOException;

import static org.mockito.Mockito.doNothing;
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AdminTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private AdminServiceImpl adminService;

    @InjectMocks
    private AdminController adminController;

    private Admin admin;

    @BeforeEach
    void setUp() {
        admin = new Admin();
        admin.setName("admin");
        admin.setPassword("123");
    }

    @Test
    void testAdminLogin() throws Exception {
        doNothing().when(adminService).adminRegister(admin);

        ResultActions resultActions=mockMvc.perform(MockMvcRequestBuilders.post("/admin/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"超级管理员\",\"password\":\"123\"}")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(200));
        // Extract and save the token
        String token = resultActions.andReturn().getResponse().getContentAsString();
        token = token.split("\"token\":")[1].split("\"")[1];
        String fileName = "token.txt";
        saveTokenToFile(token, fileName);

        // Print confirmation
        System.out.println("Token saved to " + fileName);
    }

    private String extractTokenFromResponse(String responseString) {
        // Assuming the token is directly returned as a string in the data field of the JSON response
        // Adjust this according to your actual JSON structure
        return responseString.split("\"token\":")[1].split("\"")[1];
    }

    private void saveTokenToFile(String token, String fileName) throws IOException {
        FileWriter writer = new FileWriter(fileName);
        writer.write(token);
        writer.close();
    }
}