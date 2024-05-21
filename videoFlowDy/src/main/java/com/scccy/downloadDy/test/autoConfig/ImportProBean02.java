package com.scccy.downloadDy.test.autoConfig;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
@ConfigurationProperties("usershici")
public class ImportProBean02 {

    private Integer id;
    private String name;
    private Integer age;

}
