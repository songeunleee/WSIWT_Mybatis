package com.example.wsiwt_back.security;

import com.example.wsiwt_back.domain.user.UserEntity;
import com.example.wsiwt_back.domain.user.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;


@Slf4j
@Service

public class OAuthUserServiceImpl extends DefaultOAuth2UserService {


    @Autowired
    private UserRepository userRepository;

    public OAuthUserServiceImpl(UserRepository userRepository) {
        super();

    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        final OAuth2User oAuth2User = super.loadUser(userRequest);

//        try {
//            log.info("OAuth2User attributes {}", new ObjectMapper().writeValueAsString(oAuth2User.getAttributes()));
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
        //디버깅을 돕기위해 사용자 정보가 어떻게 되는지 로깅함. 테스팅 시에만 사용
        final String authProvider = userRequest.getClientRegistration().getClientName();
        String username;
        String picture;

        if(authProvider.equals("Google")){
            username = (String) oAuth2User.getAttributes().get("name");
            picture = (String)  oAuth2User.getAttributes().get("picture");
        }else{
            username = (String) oAuth2User.getAttributes().get("login");
            picture = (String) oAuth2User.getAttributes().get("avatar_url");

        }
        UserEntity userEntity = null;

        if (!userRepository.existsByUsername(username)) {
            userEntity = UserEntity.builder()
                    .username(username)
                    .picture(picture)
                    .authProvider(authProvider)
                    .build();
            userRepository.save(userEntity);
        } else{
            userEntity = userRepository.findByUsername(username);
        }
        log.info("Successfully pulled user info username {} authProvider {}",
                username,
                authProvider);
        return new ApplicationOAuth2User(userEntity.getId(),oAuth2User.getAttributes());
    }

}



