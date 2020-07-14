package com.barrett.smartsite.factory;

import java.util.UUID;

/**
 * @program: smartsite
 * @description:
 * @author: Barrett
 * @create: 2019-10-07 11:03
 **/
public class UniqueIDUtil {
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static String getSuffix(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }

    public static String getFileName(String fileOriginName) {
        return getUUID() + getSuffix(fileOriginName);
    }

    // Change multi file suffix to M3U8:
    public static String changeSuffix(String fileName){
        //Get file name without suffix:
        String fileNameRaw = fileName.substring(0, fileName.lastIndexOf("."));
        return fileNameRaw + ".m3u8";
    }

    // Get filename form URL path link:
    public static String getFileNameFromUrlLink(String urlLink){
        return urlLink.substring(urlLink.lastIndexOf("/"));
    }

    // Get file type based on suffix of files:
    public static String getFileType(String fileOriginName) {
        if (fileOriginName.endsWith(".jpg") || fileOriginName
                .endsWith(".gif") || fileOriginName
                .endsWith(".jp2") || fileOriginName
                .endsWith(".jpeg") || fileOriginName
                .endsWith(".png") || fileOriginName
                .endsWith(".tif") || fileOriginName
                .endsWith(".tiff") || fileOriginName
                .endsWith(".JPG") || fileOriginName
                .endsWith(".JP2") || fileOriginName
                .endsWith(".JPEG") || fileOriginName
                .endsWith(".PNG") || fileOriginName
                .endsWith(".TIF") || fileOriginName
                .endsWith(".TIFF") || fileOriginName
                .endsWith(".GIF"))
            return "IMAGE";
        if (fileOriginName.endsWith(".mp4") || fileOriginName
                .endsWith(".mpeg") || fileOriginName
                .endsWith(".mov") || fileOriginName
                .endsWith(".rmvb") || fileOriginName
                .endsWith(".MP4") || fileOriginName
                .endsWith(".MPEG") || fileOriginName
                .endsWith(".RMVB") || fileOriginName
                .endsWith(".MOV")) {
            return "VIDEO";
        }
        return "NA";
    }
}
