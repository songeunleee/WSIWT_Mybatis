package com.example.wsiwt_back.web;

import com.example.wsiwt_back.security.TokenProvider;
import com.example.wsiwt_back.service.UserService;
import com.example.wsiwt_back.web.dto.ResponseDto;
import com.example.wsiwt_back.web.dto.user.UserDto;
import com.example.wsiwt_back.domain.user.UserEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Api( tags = {"User Entity"})
@Tag(name = "User Entity", description = "user에 대한 로그인 과 회원가입")

@RequiredArgsConstructor
@Slf4j

@RestController
@RequestMapping("/auth")
public class userController {

    private final UserService userService;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private final TokenProvider tokenProvider;

   // @ApiOperation(value = "회원가입", response = UserDto.class)
    @ApiResponse(responseCode = "200", description = "회원가입 완료", content = @Content(schema = @Schema(implementation = UserDto.class)))
    @ApiResponse(responseCode = "409", description = "이미 있는  username")
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody UserDto userDto){
        try{
            if(userDto == null || userDto.getPassword() == null){
                throw new RuntimeException("Invalid Password value");
            }

            UserEntity user = UserEntity.builder()
                    .username(userDto.getUsername())
                    .picture(userDto.getPicture())
                    .password(passwordEncoder.encode(userDto.getPassword()))
                    .build();

            Long userId = userService.create(user);


            return ResponseEntity.ok().body(userId);
        }catch (Exception e){
            ResponseDto responseDto = ResponseDto.builder().error(e.getMessage()).build();

            return ResponseEntity.status(409).body(responseDto);
        }


    }
    @ApiOperation(value = "로그인", response = UserDto.class)
    @ApiResponse(responseCode = "200", description = "로그인 성공", content = @Content(schema = @Schema(implementation = UserDto.class)))
    @ApiResponse(responseCode = "404", description = "해당 회원이 없음")
    @PostMapping("/signin")
    public ResponseEntity<?> authenticate(@RequestBody UserDto userDTO) {
        UserEntity user = userService.getByCredentials(
                userDTO.getUsername(),
                userDTO.getPassword(),
                passwordEncoder);

        if(user != null) {
            // 토큰 생성
            final String token = tokenProvider.create(user);
            final UserDto responseUserDTO = UserDto.builder()
                    .username(user.getUsername())
                    .picture(user.getPicture())
                    .id(user.getId())
                    .token(token)
                    .build();
            return ResponseEntity.ok().body(responseUserDTO);
        } else {
            ResponseDto responseDTO = ResponseDto.builder()
                    .error("Login failed.")
                    .build();
            return ResponseEntity
                    .status(404).body(responseDTO);
        }
    }


}
