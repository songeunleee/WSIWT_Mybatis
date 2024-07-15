package com.example.wsiwt_back.domain.user;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data

public class UserEntity {

    private Long id;

    private String username;

    private String password;

    private String role;

    private String picture;

    private String authProvider;


}
