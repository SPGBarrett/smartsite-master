package com.barrett.entrancecontrolservice;

import com.barrett.entrancecontrolservice.util.SpringUtil;
import com.barrett.entrancecontrolservice.util.function;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.io.UnsupportedEncodingException;
import java.util.Scanner;

@SpringBootApplication
public class EntrancecontrolserviceApplication {

    public static boolean cameraconntect = false;

    public static void main(String[] args) {
        SpringApplication.run(EntrancecontrolserviceApplication.class, args);
        // Get component class injected to Spring container:
        ApplicationContext context = SpringUtil.getApplicationContext();
        function fun = context.getBean(function.class);

        //*******************************************************************
        //这是一个示例程序，函数输入输出用控制台打印
        fun.Init();
        getcameraip();
        boolean stop =true;
        while(stop)
        {
            try {
                stop = maninterface();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            if(stop ==false)
                break ;
        }

    }

    public static boolean maninterface( ) throws UnsupportedEncodingException {

        ApplicationContext context = SpringUtil.getApplicationContext();
        function fun = context.getBean(function.class);

        Scanner sc = new Scanner(System.in);
        System.out.println("**************************************");
        System.out.println("请输入整数标号");
        System.out.println("0：连接相机  1:断开相机 ,2连接/断开视频");
        System.out.println("3：添加人脸  4:查询及删除人脸 ,5搜索相机");
        System.out.println("6：设置韦根  7:灯控设置 ,8: 去重复");
        System.out.println("9：相似度设计  10:标题设置 ,11: 时间设置");
        System.out.println("12：系统升级，13： 串口设置，14： 网络设置");
        System.out.println("15：字符串解码");
        System.out.println("其它：退出 ");
        System.out.println("**********请输入要选择的操作*********");
        int chooice=999;
        try {chooice = sc.nextInt();}
        catch(Exception e){System.out.println("输入有误");}
        //  libFaceRecognition.INSTANCE.lib_clsClear();
        System.out.println(chooice);

        switch(chooice){
            case 0 :  getcameraip();break;
            case 1 :  fun.DisConnectCamera();break;
            case 2 :  fun.StartStream();break;
            case 3:  fun.AddFace();break;
            case 4 :  fun.  degregmng();break;
            case 5 :  fun.searchcerme();break;
            case 6 :  fun.SettingWG();break;
            case 7 :  fun.SettingLight();break;
            case 8:  fun. Repetition ();break;
            case 9 :  fun.SimilaritySetting();break;
            case 10 :  fun.TagSetting();break;
            case 11 :  fun.TimeSetting();break;
            case 12 :  fun.upgrade();break;
            case 13 :  fun.GorgelineSetting();break;
            case 14 :  fun.webSetting();break;
            case 15 :  fun.DecodeDemo();break;
            default : return false;
            //语句
        }
        return  true;
    }

    public static void getcameraip()
    {
        ApplicationContext context = SpringUtil.getApplicationContext();
        function fun = context.getBean(function.class);
        // Scanner sc = new Scanner(System.in);
        //  System.out.println("请输入相机ip");
        // String str = sc.nextLine();
        String str="10.168.1.176";
        fun.connectCamera(str);
    }

}
