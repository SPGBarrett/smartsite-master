package com.barrett.facedetectservice.error;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @program: smartsite-master
 * @description: Logger service
 * @author: Barrett
 * @create: 2020-07-10 08:53
 **/
@Service
public class LoggerService {

    @Value("${face.detect.log.folder}")
    public String LOG_FILE_PATH;

    private final String LOG_FILE_NAME = "FaceDetectAPILog.txt";

    private final String INFO_LOG_NAME = "info_log.txt";

    /**
     * @Description: Write log info to file
     * @Param:
     * @return:
     * @Author: Barrett
     * @Date:
     */
    public void writeLogFile(String methodName, String inputMsg, String responseMsg) {
        try {
            Date currentDate = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String currentDateInDate = sdf.format(currentDate);
            String folderPath = LOG_FILE_PATH + currentDateInDate;
            String fileName = folderPath + "\\" + LOG_FILE_NAME;
            File folder = new File(folderPath);
            File file = new File(fileName);
            FileOutputStream fos = null;
            // Check folder exist:
            if (!folder.exists()) {
                folder.mkdirs();
            }
            // Check file:
            if (!file.exists()) {
                file.createNewFile();
                fos = new FileOutputStream(file);
            } else {
                fos = new FileOutputStream(file, true);
            }
            OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
            sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String currentDateInSec = sdf.format(currentDate);
            StringBuilder sb = new StringBuilder();
            sb.append(currentDateInSec + " " + methodName + "\n");
            sb.append("Input Message:" + "\n");
            sb.append(inputMsg + "\n");
            sb.append("Response Message:" + "\n");
            sb.append(responseMsg + "\n");
            osw.write(sb.toString() + "\n");
            osw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @Description: Write log info to file
     * @Param:
     * @return:
     * @Author: Barrett
     * @Date:
     */
    public void writeInfoLogs(String logPath, String msg){
        try {
            Date currentDate = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String currentDateInDate = sdf.format(currentDate);
            String folderPath = logPath + currentDateInDate;
            String fileName = folderPath + "\\" + INFO_LOG_NAME;
            File folder = new File(folderPath);
            File file = new File(fileName);
            FileOutputStream fos = null;
            // Check folder exist:
            if (!folder.exists()) {
                folder.mkdirs();
            }
            // Check file:
            if (!file.exists()) {
                file.createNewFile();
                fos = new FileOutputStream(file);
            } else {
                fos = new FileOutputStream(file, true);
            }
            OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
            sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String currentDateInSec = sdf.format(currentDate);
            StringBuilder sb = new StringBuilder();
            sb.append(currentDateInSec + " InfoLog:" +  "\n");
            sb.append(msg);
            sb.append("\n");
            osw.write(sb.toString() + "\n");
            osw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
