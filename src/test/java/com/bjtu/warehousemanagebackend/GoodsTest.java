package com.bjtu.warehousemanagebackend;

import com.bjtu.warehousemanagebackend.controller.GoodsController;
import com.bjtu.warehousemanagebackend.domain.Goods;
import com.bjtu.warehousemanagebackend.service.impl.GoodsServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class GoodsTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private GoodsServiceImpl goodsService;

    @InjectMocks
    private GoodsController goodsController;

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
    public void testToken() throws  Exception{
        System.out.println(token);
    }

    @Test
    public void testAddGoods() throws Exception {
        Goods newGood = new Goods(); // Create a sample new good
        doNothing().when(goodsService).addGoods(newGood);
        mockMvc.perform(post("/goods")
                        .header("Token",token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Sample Good\",\"quantity\":10,\"count\": 10}")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateGoods() throws Exception {
        Goods updatedGood = new Goods(); // Create a sample updated good
        doNothing().when(goodsService).updateGoods(updatedGood);
        mockMvc.perform(put("/goods/1")
                        .header("Token",token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Updated Good\",\"quantity\":20,\"price\": 30}")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetGoodsById() throws Exception {
        Goods good = new Goods(); // Create a sample good
        when(goodsService.getGoodsById("10")).thenReturn(good); // Mock service response
        mockMvc.perform(get("/goods/1")
                        .header("Token",token)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").exists());
    }

    @Test
    public void testFindByLikeName() throws Exception {
        List<Goods> goodsList = Arrays.asList(new Goods(), new Goods()); // Create a list of sample goods
        when(goodsService.findByLikeName(anyString())).thenReturn(goodsList); // Mock service response
        mockMvc.perform(get("/goods/search/Sample")
                        .header("Token",token)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray());
    }

    @Test
    public void testGetAllGoods() throws Exception {
        List<Goods> goodsList = Arrays.asList(new Goods(), new Goods()); // Create a list of sample goods
        when(goodsService.getAllGoods()).thenReturn(goodsList); // Mock service response
        mockMvc.perform(get("/goods")
                        .header("Token",token)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray());
    }

    @Test
    public void testDeleteGoods() throws Exception {
        doNothing().when(goodsService).deleteGoods("1");
        mockMvc.perform(delete("/goods/1")
                        .header("Token",token)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}