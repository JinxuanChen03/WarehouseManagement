package com.bjtu.warehousemanagebackend.filter;

import com.alibaba.fastjson.TypeReference;
import com.bjtu.warehousemanagebackend.service.Impl.RememberMeServiceImpl;
import com.bjtu.warehousemanagebackend.utils.MyAuthenticationHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
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
                         MyAuthenticationHandler authenticationHandler,
                         RememberMeServiceImpl rememberMeServices) throws Exception {
        super(authenticationManager);
        setAuthenticationFailureHandler(authenticationHandler);
        setAuthenticationSuccessHandler(authenticationHandler);
        //rememberMe
        setRememberMeServices(rememberMeServices);
        //登陆使用的路径
        setFilterProcessesUrl("/sys/user/login");
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
        String verifyCode = null;
        String rememberMe = null;
        if (isContentTypeJson(request)) {
            try {
                Map<String, String> map = objectMapper.readValue(request.getInputStream(), new TypeReference<>() { });
                username = map.get(getUsernameParameter());
                password = map.get(getPasswordParameter());
                verifyCode = map.get(VERIFY_CODE_KEY);
                rememberMe = map.get(RememberMeServiceImpl.REMEMBER_ME_KEY);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            username = obtainUsername(request);
            password = obtainPassword(request);
            verifyCode = request.getParameter(VERIFY_CODE_KEY);
            rememberMe = request.getParameter(RememberMeServiceImpl.REMEMBER_ME_KEY);
        }
        //校验验证码
        final String vc = (String) request.getSession().getAttribute(VERIFY_CODE_KEY);
        if (vc == null) {
            throw new BadCredentialsException("验证码不存在,请先获取验证码");
        } else if (verifyCode == null || "".equals(verifyCode)) {
            throw new BadCredentialsException("请输入验证码");
        } else if (!vc.equalsIgnoreCase(verifyCode)) {
            throw new BadCredentialsException("验证码错误");
        }

        //将 rememberMe 状态存入 attr中
        if (!ObjectUtils.isEmpty(rememberMe)) {
            request.setAttribute(RememberMeServiceImpl.REMEMBER_ME_KEY, rememberMe);
        }

        username = (username != null) ? username.trim() : "";
        password = (password != null) ? password : "";
        UsernamePasswordAuthenticationToken authRequest = UsernamePasswordAuthenticationToken.unauthenticated(username, password);
        // Allow subclasses to set the "details" property
        setDetails(request, authRequest);

        return this.getAuthenticationManager().authenticate(authRequest);
    }
}

