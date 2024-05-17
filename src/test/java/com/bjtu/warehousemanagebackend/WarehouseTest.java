package com.bjtu.warehousemanagebackend;

import com.bjtu.warehousemanagebackend.controller.WarehouseController;
import com.bjtu.warehousemanagebackend.domain.Result;
import com.bjtu.warehousemanagebackend.domain.Warehouse;
import com.bjtu.warehousemanagebackend.service.impl.WarehouseServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class WarehouseTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WarehouseServiceImpl warehouseService;

    private String token;

    {
        try {
            token = new String(Files.readAllBytes(Paths.get("token.txt")));
        } catch (IOException e) {
            // 处理读取文件时可能出现的异常
            e.printStackTrace();
        }
    }
    @Test
    public void testAddWarehouse() throws Exception {
        Warehouse warehouse = new Warehouse();
        // Set warehouse properties

        doNothing().when(warehouseService).addWareHouse(warehouse);
        mockMvc.perform(post("/warehouses")
                        .header("Token",token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Sample WareHouse\",\"addr\": \"Sample Addr\"}")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    public void testUpdateWarehouse() throws Exception {
        Warehouse warehouse = new Warehouse();
        // Set warehouse properties

        doNothing().when(warehouseService).updateWarehouse(warehouse);

        mockMvc.perform(put("/warehouses/{id}", "1")
                        .header("Token",token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Update WareHouse\",\"addr\": \"Update Addr\"}")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    public void testGetWarehouseById() throws Exception {
        Warehouse warehouse = new Warehouse();
        // Set warehouse properties

        when(warehouseService.getWarehouseById("1")).thenReturn(warehouse);

        mockMvc.perform(get("/warehouses/{id}", "1")
                        .header("Token",token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").exists());

        verify(warehouseService, times(1)).getWarehouseById("1");
    }

    @Test
    public void testSearchByName() throws Exception {
        List<Warehouse> warehouseList = new ArrayList<>();
        // Add warehouses to the list

        when(warehouseService.searchByName("name")).thenReturn(warehouseList);

        mockMvc.perform(get("/warehouses/search/{name}", "Update Warehouse")
                        .header("Token",token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray());

        verify(warehouseService, times(1)).searchByName("Update Warehouse");
    }

    @Test
    public void testGetAll() throws Exception {
        List<Warehouse> warehouseList = new ArrayList<>();
        // Add warehouses to the list

        when(warehouseService.getAll()).thenReturn(warehouseList);

        mockMvc.perform(get("/warehouses")
                        .header("Token",token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray());

        verify(warehouseService, times(1)).getAll();
    }

    @Test
    public void testDeleteWarehouse() throws Exception {
        mockMvc.perform(delete("/warehouses/{id}", "1").header("Token",token))
                .andExpect(status().isOk());

        verify(warehouseService, times(1)).deleteWarehouse("1");
    }

    // Helper method to convert object to JSON string
    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}