package capstonedesign.arlabel.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@AutoConfigureMockMvc
@SpringBootTest
class ProductControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void 제품명으로_영양정보_및_무기물질함량_정보_조회() throws Exception {
        mockMvc.perform(get("/arlabel")
                        .param("product-name", "제주 삼다수(330ml)")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andExpect(content().json("{" +
                        "\"productName\":\"제주 삼다수(330ml)\"," +
                        "\"calcium\":\"2.5~4.0\"," +
                        "\"potassium\":\"1.5~3.4\"," +
                        "\"natrium\":\"4.0~7.2\"," +
                        "\"magnesium\":\"1.7~3.5\"," +
                        "\"fluorine\":\"불검출\"}", true) // strict = true : 응답이 정확한 순서와 정확한 필드를 포함하고 있는지 검증
                )
                .andDo(print()); // 요청, 응답 메시지 확인

        mockMvc.perform(get("/arlabel")
                        .param("product-name", "코카콜라(335ml)")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andExpect(content().json("{" +
                        "\"productName\":\"코카콜라(335ml)\"," +
                        "\"calories\":\"152kcal\"," +
                        "\"sodium\":\"21mg\"," +
                        "\"carbohydrates\":\"40g\"," +
                        "\"sugars\":\"39g\"," +
                        "\"transFats\":\"0g\"," +
                        "\"saturatedFats\":\"0g\"," +
                        "\"cholesterol\":\"0mg\"," +
                        "\"proteins\":\"0g\"}", true) // strict = true : 응답이 정확한 순서와 정확한 필드를 포함하고 있는지 검증
                )
                .andDo(print()); // 요청, 응답 메시지 확인
    }

}