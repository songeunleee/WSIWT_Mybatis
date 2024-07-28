package com.example.wsiwt_back.web.dto.user;

import com.example.wsiwt_back.domain.user.UserEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Getter

// 작성자 정보를 사용할때만 쓰는 Dto
public class AuthorDto {

    @ApiModelProperty(value = "사용자의 username", example = "llssee123")
    private String username;
  //  @ApiModelProperty(value = "사용자의 id", example = "long")
  //  private Long id;
    @ApiModelProperty(value = "사용자의 profile 사진 url", example = "url")
    private String picture;

    public AuthorDto() {

    }

    public AuthorDto(UserEntity user) {
        this.username = user.getUsername();
    //    this.id = user.getId();
        this.picture = user.getPicture();

    }
}


