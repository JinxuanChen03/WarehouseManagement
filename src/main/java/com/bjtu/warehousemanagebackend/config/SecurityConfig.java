package com.bjtu.warehousemanagebackend.config;

import com.bjtu.warehousemanagebackend.filter.JwtAuthenticationTokenFilter;
import com.bjtu.warehousemanagebackend.filter.MyLoginFilter;
import com.bjtu.warehousemanagebackend.utils.MyAuthenticationHandler;
import com.bjtu.warehousemanagebackend.utils.MyRememberMeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity //注解代替继承
@RequiredArgsConstructor
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Autowired
    private MyAuthenticationHandler myAuthenticationHandler;

    /**
     * 获取AuthenticationManager（认证管理器），登录时认证使用
     */
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /**
     * 自定义RememberMe服务token持久化仓库
     */
    @Bean
    public PersistentTokenRepository persistentTokenRepository(DataSource datasource) {
        final JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        //设置数据源
        tokenRepository.setDataSource(datasource);
        return tokenRepository;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http,
                                            MyLoginFilter loginFilter,
                                            MyAuthenticationHandler authenticationHandler,
                                            MyRememberMeService rememberMeServices) throws Exception {
        //路径配置
        http
                .authorizeHttpRequests(auth -> auth
                        // 对于登录接口 允许匿名访问
                        .requestMatchers("/user/*").permitAll()
                        // 除上面外的所有请求全部需要鉴权认证
                        .anyRequest().authenticated())
                .formLogin(form -> form.loginProcessingUrl("/login")
                        .usernameParameter("name")
                        .passwordParameter("password"))

                //csrf验证 存储到Cookie中
                .csrf(csrf -> csrf
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                        .csrfTokenRequestHandler(new CsrfTokenRequestAttributeHandler()))
                .rememberMe(re -> re
                        .rememberMeServices(rememberMeServices))

                //会话管理
                .sessionManagement(session -> session
                        .maximumSessions(1)
                        //禁止登陆后挤下线
                        .maxSessionsPreventsLogin(true)
                        .expiredSessionStrategy(authenticationHandler))

                //把token校验过滤器添加到过滤器链中
                .addFilterBefore(loginFilter,UsernamePasswordAuthenticationFilter.class)

                //配置异常处理器
                .exceptionHandling(exception -> exception
                        .accessDeniedHandler(authenticationHandler)
                        .authenticationEntryPoint(authenticationHandler))

                //允许跨域
                .cors(cors -> cors.configure(http));

        return http.build();
    }
}
