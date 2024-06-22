package com.github.memoryh.nutritionfacts.api.service;

import com.github.memoryh.nutritionfacts.api.dto.MineralContent;
import com.github.memoryh.nutritionfacts.api.dto.NutritionFacts;

public interface ProductService {

    // 제품명을 기반으로 제품 정보를 가져온다.
    Products<?> getProductInfoByProductNameAndType(String productName, boolean isWater);

    // 제품명과 제품 크기로 무기물질 함량 정보를 가져온다.
    MineralContent getMineralContentByProductNameAndSize(String productName, String size);

    // 제품명과 제품 크기로 제품 영양 성분 정보를 가져온다.
    NutritionFacts getNutritionFactsByProductNameAndSize(String productName, String size);

}