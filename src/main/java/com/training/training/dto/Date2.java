package com.training.training.dto;

import lombok.*;

import java.time.Instant;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Date2 {

    private Instant heureCourrante;

    private String  missionStatus;

    private Integer count;
}
