package com.github.memoryh.nutritionfacts.api.config;

import com.github.memoryh.nutritionfacts.api.logtrace.LogTrace;
import com.github.memoryh.nutritionfacts.api.logtrace.ThreadLocalLogTrace;
import com.github.memoryh.nutritionfacts.api.logtrace.aop.LogTraceAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public LogTrace logTrace() {
        return new ThreadLocalLogTrace();
    }

    @Bean
    public LogTraceAspect logTraceAspect(LogTrace logTrace) {
        return new LogTraceAspect(logTrace);
    }

}