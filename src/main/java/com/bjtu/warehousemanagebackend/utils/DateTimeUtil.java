package com.bjtu.warehousemanagebackend.utils;

import java.text.SimpleDateFormat;

public class DateTimeUtil {
    public static String getNowTimeString() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(System.currentTimeMillis());
    }

}
