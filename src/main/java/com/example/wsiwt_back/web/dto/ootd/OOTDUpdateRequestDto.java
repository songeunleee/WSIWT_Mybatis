package com.example.wsiwt_back.web.dto.ootd;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor

public class OOTDUpdateRequestDto {

    private String url;
    private String contents;

    @Builder
    public OOTDUpdateRequestDto(String url, String contents){
        this.url = url;
        this.contents = contents;
    }
}
