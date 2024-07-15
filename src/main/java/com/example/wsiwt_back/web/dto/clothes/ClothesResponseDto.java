package com.example.wsiwt_back.web.dto.clothes;

import com.example.wsiwt_back.domain.clothes.Clothes;
import com.example.wsiwt_back.web.dto.comment.CommentResponseDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ClothesResponseDto {

    @ApiModelProperty(value = "clothes의 id", example = "1")
    private Long id;
    @ApiModelProperty(value = "clothes의 이름", example = "string")
    private String name;
    @ApiModelProperty(value = "clothes의 타입", example = "[1,2]")
    private List<Integer> type;
    @ApiModelProperty(value = "clothes의 카테고리", example = "패딩")
    private String category;
    @ApiModelProperty(value = "clothes의 이미지 주소", example = "url")
    private String url;
    @ApiModelProperty(value = "clothes의 생성 시각")
    private LocalDateTime createdAt;
    @ApiModelProperty(value = "clothes의 수정 시각")
    private LocalDateTime updatedAt;

    public ClothesResponseDto(Clothes clothes){
        this.id = clothes.getId();
        this.name = clothes.getName();
        this.category = clothes.getCategory();
        this.createdAt = clothes.getCreatedDate();
        this.updatedAt = clothes.getModifiedDate();
        this.type = Arrays.stream(clothes.getType().split(",")).map(Integer::parseInt).collect(Collectors.toList());
        this.url = clothes.getUrl();
    }
}


