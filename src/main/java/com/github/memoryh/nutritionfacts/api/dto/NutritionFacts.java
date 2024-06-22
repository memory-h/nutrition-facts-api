package com.github.memoryh.nutritionfacts.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class NutritionFacts {

    private String productName; // 제품 이름

    private String calories; // 칼로리

    private String sodium; // 나트륨

    private String carbohydrates; // 탄수화물

    private String sugars; // 당류

    private String transFats; // 트랜스지방

    private String saturatedFats; // 포화지방

    private String cholesterol; // 콜레스테롤

    private String proteins; // 단백질

}