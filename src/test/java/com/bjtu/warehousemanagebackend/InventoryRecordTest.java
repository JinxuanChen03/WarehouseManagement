package com.bjtu.warehousemanagebackend;

import com.bjtu.warehousemanagebackend.domain.InventoryRecord;
import com.bjtu.warehousemanagebackend.service.impl.InventoryRecordServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class InventoryRecordTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InventoryRecordServiceImpl recordService;

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
    public void testIn() throws Exception {
        InventoryRecord record = new InventoryRecord();
        doNothing().when(recordService).in(record);
        mockMvc.perform(MockMvcRequestBuilders.post("/record/in")
                        .header("Token",token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"cid\":\"4\",\"wid\":23,\"count\": 1,\"description\":\"hahhahaha\"}")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testOut() throws Exception {
        InventoryRecord record = new InventoryRecord(/* add necessary parameters */);
        doNothing().when(recordService).out(record);
        mockMvc.perform(MockMvcRequestBuilders.post("/record/out")
                        .header("Token",token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"cid\":\"4\",\"wid\":23,\"count\": 1,\"description\":\"hahhahaha\"}")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testFindRecordByWarehouse() throws Exception {
        String warehouseId = "23";
        when(recordService.findAllByWarehouseId(warehouseId)).thenReturn(Collections.emptyList());

        mockMvc.perform(MockMvcRequestBuilders.get("/record/warehouse/{id}", warehouseId)
                        .header("Token",token))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testFindRecordByGoods() throws Exception {
        String goodsId = "4";
        when(recordService.findAllByGoodsId(goodsId)).thenReturn(Collections.emptyList());

        mockMvc.perform(MockMvcRequestBuilders.get("/record/goods/{id}", goodsId)
                        .header("Token",token))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}