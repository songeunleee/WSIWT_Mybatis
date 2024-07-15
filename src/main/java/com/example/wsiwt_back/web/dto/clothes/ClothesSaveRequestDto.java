package com.example.wsiwt_back.web.dto.clothes;

import com.example.wsiwt_back.domain.clothes.Clothes;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ClothesSaveRequestDto {
    private String name;
    private List<Integer> type;
    private String category;
    private String author;
    private String url;

    public Clothes toEntity(Long userId){
        System.out.println(type.toString());
        return Clothes.builder().name(name).userId(userId).type(type.toString()
                .replace("[", "")
                .replace("]", "")
                        .replace(" ", "" )
                ).category(category).url(url).build();
    }
}
