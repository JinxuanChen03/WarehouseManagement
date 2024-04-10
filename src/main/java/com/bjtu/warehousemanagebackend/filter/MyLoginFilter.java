package com.bjtu.warehousemanagebackend.filter;

import com.bjtu.warehousemanagebackend.utils.MyRememberMeService;
import com.bjtu.warehousemanagebackend.utils.MyAuthenticationHandler;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.util.MimeTypeUtils;
import org.springframework.util.ObjectUtils;

import java.io.IOException;
import java.util.Map;

import static com.bjtu.warehousemanagebackend.utils.MyAuthenticationHandler.APPLICATION_JSON_CHARSET_UTF_8;

@Component
public class MyLoginFilter extends UsernamePasswordAuthenticationFilter {
    private final ObjectMapper objectMapper = new ObjectMapper();

    public MyLoginFilter(AuthenticationManager authenticationManager,
                         MyRememberMeService rememberMeServices) throws Exception {
        super(authenticationManager);
        //rememberMe
        setRememberMeServices(rememberMeServices);
        //登陆使用的路径
        setFilterProcessesUrl("/login");
    }

    private static boolean isContentTypeJson(HttpServletRequest request) {
        final String contentType = request.getContentType();
        return APPLICATION_JSON_CHARSET_UTF_8.equalsIgnoreCase(contentType) || MimeTypeUtils.APPLICATION_JSON_VALUE.equalsIgnoreCase(contentType);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        if (!HttpMethod.POST.name().equalsIgnoreCase(request.getMethod())) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }
        String username = null;
        String password = null;
        String rememberMe = null;
        if (isContentTypeJson(request)) {
            try {
                Map<String, String> map = objectMapper.readValue(request.getInputStream(), new TypeReference<>() { });
                username = map.get(getUsernameParameter());
                password = map.get(getPasswordParameter());
                rememberMe = map.get(MyRememberMeService.REMEMBER_ME_KEY);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            username = obtainUsername(request);
            password = obtainPassword(request);
            rememberMe = request.getParameter(MyRememberMeService.REMEMBER_ME_KEY);
        }

        //将 rememberMe 状态存入 attr中
        if (!ObjectUtils.isEmpty(rememberMe)) {
            request.setAttribute(MyRememberMeService.REMEMBER_ME_KEY, rememberMe);
        }
        username = (username != null) ? username.trim() : "";
        password = (password != null) ? password : "";
        UsernamePasswordAuthenticationToken authRequest = UsernamePasswordAuthenticationToken.unauthenticated(username, password);
        setDetails(request, authRequest);

        return this.getAuthenticationManager().authenticate(authRequest);
    }
}

