package com.barrett.facedetectservice.util;

import java.util.UUID;

/**
 * @program: smartsite-master
 * @description:
 * @author: Barrett
 * @create: 2020-05-11 10:39
 **/
public class UniqueIDGenerator {
    public static String getUUIDWithDash() {
        String result = UUID.randomUUID().toString();
        return result;
    }

    public static String getUUIDWithoutDash() {
        String result = UUID.randomUUID().toString();
        result = result.replace("-", "");
        return result;
    }

    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static String getSuffix(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }

    public static String getFileName(String fileOriginName) {
        return getUUID() + getSuffix(fileOriginName);
    }

    // Get filename form URL path link:
    public static String getFileNameFromUrlLink(String urlLink) {
        return urlLink.substring(urlLink.lastIndexOf("/"));
    }
}
