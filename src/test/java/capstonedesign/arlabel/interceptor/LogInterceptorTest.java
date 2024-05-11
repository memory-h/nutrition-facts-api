package capstonedesign.arlabel.interceptor;

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

@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
class LogInterceptorTest {

    @Autowired
    MockMvc mockMvc;

    @DisplayName("경로가 /api로 시작하지 않는 경우")
    @Test
    void pathDoesNotStartWithArlabel() throws Exception {
        mockMvc.perform(get("/test")
                        .characterEncoding("UTF-8"))
                .andExpect(status().isNotFound());
    }

    @DisplayName("쿼리 파라미터가 잘못된 경우")
    @Test
    void queryParameterIsIncorrect() throws Exception {
        mockMvc.perform(get("/api")
                        .param("product", "제주 삼다수(330ml)")
                        .characterEncoding("UTF-8"))
                .andExpect(status().isNotFound());
    }

}