package com.scccy.videoBase;

import com.scccy.videoBase.myInterface.EnableMyAll;
import org.springframework.boot.SpringApplication;

@EnableMyAll
public class VideoBaseApplication {
    public static void main(String[] args) {
        SpringApplication.run(VideoBaseApplication.class, args);
    }
}
