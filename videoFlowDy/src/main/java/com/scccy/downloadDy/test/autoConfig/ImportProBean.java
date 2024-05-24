package com.scccy.downloadDy.test.autoConfig;

import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Data
@ConfigurationProperties(prefix = "usershici")
public class ImportProBean implements InitializingBean {
    private Integer id;
    private String name;
    private Integer age;

    public static Integer ID;
    public static String NAME;
    public static Integer AGE;

    @Override
    public void afterPropertiesSet() throws Exception {
        ID = this.getId();
        NAME = this.getName();
        AGE = this.getAge();
    }
}

