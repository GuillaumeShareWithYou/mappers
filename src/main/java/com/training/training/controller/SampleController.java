package com.training.training.controller;

import com.training.training.dto.Date;
import com.training.training.dto.Date2;
import com.training.training.mapper.DateMapper;
import com.training.training.service.SampleService;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestController
@Slf4j
public class SampleController {

    private SampleService sampleService;
    private DateMapper dateMapper = Mappers.getMapper(DateMapper.class);

    SampleController(SampleService sampleService) {

        this.sampleService = sampleService;
    }

    @GetMapping("/time")
    public Date printTime() {
        log.debug("call the time service");
        return null;
    }

    @PostMapping(value = "/time", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Date2 getTime (@RequestBody Date date){
        log.debug(date.toString());
        return dateMapper.map(date);
    }
}
