package com.bjtu.warehousemanagebackend.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CronUtil {

    public static String formatDateByPattern(Date date, String dateFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        String formatTimeStr = null;
        if (date != null) {
            formatTimeStr = sdf.format(date);
        }
        return formatTimeStr;
    }

    //convert Date to cron ,eg. "21 25 17 07 01 ? 2020"
    public static String dateToCron(java.util.Date date) {
//        String dateFormat = "ss mm HH dd MM ? yyyy";
        String dateFormat = "ss mm HH dd MM ?";
        return formatDateByPattern(date, dateFormat);
    }
}