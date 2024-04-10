package com.bjtu.warehousemanagebackend.config;

import com.bjtu.warehousemanagebackend.enums.PermissionEnum;
import com.bjtu.warehousemanagebackend.filter.JwtAuthenticationTokenFilter;
import com.bjtu.warehousemanagebackend.filter.MyLoginFilter;
import com.bjtu.warehousemanagebackend.service.Impl.UserDetailsServiceImpl;
import com.bjtu.warehousemanagebackend.utils.MyAuthenticationHandler;
import com.bjtu.warehousemanagebackend.utils.MyRememberMeService;
import lombok.RequiredArgsConstructor;
import org.apache.velocity.runtime.directive.contrib.For;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
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
import java.util.List;

import static com.bjtu.warehousemanagebackend.enums.PermissionEnum.*;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class MySecurityConfig {

    /**
     * 接口文档放行
     */
    public static final List<String> ALL_WHITE_LIST =
            List.of("/login","/logout");

    public static final List<String> CUSTOMER_WHITE_LIST =
            List.of("/customer/**");

    public static final List<String> PROVIDER_WHITE_LIST =
            List.of("/provider/**");

    public static final List<String> ADMIN_WHITE_LIST =
            List.of("/admin/**");

    /**
     * 获取AuthenticationManager（认证管理器），登录时认证使用
     */
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /**
     * 允许抛出用户不存在的异常
     * @param userDetailsService myUserDetailsService
     * @return DaoAuthenticationProvider
     */
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(UserDetailsServiceImpl userDetailsService) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setUserDetailsPasswordService(userDetailsService);
        provider.setHideUserNotFoundExceptions(false);
        return provider;
    }

    /**
     * 自定义RememberMe服务token持久化仓库
     */
    @Bean
    public PersistentTokenRepository persistentTokenRepository(DataSource datasource) {
        final JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        //设置数据源
        tokenRepository.setDataSource(datasource);
        //第一次启动的时候建表
//        tokenRepository.setCreateTableOnStartup(true);
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
                        //允许匿名访问
                        .requestMatchers(HttpMethod.GET, ALL_WHITE_LIST.toArray(new String[0]))
                        .permitAll()

                        //允许相应权限访问
                        .requestMatchers(HttpMethod.GET,CUSTOMER_WHITE_LIST.toArray(new String[0]))
                        .hasAuthority(ROLE_CUSTOMER.toString())

                        .requestMatchers(HttpMethod.GET,PROVIDER_WHITE_LIST.toArray(new String[0]))
                        .hasAuthority(ROLE_PROVIDER.toString())

                        .requestMatchers(HttpMethod.GET,ADMIN_WHITE_LIST.toArray(new String[0]))
                        .hasAuthority(ROLE_ADMIN.toString())

                        .anyRequest()
                        .authenticated())
                .formLogin(form -> form.loginProcessingUrl("/login")
                        .usernameParameter("name")
                        .passwordParameter("password"))
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
                //csrf验证 存储到Cookie中
                .csrf(csrf -> csrf
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                        .csrfTokenRequestHandler(new CsrfTokenRequestAttributeHandler()))
                .rememberMe(re -> re
                        .rememberMeServices(rememberMeServices))
                //允许跨域
                .cors(cors -> cors.configure(http));

        return http.build();
    }
}
