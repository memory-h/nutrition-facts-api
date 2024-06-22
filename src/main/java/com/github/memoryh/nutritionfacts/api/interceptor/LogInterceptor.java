package com.github.memoryh.nutritionfacts.api.interceptor;

import com.github.memoryh.nutritionfacts.api.logtrace.LogTrace;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@Slf4j
@Component
@RequiredArgsConstructor
public class LogInterceptor implements HandlerInterceptor {

    private final LogTrace logTrace;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // "X-Forwarded-For" 헤더에서 클라이언트의 실제 IP 주소를 가져온다.
        String xForwardedForHeader = request.getHeader("X-Forwarded-For");

        /*
         * "X-Forwarded-For" 헤더가 존재하면, 이 헤더에서 첫 번째 IP 주소(클라이언트의 원본 IP)를 사용한다.
         * 만약 헤더가 없다면, HttpServletRequest의 getRemoteAddr() 메소드를 통해 IP 주소를 가져온다. (로드 밸런서의 IP 주소)
         */
        String ipAddress = xForwardedForHeader != null ? xForwardedForHeader.split(",")[0] : request.getRemoteAddr();
        String userAgent = request.getHeader("User-Agent");
        String requestUrl = URLDecoder.decode(request.getRequestURL().toString(), StandardCharsets.UTF_8);
        String requestMethod = request.getMethod();

        // 쿼리 파라미터가 있는 경우
        if (request.getQueryString() != null) {
            requestUrl += "?" + URLDecoder.decode(request.getQueryString(), StandardCharsets.UTF_8);
        }

        // 로드밸런서가 health check를 진행하는 경로
        if (request.getRequestURI().equals("/server/health")) {
            logTrace.requestInfo(requestMethod, requestUrl, ipAddress, userAgent);

            return true;
        }

        // 크롤러가 모든 페이지에 접근하는 것을 차단
        else if (request.getRequestURI().equals("/robots.txt")) {
            log.info("[Method]: {}, [URL]: {}, [User IP]: {}, [User-Agent]: {}", requestMethod, requestUrl, ipAddress, userAgent);

            return true;
        }

        else if (request.getRequestURI().equals("/api/product") && request.getParameter("name") != null) {
            // 사용자 IP와 User-Agent를 포함하여 로그 메시지를 생성하고 추적 시작
            logTrace.requestInfo(requestMethod, requestUrl, ipAddress, userAgent);

            return true;
        }

        else {
            log.warn("[잘못된 요청] [Method]: {}, [URL]: {}, [User IP]: {}, [User-Agent]: {}", requestMethod, requestUrl, ipAddress, userAgent);

            // HTTP Status Code 404
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);

            // 요청 처리 중단
            return false;
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        logTrace.remove();
    }

}