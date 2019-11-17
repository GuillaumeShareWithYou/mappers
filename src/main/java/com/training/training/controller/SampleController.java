package com.training.training.controller;

import com.training.training.service.SampleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class SampleController {

    private SampleService sampleService;

    SampleController(SampleService sampleService) {

        this.sampleService = sampleService;
    }

    @GetMapping("/time")
    public void printTime() {
        this.sampleService.printTime();
        log.debug("call the time service");
    }
}
