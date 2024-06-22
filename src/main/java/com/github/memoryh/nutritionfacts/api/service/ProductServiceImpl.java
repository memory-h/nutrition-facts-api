package com.github.memoryh.nutritionfacts.api.service;

import com.github.memoryh.nutritionfacts.api.dto.MineralContent;
import com.github.memoryh.nutritionfacts.api.dto.NutritionFacts;
import com.github.memoryh.nutritionfacts.api.repository.InquiryAboutProductInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final InquiryAboutProductInfo inquiryAboutProductInfo;

    @Override
    @Transactional(readOnly = true)
    public Products<?> getProductInfoByProductNameAndType(String productName, boolean isWater) {
        if (isWater) {
            // 해당 제품이 물인 경우 productName를 사용하여 무기물질 함량 정보를 가져온다.
            return new Products<>(inquiryAboutProductInfo.findAllSizesMineralContentByProductName(productName));
        } else {
            // 해당 제품이 물이 아닌 경우 productName를 사용하여 영양 성분 정보를 가져온다.
            return new Products<>(inquiryAboutProductInfo.findAllSizesNutritionFactsByProductName(productName));
        }
    }

    @Override
    @Transactional(readOnly = true)
    public MineralContent getMineralContentByProductNameAndSize(String productName, String size) {
        // 해당 제품이 물인 경우 productName과 size를 사용하여 무기물질 함량 정보를 가져온다.
        return inquiryAboutProductInfo.findMineralContentByProductNameAndSize(productName, size);
    }

    @Override
    @Transactional(readOnly = true)
    public NutritionFacts getNutritionFactsByProductNameAndSize(String productName, String size) {
        // 해당 제품이 물이 아닌 경우 productName과 size를 사용하여 영양 성분 정보를 가져온다.
        return inquiryAboutProductInfo.findNutritionFactsByProductNameAndSize(productName, size);
    }

}