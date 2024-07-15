package com.example.wsiwt_back.domain;

import lombok.Getter;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Getter
 // Auditing 기능 포함
public abstract class BaseTimeEntity {


    private LocalDateTime createdDate;


    private LocalDateTime modifiedDate;

}