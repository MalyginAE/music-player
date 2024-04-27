package com.hse.nn.musicplayerdictionary.config;

import com.hse.nn.musicplayerdictionary.service.CustomRedirectStrategy;
import com.hse.nn.musicplayerdictionary.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.cglib.proxy.Proxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.client.endpoint.DefaultJwtBearerTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.JwtBearerGrantRequest;
import org.springframework.security.oauth2.client.endpoint.OAuth2AccessTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AuthorizationCodeGrantRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.client.web.AuthorizationRequestRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizationRequestResolver;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestResolver;
import org.springframework.security.oauth2.core.endpoint.OAuth2AccessTokenResponse;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.web.SecurityFilterChain;

import java.lang.reflect.Method;
import java.util.Set;
import java.util.function.Consumer;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final UserService userService;
    @Autowired
    private ClientRegistrationRepository clientRegistrationRepository;

    @Bean
    @Order(SecurityProperties.BASIC_AUTH_ORDER)
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests.requestMatchers("/hse/api/v1/music-player-dictionary/music/**").hasAnyRole("ADMIN", "USER")
                        .requestMatchers("/oauth/**").permitAll()
                        .anyRequest().authenticated())
                .oauth2Login(it -> it
                        .authorizationEndpoint(authorizationEndpointConfig -> authorizationEndpointConfig
                                .authorizationRedirectStrategy(new CustomRedirectStrategy())
//                                .authorizationRequestResolver(authorizationRequestResolver())
                        )

                        .defaultSuccessUrl("/hse/api/v1/music-player-dictionary/music/popular")
                        .userInfoEndpoint(userInfo -> userInfo.oidcUserService(buildOIDC()))


                );
//        http.httpBasic(withDefaults());
        return http.build();
    }

//    private OAuth2AuthorizationRequestResolver authorizationRequestResolver(
//            ClientRegistrationRepository clientRegistrationRepository) {
//
//        DefaultOAuth2AuthorizationRequestResolver authorizationRequestResolver =
//                new DefaultOAuth2AuthorizationRequestResolver(
//                        clientRegistrationRepository, "/oauth2/authorization");
//        authorizationRequestResolver.setAuthorizationRequestCustomizer(
//                authorizationRequestCustomizer());
//
//        return authorizationRequestResolver;
//    }

//    private Consumer<OAuth2AuthorizationRequest.Builder> authorizationRequestCustomizer() {
//
//    }
//    .authorizationEndpoint(authorization -> authorization
//            .authorizationRequestResolver(
//    authorizationRequestResolver(this.clientRegistrationRepository)
//					)
//                            )

//    .authorizationCodeGrant(codeGrant -> codeGrant
//            .authorizationRequestRepository(this.authorizationRequestRepository())
//            ...
//            )
    private OAuth2UserService<OidcUserRequest, OidcUser> buildOIDC() {
//        final OidcUserService delegate = new OidcUserService();
        return userRequest ->
        {
            String email = userRequest.getIdToken().getClaim("email");
            UserDetails userDetails = getUserDetails(email);
//            OidcUser loadedUser = delegate.loadUser(userRequest);

            DefaultOidcUser oidcUser = new DefaultOidcUser(userDetails.getAuthorities(),
                    userRequest.getIdToken());
            Set<Method> usDetMeth = Set.of(UserDetails.class.getMethods());

//            UserDetails finalUserDetails = userDetails;
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
