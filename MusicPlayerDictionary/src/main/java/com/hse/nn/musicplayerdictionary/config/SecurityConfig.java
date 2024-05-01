package com.hse.nn.musicplayerdictionary.config;

import com.hse.nn.musicplayerdictionary.service.CustomRedirectStrategy;
import com.hse.nn.musicplayerdictionary.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.cglib.proxy.Proxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.web.SecurityFilterChain;

import java.lang.reflect.Method;
import java.util.Set;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final UserService userService;


    @Bean
    @Order(SecurityProperties.BASIC_AUTH_ORDER)
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((requests) -> requests.requestMatchers("/hse/api/v1/music-player-dictionary/**").hasAnyRole("ADMIN", "USER")
                        .requestMatchers("/oauth/**").permitAll()
                        .anyRequest().authenticated())
                .oauth2Login(it -> it
                        .authorizationEndpoint(authorizationEndpointConfig -> authorizationEndpointConfig
                                .authorizationRedirectStrategy(new CustomRedirectStrategy())
                        )
                        .defaultSuccessUrl("/hse/api/v1/music-player-dictionary/music/popular")
                        .userInfoEndpoint(userInfo -> userInfo.oidcUserService(buildOIDC()))
                );
        http.httpBasic(withDefaults());
        return http.build();
    }

    private OAuth2UserService<OidcUserRequest, OidcUser> buildOIDC() {
        return userRequest ->
        {
            String email = userRequest.getIdToken().getClaim("email");
            UserDetails userDetails = getUserDetails(email);

            DefaultOidcUser oidcUser = new DefaultOidcUser(userDetails.getAuthorities(),
                    userRequest.getIdToken());
            Set<Method> usDetMeth = Set.of(UserDetails.class.getMethods());

            return (OidcUser) Proxy.newProxyInstance(SecurityConfig.class.getClassLoader(), new Class[]{
                    UserDetails.class, OidcUser.class
            }, (proxy, method, args) -> usDetMeth.contains(method) ? method.invoke(userDetails, args) : method.invoke(oidcUser, args));
        };
    }

    private UserDetails getUserDetails(String email) {
        UserDetails userDetails;
        try {
            return userService.loadUserByUsername(email);
        } catch (UsernameNotFoundException e) {
            userDetails = userService.create(email);
        }
        return userDetails;
    }
}
