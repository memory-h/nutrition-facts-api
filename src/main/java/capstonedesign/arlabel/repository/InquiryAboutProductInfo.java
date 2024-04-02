package capstonedesign.arlabel.repository;

import capstonedesign.arlabel.dto.InorganicMatterContent;
import capstonedesign.arlabel.dto.NutritionInfo;

public interface InquiryAboutProductInfo {

    // 무기 물질 함량 정보를 조회하는 메서드
    InorganicMatterContent getInorganicMatterContentByProductName(String productName);

    // 제품 영양 정보를 조회하는 메서드
    NutritionInfo getNutritionInfoByProductName(String productName);

}