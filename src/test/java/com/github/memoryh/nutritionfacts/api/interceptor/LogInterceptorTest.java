package com.github.memoryh.nutritionfacts.api.interceptor;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class LogInterceptorTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("잘못된 엔드포인트로 요청한 경우")
    void invalidQueryParameterReturnsNotFound() throws Exception {
        mockMvc.perform(get("/test")
                        .characterEncoding("UTF-8"))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("쿼리 파라미터가 잘못된 경우")
    void queryParameterIsIncorrect() throws Exception {
        mockMvc.perform(get("/api/product")
                        .param("names", "제주 삼다수")
                        .characterEncoding("UTF-8"))
                .andExpect(status().isNotFound());
    }

}