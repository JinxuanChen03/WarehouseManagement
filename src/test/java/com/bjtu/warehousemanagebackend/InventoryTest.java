package com.bjtu.warehousemanagebackend;

import com.bjtu.warehousemanagebackend.controller.InventoryController;
import com.bjtu.warehousemanagebackend.service.impl.InventoryServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class InventoryTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private InventoryServiceImpl inventoryService;

    @InjectMocks
    private InventoryController inventoryController;

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
    @WithMockUser(roles = {"SUPER_ADMIN", "ADMIN", "USER"})
    public void testFindAll() throws Exception {
//        doNothing().when(inventoryService).findAll();
        mockMvc.perform(get("/inventory")
                        .header("Token",token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = {"SUPER_ADMIN", "ADMIN", "USER"})
    public void testFindByWarehouse() throws Exception {
        String warehouseId = "23";
//        doNothing().when(inventoryService).findByWarehouse(warehouseId);
        mockMvc.perform(get("/inventory/warehouse/{id}", warehouseId)
                        .header("Token",token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = {"SUPER_ADMIN", "ADMIN", "USER"})
    public void testFindByGoods() throws Exception {
        String goodsId = "4";

//        doNothing().when(inventoryService).findByGoodsId(goodsId);
        mockMvc.perform(get("/inventory/goods/{id}", goodsId)
                        .header("Token",token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}