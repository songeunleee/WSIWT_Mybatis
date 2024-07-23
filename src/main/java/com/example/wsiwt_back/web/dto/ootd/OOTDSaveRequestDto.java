package com.example.wsiwt_back.web.dto.ootd;

import com.example.wsiwt_back.domain.clothes.Clothes;
import com.example.wsiwt_back.domain.ootd.OOTD;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@Getter
@NoArgsConstructor

public class OOTDSaveRequestDto {

    private String contents;

    private String url;

    @Builder
    public OOTDSaveRequestDto(String contents, String author,String url){
        this.contents = contents;

     //   this.image = image;
        this.url = url;

    }

    public OOTD toEntity(){
        return OOTD.builder().contents(contents).url(url).build();
    }
}
