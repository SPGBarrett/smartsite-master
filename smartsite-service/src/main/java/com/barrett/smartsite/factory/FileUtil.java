package com.barrett.smartsite.factory;
import com.barrett.smartsite.bean.MultiFiles;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.barrett.smartsite.service.FileUploadService;
import com.barrett.smartsite.service.MultiFilesService;
import com.barrett.smartsite.service.WelcomeScreenSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @program: smartsite
 * @description:
 * @author: Barrett
 * @create: 2019-10-07 11:00
 **/
@Service
public class FileUtil {

    @Autowired
    FileUploadService fileUploadService;
    @Autowired
    MultiFilesService multiFilesService;
    @Autowired
    WelcomeScreenSettingService welcomeScreenSettingService;
    @Value("${web.upload-path}")
    private String path;
    @Value("${multimedia.link}")
    private String defaultUrl;
    @Value("${ffmpeg.path}")
    String ffmpegPath;
    @Value("${ffmpeg.target.path}")
    String targetPath;
    String videoLength = "10";
    // Append path:
    private final String appendUrl = "/external/";

    /**
    * @Description:
    * @Param:
    * @return:
    * @Author: Barrett
    * @Date:
    */
    private MultiFiles constructMultiFileFromMultipart(MultipartFile file, String fileParent, String nameForSave) {
        MultiFiles result = new MultiFiles();
        if (file == null) return null;
        result.setId(0);
        result.setParent(fileParent);
        result.setSuffix(UniqueIDUtil.getSuffix(file.getOriginalFilename()));
        result.setType(UniqueIDUtil.getFileType(file.getOriginalFilename()));
        // Set data time format:
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        df.format(date);
        result.setTime(date.toString());
        String urlLink = defaultUrl + appendUrl + nameForSave;
        result.setUrl_link(urlLink);
        result.setPath(this.path);
        // Return result:
        return result;
    }

    /**
    * @Description:  Method that implement uploading files and update database:
    * @Param:
    * @return:
    * @Author: Barrett
    * @Date:
    */
    public String uploadFile(MultipartFile file, String fileParent) {
        // Get file name for save:
        String fileNameForSave = UniqueIDUtil.getFileName(file.getOriginalFilename());
        // Save file to upload files folder:
        int saveResult = this.fileUploadService.saveFileToLocalFolder(file, this.path, fileNameForSave);
        String result = "";
        if (saveResult == 1) {
            result = result + "File:" + file.getOriginalFilename() + " has been saved successfully as:";
            result = result + fileNameForSave;
        } else {
            result = "Save file failed!";
        }
        // Save file information to database:
        MultiFiles tmpFiles = constructMultiFileFromMultipart(file, fileParent, fileNameForSave);
        this.multiFilesService.insert(tmpFiles);
        // Update welcome screen setting:
        if(fileParent.equals("欢迎界面")){
            String welcomeUrl = defaultUrl + appendUrl + fileNameForSave;
            welcomeScreenSettingService.updatePicPath(1, welcomeUrl);
        }
        return result;
    }

    /**
    * @Description:  Convert MP4 file to m3u8:
    * @Param:
    * @return:
    * @Author: Barrett
    * @Date:
    */
    public String convertFileToM3U8(String sourcePath, String targetPath){
        String strOutput = "";
        //Construct cmd line:
        List<String> command = new ArrayList<>();
        command.add(ffmpegPath);
        command.add("-i");
        command.add(sourcePath);
        command.add("-c:v");
        command.add("libx264");
        command.add("-hls_time");
        command.add(videoLength); //Time for each ts file
        command.add("-hls_list_size");
        command.add("0");
        command.add("-c:a");
        command.add("aac");
        command.add("-strict");
        command.add("-2");
        command.add("-f");
        command.add("hls");
        command.add(targetPath);
        // Use Process to utilize sys CMD:
        ProcessBuilder builder = new ProcessBuilder();
        builder.command(command);
        // Output information:
        builder.redirectErrorStream(true);
        try {
            // execute command
            Process process = builder.start();
            // get info:
            StringBuffer sbf = new StringBuffer();
            String line = null;
            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
            while ((line = br.readLine()) != null) {
                sbf.append(line);
                sbf.append(" ");
            }
            String resultInfo = sbf.toString();
            // Print info:
            System.out.println(resultInfo);
            strOutput = resultInfo;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return strOutput;
    }

    /**
    * @Description: Async method that convert files and save data to database
    * @Param:
    * @return:
    * @Author: Barrett
    * @Date:
    */
    public String convertFilesAsync(){
        String strResult = "Start Converting!";
        // Start new thread to do job:
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                // Get all multi files:
                List<MultiFiles> fileList = multiFilesService.getAll();
                // Loop all files and convert:
                for(MultiFiles file : fileList){
                    if(file.getType().equals("VIDEO")){
                        //Get raw file name:
                        String orgLink = file.getUrl_link();
                        String fileNameRaw = UniqueIDUtil.getFileNameFromUrlLink(orgLink);
                        String targetFileName = path + UniqueIDUtil.changeSuffix(fileNameRaw);
                        String sourceFileName = path + fileNameRaw;
                        convertFileToM3U8(sourceFileName, targetFileName);
                        file.setUrl_link(UniqueIDUtil.changeSuffix(orgLink));
                        // Update database:
                        multiFilesService.update(file);
                    }
                }
            }
        });
        // Start thread:
        thread.start();
        return strResult;
    }
}
