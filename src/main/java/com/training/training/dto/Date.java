package com.training.training.dto;

import lombok.*;

import java.time.Instant;

@Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor
public class Date {

    private Instant heureCourrante;

    private String  mission;
}
