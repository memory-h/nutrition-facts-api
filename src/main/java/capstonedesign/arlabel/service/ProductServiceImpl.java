package capstonedesign.arlabel.service;

import capstonedesign.arlabel.repository.InquiryAboutProductInfo;
import capstonedesign.arlabel.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;
    private final InquiryAboutProductInfo InquiryAboutProductInfo;

    /**
     * 제품명을 통해 제품 영양 정보 또는 무기 물질 함량 정보를 조회하는 비즈니스 로직
     *
     * 읽기 전용 커넥션을 활용하여, 리소스 사용량을 최적화한다.
     * JDBC는 ORM 프레임워크를 사용할 때보다는 더 제한적인 최적화가 이루어짐 (ORM에서 제공하는 일부 고급 기능들이 없기 때문)
     */
    @Transactional(readOnly = true)
    @Override
    public Object findByProductInfo(String productName) {
        boolean isWater = repository.isProductWater(productName);

        if (isWater) {
            // 해당 제품이 물인 경우 무기 물질 함량 정보를 가져온다.
            return InquiryAboutProductInfo.getInorganicMatterContentByProductName(productName);
        } else {
            // 해당 제품이 물이 아닌 경우 영양 정보를 가져온다.
            return InquiryAboutProductInfo.getNutritionInfoByProductName(productName);
        }
    }

}