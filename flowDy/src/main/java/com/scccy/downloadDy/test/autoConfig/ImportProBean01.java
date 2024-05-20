package com.scccy.downloadDy.test.autoConfig;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class ImportProBean01 {
//    demo04.sms.url=http://localhost:3000/sendmsg
//demo04.sms.token=qeqwe1231qwe123qe
//demo04.sms.templateId=10011
    @Value("${usershici.id}")
    private Integer id;
    @Value("${usershici.name}")
    private String name;
    @Value("${usershici.age}")
    private Integer age;
}
