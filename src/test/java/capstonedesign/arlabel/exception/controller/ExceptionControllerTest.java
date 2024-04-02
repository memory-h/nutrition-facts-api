package capstonedesign.arlabel.exception.controller;

import capstonedesign.arlabel.exception.NoSuchDBException;
import capstonedesign.arlabel.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@AutoConfigureMockMvc
@SpringBootTest
class ExceptionControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ProductService productService;

    @Test
    void 데이터베이스에_저장되지_않은_제품명을_조회한_경우() throws Exception {
        mockMvc.perform(get("/arlabel")
                        .param("product-name", "콜라(335m)")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("제품명을 다시 입력하세요."))
                .andDo(print());
    }

    @Test
    void 데이터베이스에_저장되지_않은_제품명을_조회한_경우_예외처리() {
        // 데이터베이스에 저장되지 않은 제품명으로 findByProductInfo() 메서드를 호출했을 때 발생하는 예외를 캡처한다.
        Throwable thrown = catchThrowable(() -> productService.findByProductInfo("환타(300ml)"));

        // Assert: 캡처된 예외가 'RuntimeException' 타입인지 검증한다.
        // 'productService.findByProductInfo()' 메서드가 'RuntimeException'을 정상적으로 발생시키는지 확인한다.
        assertThat(thrown).isInstanceOf(RuntimeException.class);

        // Assert: 발생한 'RuntimeException'의 원인이 또 다른 'RuntimeException' 타입인지 검증한다.
        // 일반적으로 예외가 감싸진 경우 이런 검증이 필요할 수 있음
        assertThat(thrown.getCause()).isInstanceOf(RuntimeException.class);

        // Assert: 두 번째 'RuntimeException'의 원인이 'NoSuchDBException' 타입인지 검증한다.
        // 이 검증은 우리가 예상하는 예외가 예외 체인의 어딘가에 포함되어 있는지 확인하는 데 사용된다.
        assertThat(thrown.getCause().getCause()).isInstanceOf(NoSuchDBException.class);
    }

}