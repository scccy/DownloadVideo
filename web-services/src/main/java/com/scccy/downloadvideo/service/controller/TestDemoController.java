package com.scccy.downloadvideo.service.controller;

import com.scccy.downloadvideo.service.model.TestDemo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestDemoController {

    @GetMapping("/testdemo")
    public TestDemo getTestDemo(@RequestParam("id") Long id) {
        return new TestDemo()
                .setId(id)
                .setName("Test Demo " + id)
                .setDescription("This is test demo " + id);
    }
} 