package com.github.memoryh.nutritionfacts.api.repository;

import com.github.memoryh.nutritionfacts.api.dto.MineralContent;
import com.github.memoryh.nutritionfacts.api.dto.NutritionFacts;

import java.util.List;

public interface InquiryAboutProductInfo {

    // 제품명으로 해당 제품의 모든 크기의 무기물질 함량 정보들을 조회
    List<MineralContent> findAllSizesMineralContentByProductName(String productName);

    // 제품명으로 해당 제품의 모든 크기의 영양 성분 정보들을 조회
    List<NutritionFacts> findAllSizesNutritionFactsByProductName(String productName);

    // 무기물질 함량 정보를 단일 조회
    MineralContent findMineralContentByProductNameAndSize(String productName, String size);

    // 영양 성분 정보를 단일 조회
    NutritionFacts findNutritionFactsByProductNameAndSize(String productName, String size);

}