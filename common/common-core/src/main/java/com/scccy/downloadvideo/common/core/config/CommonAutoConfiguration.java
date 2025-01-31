@Configuration
@EnableConfigurationProperties({
    DownloadProperties.class,
    WebSocketProperties.class
})
public class CommonAutoConfiguration {
    
    @Bean
    @ConditionalOnMissingBean
    public XBogusManager xBogusManager() {
        return new XBogusManager();
    }
    
    @Bean
    @ConditionalOnMissingBean
    public ObjectMapper objectMapper() {
        return new ObjectMapper()
            .setSerializationInclusion(JsonInclude.Include.NON_NULL)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }
    
    @Bean
    @ConditionalOnMissingBean
    public OkHttpClient okHttpClient() {
        return new OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build();
    }
} 