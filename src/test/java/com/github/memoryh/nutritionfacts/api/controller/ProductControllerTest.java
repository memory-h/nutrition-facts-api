package com.github.memoryh.nutritionfacts.api.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class ProductControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("영양 성분 및 무기물질 함량 정보 단건 조회")
    void getProductInfoByNameAndOptionalSize() throws Exception {
        mockMvc.perform(get("/api/product")
                        .param("name", "제주 삼다수")
                        .param("size", "330ml")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andExpect(content().json("{" +
                                "\"productName\":\"제주 삼다수(330ml)\"," +
                                "\"calcium\":\"2.5~4.0\"," +
                                "\"potassium\":\"1.5~3.4\"," +
                                "\"sodium\":\"4.0~7.2\"," +
                                "\"magnesium\":\"1.7~3.5\"," +
                                "\"fluorine\":\"불검출\"," +
                                "\"catchmentArea\":\"제주특별자치도 제주시 조천읍 남조로 1717-3, 제주특별자치도개발공사\"" +
                                "}",
                        true) // strict = true : 응답이 정확한 순서와 정확한 필드를 포함하고 있는지 검증
                )
                .andDo(print()); // 요청, 응답 메시지 확인

        mockMvc.perform(get("/api/product")
                        .param("name", "코카콜라")
                        .param("size", "335ml")
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
                                "\"proteins\":\"0g\"" +
                                "}",
                        true) // strict = true : 응답이 정확한 순서와 정확한 필드를 포함하고 있는지 검증
                )
                .andDo(print()); // 요청, 응답 메시지 확인
    }

    @Test
    @DisplayName("영양 성분 및 무기물질 함량 정보 여러 건 조회")
    void getProductInfoByName() throws Exception {
        mockMvc.perform(get("/api/product")
                        .param("name", "제주 삼다수")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andExpect(content().json("{" +
                                "\"products\": [" +
                                "{" +
                                "\"productName\": \"제주 삼다수(330ml)\"," +
                                "\"calcium\": \"2.5~4.0\"," +
                                "\"potassium\": \"1.5~3.4\"," +
                                "\"sodium\": \"4.0~7.2\"," +
                                "\"magnesium\": \"1.7~3.5\"," +
                                "\"fluorine\": \"불검출\"," +
                                "\"catchmentArea\": \"제주특별자치도 제주시 조천읍 남조로 1717-3, 제주특별자치도개발공사\"" +
                                "}," +
                                "{" +
                                "\"productName\": \"제주 삼다수(500ml)\"," +
                                "\"calcium\": \"2.2~3.6\"," +
                                "\"potassium\": \"1.5~3.4\"," +
                                "\"sodium\": \"4.0~7.2\"," +
                                "\"magnesium\": \"1.0~2.8\"," +
                                "\"fluorine\": \"불검출\"," +
                                "\"catchmentArea\": \"제주특별자치도 제주시 조천읍 남조로 1717-3, 제주특별자치도개발공사\"" +
                                "}" +
                                "]" +
                                "}",
                        true) // strict = true : 응답이 정확한 순서와 정확한 필드를 포함하고 있는지 검증
                )
                .andDo(print()); // 요청, 응답 메시지 확인

        mockMvc.perform(get("/api/product")
                        .param("name", "코카콜라")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andExpect(content().json("{" +
                                "\"products\": [" +
                                "{" +
                                "\"productName\": \"코카콜라(335ml)\"," +
                                "\"calories\": \"152kcal\"," +
                                "\"sodium\": \"21mg\"," +
                                "\"carbohydrates\": \"40g\"," +
                                "\"sugars\": \"39g\"," +
                                "\"transFats\": \"0g\"," +
                                "\"saturatedFats\": \"0g\"," +
                                "\"cholesterol\": \"0mg\"," +
                                "\"proteins\": \"0g\"" +
                                "}," +
                                "{" +
                                "\"productName\": \"코카콜라(500ml)\"," +
                                "\"calories\": \"216kcal\"," +
                                "\"sodium\": \"15mg\"," +
                                "\"carbohydrates\": \"54g\"," +
                                "\"sugars\": \"54g\"," +
                                "\"transFats\": \"0g\"," +
                                "\"saturatedFats\": \"0g\"," +
                                "\"cholesterol\": \"0mg\"," +
                                "\"proteins\": \"0g\"" +
                                "}" +
                                "]" +
                                "}",
                        true) // strict = true : 응답이 정확한 순서와 정확한 필드를 포함하고 있는지 검증
                )
                .andDo(print()); // 요청, 응답 메시지 확인
    }

}