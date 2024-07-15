package com.example.wsiwt_back.web.dto.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class UserDto {
    @ApiModelProperty(value = "사용자의 token", example = "string")
    private String token;
    @ApiModelProperty(value = "사용자의 username", example = "llssee123")
    private String username;
    @ApiModelProperty(value = "사용자의 password", example = "항상 null값으로 노출되지 않음")
    private String password;
    @ApiModelProperty(value = "사용자의 id", example = "long")
    private Long id;
    @ApiModelProperty(value = "사용자의 profile 사진 주소", example = "url")
    private String picture;
}
