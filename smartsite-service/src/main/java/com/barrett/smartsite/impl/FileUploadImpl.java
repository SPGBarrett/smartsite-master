package com.barrett.smartsite.impl;

import com.barrett.smartsite.impl.FileUploadImpl;
import com.barrett.smartsite.service.FileUploadService;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @program: smartsite
 * @description:
 * @author: Barrett
 * @create: 2019-10-07 11:10
 **/
@Service
public class FileUploadImpl
        implements FileUploadService {
    public int saveFileToLocalFolder(MultipartFile file, String path, String fileName) {
        String realPath = path + "/" + fileName;
        File destination = new File(realPath);

        if (!destination.getParentFile().exists()) {
            destination.getParentFile().mkdir();
        }

        try {
            file.transferTo(destination);
            return 1;
        } catch (IllegalStateException e) {
            e.printStackTrace();
            return 0;
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }


    public int deleteAllFilesFromLocalFolder(String path) {
        File directory = new File(path);
        try {
            removeDirectory(directory);
            return 1;
        } catch (Exception e) {
            System.out.println(e);
            return 0;
        }
    }

    public int deleteFile(String filePath) {
        File thisFile = new File(filePath);
        try{
            thisFile.delete();
            return 1;
        }catch (Exception e){
            System.out.println(e);
            return 0;
        }
    }


    public int removeDirectory(File directory) {
        File[] files = directory.listFiles();

        for (File file : files) {
            if (file.isDirectory()) {
                removeDirectory(file);
            } else {
                file.delete();
            }
        }
        return 1;
    }
}
