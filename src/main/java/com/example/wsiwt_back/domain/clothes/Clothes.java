package com.example.wsiwt_back.domain.clothes;

import com.example.wsiwt_back.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor


public class Clothes extends BaseTimeEntity {


    private Long id;


    private String name;


    private String category;

    private String url;

    private Long userId;

    private String type;


    @Builder
    public Clothes(String name, String category, String url, Long userId, String type){
        this.name = name;
        this.category = category;
        this.url = url;
        this.userId = userId;
        this.type = type;
    }


}
