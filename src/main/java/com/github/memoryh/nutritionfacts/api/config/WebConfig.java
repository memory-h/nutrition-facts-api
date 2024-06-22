package com.github.memoryh.nutritionfacts.api.config;

import com.github.memoryh.nutritionfacts.api.interceptor.LogInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final LogInterceptor logInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(logInterceptor) // 인터셉터를 등록
                .order(1) // 인터셉터의 실행 순서를 지정
                .addPathPatterns("/**") // 모든 경로에 대해 인터셉터가 적용
                .excludePathPatterns("/css/**", "/*.ico", "/error"); // 인터셉터 적용에서 제외될 경로 패턴을 지정
    }

}