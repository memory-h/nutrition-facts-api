package capstonedesign.arlabel.controller;

import capstonedesign.arlabel.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    /*
     * HTTP GET 요청에 URL이 `/api?product-name=`인 경우 해당 메서드가 처리한다.
     * 반환 타입이 Object이므로 MessageConverter에 의해 application/json 형태로 return 된다.
     * Spring Boot가 제공하는 MappingJackson2HttpMessageConverter를 사용한다.
     */
    @GetMapping("/api")
    public Object request(@RequestParam("product-name") String productName) {
        return service.findByProductInfo(productName);
    }

    // 로드밸런서가 health check를 진행하는 경로
    @GetMapping("/server/health")
    public String health() {
        return "OK";
    }

}