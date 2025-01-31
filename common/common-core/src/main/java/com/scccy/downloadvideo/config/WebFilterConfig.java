import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.server.WebFilter;

@Configuration
public class WebFilterConfig {
    @Bean
    public WebFilter corsFilter() {
        return (exchange, chain) -> {
            // 处理请求
            return chain.filter(exchange);
        };
    }
} 