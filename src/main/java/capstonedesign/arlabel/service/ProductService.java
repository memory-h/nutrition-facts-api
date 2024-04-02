package capstonedesign.arlabel.service;

public interface ProductService {

    // 제품 이름을 기반으로 제품 정보를 조회하는 메서드
    Object findByProductInfo(String productName);

}