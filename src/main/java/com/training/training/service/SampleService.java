package com.training.training.service;

import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class SampleService {

    public Instant getTime() {
        return Instant.now();
    }
}
