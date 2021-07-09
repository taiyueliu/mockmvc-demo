package com.example.mockmvcdemo;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;

@SpringBootTest
@Slf4j
class MockmvcDemoApplicationTests {
    private MockMvc mockMvc;
    @Resource
    private WebApplicationContext webApplicationContext;
    @BeforeEach
    public void setUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void contextLoads() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get("/test")
                .param("ss", "test")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .accept(MediaType.TEXT_PLAIN_VALUE)
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
        log.info("===contentAsString==={}",contentAsString);
    }

}
