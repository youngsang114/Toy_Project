package org.delivery.storeadmin.config.security;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

@Configuration
@EnableWebSecurity  // security 활성화
public class SecurityConfig {

    private List<String> SWAGGER = List.of(
            "/swagger-ui.html",
            "/swagger-ui/**",
            "/v3/api-docs/**"
    );

    // http security 설정
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeHttpRequests(it -> {
                    it
                            // resource 에 대해서는 모든 요청 허용
                            .requestMatchers(
                                    PathRequest.toStaticResources().atCommonLocations()
                            ).permitAll()
                            // open-api /  하위 모든 주소는 인증 없이 통과
                            .requestMatchers(
                                    "/open-api/**"
                            ).permitAll()
                            // 스웨거는 인증 없이 통과
                            .requestMatchers(
                                    SWAGGER.toArray(new String[0])
                            ).permitAll()

                            // 그외 모든 요청은 인증 사용
                            .anyRequest().authenticated();
                })
                .formLogin(Customizer.withDefaults())
        ;

        return httpSecurity.build();
    }
}
