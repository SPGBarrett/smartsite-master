package com.barrett.smartsite.factory;

import java.io.File;
import java.io.IOException;

import org.aspectj.util.FileUtil;

/**
 * @program: smartsite
 * @description:
 * @author: Barrett
 * @create: 2019-10-07 11:00
 **/
public class DBDataInitUtil {
    public int copyMultiMediaFile(File file, String destination) {
        String dest = destination + "/" + file.getName();
        File toFile = new File(dest);
        try {
            FileUtil.copyFile(file, toFile);
            return 1;
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
