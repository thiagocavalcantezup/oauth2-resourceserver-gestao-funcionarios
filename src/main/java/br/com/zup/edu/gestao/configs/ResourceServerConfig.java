package br.com.zup.edu.gestao.configs;

import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
public class ResourceServerConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // @formatter:off
        http.cors()
            .and()
                .csrf().disable()
                .httpBasic().disable()
                .rememberMe().disable()
                .formLogin().disable()
                .logout().disable()
                .requestCache().disable()
                .headers().frameOptions().deny()
            .and()
                .sessionManagement().sessionCreationPolicy(STATELESS)
            .and()
                .authorizeRequests()
                .antMatchers(GET, "/api/funcionarios").hasAuthority("SCOPE_funcionarios:read")
                .antMatchers(GET, "/api/funcionarios/**").hasAuthority("SCOPE_funcionarios:read")
                .antMatchers(POST, "/api/funcionarios").hasAuthority("SCOPE_funcionarios:write")
                .antMatchers(DELETE, "/api/funcionarios/**").hasAuthority("SCOPE_funcionarios:write")
                .anyRequest().authenticated()
            .and()
                .oauth2ResourceServer().jwt();
        // @formatter:on

        return http.build();
    }

}
