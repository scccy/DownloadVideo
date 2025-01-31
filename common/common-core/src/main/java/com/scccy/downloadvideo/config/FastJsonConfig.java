import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.config.WebFluxConfigurer;

@Configuration
public class FastJsonConfig implements WebFluxConfigurer {
    @Override
    public void configureHttpMessageCodecs(ServerCodecConfigurer configurer) {
        configurer.defaultCodecs().jackson2JsonEncoder(new Jackson2JsonEncoder(
                new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL)
        ));
        configurer.defaultCodecs().jackson2JsonDecoder(new Jackson2JsonDecoder());
    }
} 