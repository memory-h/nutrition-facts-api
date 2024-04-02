package capstonedesign.arlabel.interceptor;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class LogInterceptorTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void 스프링_인터셉터_경로가_arlabel로_시작하지_않는_경우() throws Exception {
        mockMvc.perform(get("/test")
                        .characterEncoding("UTF-8"))
                .andExpect(status().isNotFound());
    }

    @Test
    void 스프링_인터셉터_쿼리_파라미터_없는_경우() throws Exception {
        mockMvc.perform(get("/arlabel")
                        .param("product", "제주 삼다수(330ml)")
                        .characterEncoding("UTF-8"))
                .andExpect(status().isNotFound());
    }

}