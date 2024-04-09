package com.bjtu.warehousemanagebackend.utils;

import com.bjtu.warehousemanagebackend.annotation.CurrentUser;
import com.bjtu.warehousemanagebackend.entity.User;
import com.bjtu.warehousemanagebackend.exception.ServiceException;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 *  增加方法注入，将含有 @CurrentUser 注解的方法参数注入当前登录用户
 */
public class CurrentUserMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().isAssignableFrom(User.class)
                && parameter.hasParameterAnnotation(CurrentUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        User user = (User) webRequest.getAttribute("CurrentUser", RequestAttributes.SCOPE_REQUEST);
//        System.out.println("!!!!!!CurrentUserResolver user: "+user);
        if (user == null)
            throw new ServiceException(HttpStatus.NO_CONTENT.value(), "CurrentUser为空");
        return user;
//        throw new MissingServletRequestPartException("CurrentUser为空");
    }
}

