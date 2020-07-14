package com.barrett.smartsite.factory;

/**
 * @program: smartsite
 * @description:
 * @author: Barrett
 * @create: 2019-10-07 11:01
 **/
public class StringUtil {
    public static String[] deConstructListFromString(String str) {
        return str.trim().split(";");
    }

    public String constructStringFromList(String[] strList) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strList.length; i++) {
            sb.append(strList[i]);
            if (i != strList.length - 1) {
                sb.append(";");
            }
        }
        return sb.toString();
    }
}
