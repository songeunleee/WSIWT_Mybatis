package com.example.wsiwt_back.config;

import com.example.wsiwt_back.security.JwtAuthenticationFilter;
import com.example.wsiwt_back.security.OAuthSuccessHandler;
import com.example.wsiwt_back.security.OAuthUserServiceImpl;
import com.example.wsiwt_back.security.RedirectUrlCookieFilter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestRedirectFilter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.web.filter.CorsFilter;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
@Slf4j
public class WebSecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    private final OAuthUserServiceImpl oAuthUserService;

    private final OAuthSuccessHandler oAuthSuccessHandler;

    private final RedirectUrlCookieFilter redirectUrlFilter;
   // @Bean
   // public WebSecurityCustomizer configure(){
       // return (web) -> web.ignoring()

              // .requestMatchers(PathRequest.toH2Console())
              //  .antMatchers(String.valueOf(PathRequest.toH2Console())).antMatchers("/static/**");

   // }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{


         http
                .cors()
                .and()
                 .csrf().disable()
                .httpBasic()
                    .disable()
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()

                 .authorizeHttpRequests(authorize -> authorize

                         .antMatchers("/","/css/**","/images/**","/js/**","/profile","/auth/**","/ootds/**","/oauth2/**","/h2-console/**",
                                 "/v3/api-docs/**", "/swagger-ui/**", "/swagger-resources/**","/v3/**","/open-api/**").permitAll()

                         .anyRequest().authenticated())
                 .oauth2Login()
                 .redirectionEndpoint()
                 .baseUri("/oauth2/callback/*")
                 .and()
                 .authorizationEndpoint()
                 .baseUri("/auth/authorize")
                 .and()
                 .userInfoEndpoint()
                 .userService(oAuthUserService)
                 .and()
                 .successHandler(oAuthSuccessHandler)
                 .and()
                 .exceptionHandling()
                    .authenticationEntryPoint(new Http403ForbiddenEntryPoint());


         http.addFilterAfter(jwtAuthenticationFilter, CorsFilter.class);
         http.addFilterBefore(redirectUrlFilter, OAuth2AuthorizationRequestRedirectFilter.class);


        return http.build();
    }
}
