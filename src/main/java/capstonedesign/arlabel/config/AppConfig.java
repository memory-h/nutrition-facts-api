package capstonedesign.arlabel.config;

import capstonedesign.arlabel.logtrace.LogTrace;
import capstonedesign.arlabel.logtrace.ThreadLocalLogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public LogTrace logTrace() {
        return new ThreadLocalLogTrace();
    }

}