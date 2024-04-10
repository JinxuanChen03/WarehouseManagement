package com.bjtu.warehousemanagebackend.utils;

import com.bjtu.warehousemanagebackend.service.impl.UserDetailsServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class MyRememberMeService extends PersistentTokenBasedRememberMeServices {
    public static final String REMEMBER_ME_KEY = "rememberMe";
    public static final List<String> TRUE_VALUES = List.of("true", "yes", "on", "1");

    public MyRememberMeService(UserDetailsServiceImpl userDetailsService, PersistentTokenRepository tokenRepository) {
        super(UUID.randomUUID().toString(), userDetailsService, tokenRepository);
    }

    @Override
    protected boolean rememberMeRequested(HttpServletRequest request, String parameter) {
        final String rememberMe = (String) request.getAttribute(REMEMBER_ME_KEY);
        if (rememberMe != null) {
            for (String trueValue : TRUE_VALUES) {
                if (trueValue.equalsIgnoreCase(rememberMe)) {
                    return true;
                }
            }
        }
        return super.rememberMeRequested(request, parameter);
    }
}

