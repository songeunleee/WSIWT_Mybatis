package com.example.wsiwt_back.security;

import com.example.wsiwt_back.domain.user.UserEntity;
import com.example.wsiwt_back.domain.user.UserRepository;
import com.example.wsiwt_back.web.dto.user.UserDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Optional;

import static com.example.wsiwt_back.security.RedirectUrlCookieFilter.REDIRECT_URI_PARAM;

@Slf4j
@Component
@AllArgsConstructor


public class OAuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final UserRepository userRepository;
    private static final String LOCAL_REDIRECT_URL = "http://localhost:3000";

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {

        log.info("auth succeeded");
        TokenProvider tokenProvider = new TokenProvider();
        String token = tokenProvider.create(authentication);

        UserEntity user = userRepository.findById(Long.parseLong(authentication.getName())).orElseThrow(()->new IllegalArgumentException(" not exist : " + authentication.getName()));
        String picture = user.getPicture();
        String username = URLEncoder.encode(user.getUsername(),"UTF-8");

        Long id = user.getId();

        ObjectMapper objectMapper = new ObjectMapper();

        UserDto userDto = UserDto.builder().id(id).username(username).picture(picture).build();

        String userJson = objectMapper.writeValueAsString(userDto);

        Optional<Cookie> oCookie = Arrays.stream(request.getCookies()).filter(cookie -> cookie.getName().equals(REDIRECT_URI_PARAM)).findFirst();
        Optional<String> redirectUri = oCookie.map(Cookie::getValue);

        response.sendRedirect(redirectUri.orElseGet(() -> LOCAL_REDIRECT_URL) + "/sociallogin?token=" + token + "&user=" +userJson);

        log.info("token {}", token );
        log.info("username {}", username );
        log.info("picture {}", picture );

    }




}
