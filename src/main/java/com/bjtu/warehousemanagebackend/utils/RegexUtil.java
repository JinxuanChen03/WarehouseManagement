package com.bjtu.warehousemanagebackend.utils;

import com.bjtu.warehousemanagebackend.constants.Role;
import com.bjtu.warehousemanagebackend.exception.ServiceException;
import org.springframework.http.HttpStatus;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtil {

    public static final String USER_NAME_ROLE = "(.*)"+ "(" + Role.ROLE_USER.getValue()+"$)";

    public static final String ADMIN_NAME_ROLE = "(.*)"+ "(" + Role.ROLE_ADMIN.getValue()+"$)";

    public static Matcher matcherUser(String userNameRole){
        return Pattern.compile(USER_NAME_ROLE).matcher(userNameRole);
    }

    public static Matcher matcherAdmin(String userNameRole){
        return Pattern.compile(ADMIN_NAME_ROLE).matcher(userNameRole);
    }

    public static boolean matchUser(String userNameRole){
        return matcherUser(userNameRole).find();
    }

    public static boolean matchAdmin(String userNameRole){
        return matcherAdmin(userNameRole).find();
    }

    public static String parseUserName(String userNameRole){
        Matcher matcher1 = matcherUser(userNameRole);
        Matcher matcher2 = matcherAdmin(userNameRole);
        if(matcher1.find()){
            return matcher1.group(1);
        }else if(matcher2.find()){
            return matcher2.group(1);
        }else{
            throw new ServiceException(HttpStatus.FORBIDDEN.value(), "错误的后缀");
        }
    }
}
