package com.barrett.smartsite.service;


import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * @program: smartsite
 * @description:
 * @author: Barrett
 * @create: 2019-10-07 11:43
 **/
public interface FileUploadService {
    int saveFileToLocalFolder(MultipartFile paramMultipartFile, String paramString1, String paramString2);

    int deleteAllFilesFromLocalFolder(String paramString);

    int deleteFile(String filePath);
}
