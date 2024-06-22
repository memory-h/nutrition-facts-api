package com.github.memoryh.nutritionfacts.api.repository;

public interface ProductRepository {

    // 제품 이름을 기반으로 해당 제품이 물인지 아닌지 판단하는 메서드
    boolean isProductWater(String productName);

}