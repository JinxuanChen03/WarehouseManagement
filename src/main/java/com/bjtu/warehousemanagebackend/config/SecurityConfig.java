package com.bjtu.warehousemanagebackend.config;

import com.bjtu.warehousemanagebackend.filter.JwtAuthenticationTokenFilter;
import com.bjtu.warehousemanagebackend.handler.AuthenticationHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity //注解代替继承
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    /**
     * 获取AuthenticationManager（认证管理器），登录时认证使用
     */
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http,
                                           JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter,
                                           AuthenticationHandler authenticationHandler) throws Exception{
        http
                .authorizeHttpRequests(auth -> auth
                        //对于登录接口 允许匿名访问
                        .requestMatchers("/login","/signup","/admin/login").permitAll()
                        //除上面外的所有请求全部需要鉴权认证
                        .anyRequest().authenticated())

                //关闭csrf
                .csrf(AbstractHttpConfigurer::disable)

                //把token校验过滤器添加到过滤器链中
                .addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class)

                //配置异常处理器
                .exceptionHandling(exception -> exception
                        //配置认证失败处理器
                        .accessDeniedHandler(authenticationHandler)
                        .authenticationEntryPoint(authenticationHandler))
                //允许跨域
                .cors(AbstractHttpConfigurer::disable);

        return http.build();
    }

}

