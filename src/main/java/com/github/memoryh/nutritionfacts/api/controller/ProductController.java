package com.github.memoryh.nutritionfacts.api.controller;

import com.github.memoryh.nutritionfacts.api.service.ProductService;
import com.github.memoryh.nutritionfacts.api.service.ProductTypeCheck;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;
    private final ProductTypeCheck productTypeCheck;

    @GetMapping("/api/product")
    public ResponseEntity<?> getProductInfoByNameAndOptionalSize(@RequestParam(value = "name") String productName,
                                                                 @RequestParam(value = "size", required = false) String size) {

        // 해당 제품이 물인지 아닌지 확인
        boolean isWater = productTypeCheck.checkProductType(productName);

        if (size == null) {
            return new ResponseEntity<>(service.getProductInfoByProductNameAndType(productName, isWater), HttpStatus.OK);
        } else {
            if (isWater) {
                return new ResponseEntity<>(service.getMineralContentByProductNameAndSize(productName, size), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(service.getNutritionFactsByProductNameAndSize(productName, size), HttpStatus.OK);
            }
        }
    }

    // 로드밸런서가 health check를 진행하는 엔드포인트
    @GetMapping("/server/health")
    public String health() {
        return "OK";
    }

}