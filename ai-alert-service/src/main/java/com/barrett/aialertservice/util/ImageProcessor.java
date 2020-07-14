package com.barrett.aialertservice.util;

import com.barrett.aialertservice.bean.ClothesInfo;
import com.barrett.aialertservice.bean.HeadInfo;
import com.barrett.aialertservice.bean.VestInfo;
import org.apache.commons.codec.binary.Base64;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.List;

/**
 * @program: smartsite-master
 * @description: Image processing
 * @author: Barrett
 * @create: 2020-05-06 11:19
 **/
public class ImageProcessor {
    public String drawRectInImgFromBase64ForHelmet(String base64Img, List<HeadInfo> boxList){
        String base64Result = "";
        // Decode base64 String to byte buffer:
        // Get rid of original base64 string head:
        String base64Head = base64Img.split(",")[0];
        base64Img = base64Img.split(",")[1];
        byte[] buffer = new Base64().decode(base64Img);
        // Convert byte buffer to stream:
        ByteArrayInputStream bai = new ByteArrayInputStream(buffer);
        // Transform stream to image and process image:
        try {
            BufferedImage image = ImageIO.read(bai);
            Graphics graph = image.getGraphics();
            graph.setColor(Color.RED);// Paint color
            // Draw rectangle:
            for(HeadInfo thisInfo : boxList){
                graph.drawRect(thisInfo.getX(), thisInfo.getY(), thisInfo.getWidth(), thisInfo.getHeight());
            }
            // Image to byte buffer:
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(image, "jpg", bos);
            // Byte buffer to Base64 String:
            base64Result = Base64.encodeBase64String(bos.toByteArray());
            // Add Base64 head:
            base64Result = base64Head + "," + base64Result;
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
        }
        return base64Result;
    }

    public String drawRectInImgFromBase64ForClothes(String base64Img, List<ClothesInfo> boxList){
        String base64Result = "";
        // Decode base64 String to byte buffer:
        // Get rid of original base64 string head:
        String base64Head = base64Img.split(",")[0];
        base64Img = base64Img.split(",")[1];
        byte[] buffer = new Base64().decode(base64Img);
        // Convert byte buffer to stream:
        ByteArrayInputStream bai = new ByteArrayInputStream(buffer);
        // Transform stream to image and process image:
        try {
            BufferedImage image = ImageIO.read(bai);
            Graphics graph = image.getGraphics();
            graph.setColor(Color.RED);// Paint color
            // Draw rectangle:
            for(ClothesInfo thisInfo : boxList){
                graph.drawRect(thisInfo.getX(), thisInfo.getY(), thisInfo.getWidth(), thisInfo.getHeight());
            }
            // Image to byte buffer:
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(image, "jpg", bos);
            // Byte buffer to Base64 String:
            base64Result = Base64.encodeBase64String(bos.toByteArray());
            // Add Base64 head:
            base64Result = base64Head + "," + base64Result;
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
        }
        return base64Result;
    }

    public String drawRectInImgFromBase64ForVest(String base64Img, List<VestInfo> boxList){
        String base64Result = "";
        // Decode base64 String to byte buffer:
        // Get rid of original base64 string head:
        String base64Head = base64Img.split(",")[0];
        base64Img = base64Img.split(",")[1];
        byte[] buffer = new Base64().decode(base64Img);
        // Convert byte buffer to stream:
        ByteArrayInputStream bai = new ByteArrayInputStream(buffer);
        // Transform stream to image and process image:
        try {
            BufferedImage image = ImageIO.read(bai);
            Graphics graph = image.getGraphics();
            graph.setColor(Color.RED);// Paint color
            // Draw rectangle:
            for(VestInfo thisInfo : boxList){
                graph.drawRect(thisInfo.getX(), thisInfo.getY(), thisInfo.getWidth(), thisInfo.getHeight());
            }
            // Image to byte buffer:
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(image, "jpg", bos);
            // Byte buffer to Base64 String:
            base64Result = Base64.encodeBase64String(bos.toByteArray());
            // Add Base64 head:
            base64Result = base64Head + "," + base64Result;
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
        }
        return base64Result;
    }

    public int saveBase64StringToFile(String base64String, String filePath){
        // create a buffered image
        BufferedImage image = null;
        File file = new File(filePath);
        if(base64String.length() <= 50){
            System.out.println("Base64格式错误，无法储存！");
            return 0;
        }
        // Save to file using file stream:
        try {
            byte[] byteBuffer = Base64.decodeBase64(base64String);
            OutputStream stream = new FileOutputStream(filePath);
            stream.write(byteBuffer);
            stream.close();
        } catch (IOException e) {
            System.out.println("Write file failed: " + filePath);
            e.printStackTrace();
            return 0;
        }
        return 1;
    }

}
