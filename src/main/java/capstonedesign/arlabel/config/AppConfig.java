package capstonedesign.arlabel.config;

import capstonedesign.arlabel.logtrace.LogTrace;
import capstonedesign.arlabel.logtrace.ThreadLocalLogTrace;
import capstonedesign.arlabel.logtrace.aop.LogTraceAspect;
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