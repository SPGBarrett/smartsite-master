package com.barrett.smartsite.controller;

import com.barrett.smartsite.bean.DataScreenSetting;
import com.barrett.smartsite.bean.MainScreenSetting;
import com.barrett.smartsite.bean.MapScreenSetting;
import com.barrett.smartsite.bean.MonitorScreenSetting;
import com.barrett.smartsite.bean.MultiFiles;
import com.barrett.smartsite.bean.MultiScreenSetting;
import com.barrett.smartsite.bean.ScreenInfo;
import com.barrett.smartsite.bean.SelectionDict;
import com.barrett.smartsite.bean.TextContent;
import com.barrett.smartsite.bean.TextScreenSetting;
import com.barrett.smartsite.bean.WelcomeScreenSetting;
import com.barrett.smartsite.controller.FileUploadController;
import com.barrett.smartsite.factory.FileUtil;
import com.barrett.smartsite.factory.StringUtil;
import com.barrett.smartsite.service.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

/**
 * @program: smartsite
 * @description: User controller
 * @author: Barrett
 * @create: 2019-10-07 10:19
 **/
@CrossOrigin
@RestController
@RequestMapping({"/SiteScreenParams"})
public class ApplicationDebugController {
    private DataScreenSetting dataScreenSetting = new DataScreenSetting();
    private MainScreenSetting mainScreenSetting = new MainScreenSetting();
    private MapScreenSetting mapScreenSetting = new MapScreenSetting();
    private MonitorScreenSetting monitorScreenSetting = new MonitorScreenSetting();
    private MultiFiles multiFiles = new MultiFiles();
    private MultiScreenSetting multiScreenSetting = new MultiScreenSetting();
    private ScreenInfo screenInfo = new ScreenInfo();
    private SelectionDict selectionDict = new SelectionDict();
    private TextContent textContent = new TextContent();
    private TextScreenSetting textScreenSetting = new TextScreenSetting();
    private WelcomeScreenSetting welcomeScreenSetting = new WelcomeScreenSetting();

    @Autowired
    private DataScreenSettingService dataScreenSettingService;

    @Autowired
    private MainScreenSettingService mainScreenSettingService;

    @Autowired
    private MapScreenSettingService mapScreenSettingService;
    @Autowired
    private MonitorScreenSettingService monitorScreenSettingService;
    @Autowired
    private MultiFilesService multiFilesService;
    @Autowired
    private MultiScreenSettingService multiScreenSettingService;
    @Autowired
    private ScreenInfoService screenInfoService;
    @Autowired
    private SelectionDictService selectionDictService;
    @Autowired
    private TextContentService textContentService;
    @Autowired
    private TextScreenSettingService textScreenSettingService;
    @Autowired
    private WelcomeScreenSettingService welcomeScreenSettingService;
    private FileUploadController fileUploadController = new FileUploadController();
    private FileUtil fileUtil = new FileUtil();


    private StringUtil strUtil = new StringUtil();


    private final int DEFAULT_INTERVAL = 5000;
    private final String[] DEFAULT_DATA_TYPES = {"��������", "��������", "��������", "��������", "��������", "��������"};
    private final String[] DEFAULT_MAP_TYPES = {"������", "��������", "3D����", "��������"};
    private final int DEFAULT_LOOP_DATA = 0;
    private final int DEFAULT_LOOP_MAP = 0;
    private final String[] DEFAULT_SELECTED_SCREEN = {"1", "2", "5"};
    private final int DEFAULT_MAIN_SCREEN = 1;
    private final int DEFAULT_MAIN_SELECTION = 1;
    private final String[] DEFAULT_ALL_DATA = this.DEFAULT_DATA_TYPES;
    private final String[] DEFAULT_ALL_MAP = this.DEFAULT_MAP_TYPES;
    private final String[] DEFAULT_MONITOR_IDS = {
            "#1", "#2", "#3", "#4", "#5", "#6", "#7", "#8", "#9", "#10", "#11", "#12"};
    private final String DEFAULT_MULTI_PATH = "E:/uploadFiles";
    private final String[] DEFAULT_TRANS_STYLE = {"����", "����", "����", "����", "����"};
    private final int DEFAULT_WIDTH = 1;
    private final int DEFAULT_LENGTH = 1;
    private final int DEFAULT_RESOLUTE_X = 1280;
    private final int DEFAULT_RESOLUTE_Y = 720;
    private final int DEFAULT_STATE = 1;
    private final String DEFAULT_MEMO = "����������...";
    private final String DEFAULT_DATA_INFO = "��������������...";
    private final String[] DEFAULT_COLORS = {"����", "����", "����", "����", "����", "����", "����"};
    private final String[] DEFAULT_BG_TYPES = {"����", "����", "����"};
    private final int[] DEFAULT_BG_TYPES_INT = {1, 2, 3};
    private final String[] DEFAULT_FONTS = {"����", "����", "����", "����", "����"};
    private final String[] DEFAULT_FONT_SIZE = {"12", "14", "16", "18", "24", "26", "32", "40"};
    private final String[] DEFAULT_UI_TYPES = {"5����", "8����"};
    private final int DEFAULT_POS_X = 0;
    private final int DEFAULT_POS_Y = 0;
    private final int DEFAULT_TEXT_CONTENT_NUM = 3;
    private final int DEFAULT_SCREEN_NUM = 6;
    private final String DEFAULT_WELCOME_TEXT = "����xxx����xxx������������xxx������������������";
    private final String[] DEFAULT_SCREEN_NAMES = {"����������", "������������", "����������", "������������", "��������������", "������������"};
    private final String[] DEFAULT_SCREEN_CONTENT = {"����������������", "����������������", "����������������", "������������", "��������������������������", "������������"};
    private final String[] DEFAULT_CONTENT_TYPES = {"1", "2", "3", "4", "5", "6"};
    private final String DEFAULT_CREATE_DATE = "2019-08-08";
    private final String DEFAULT_MODIFY_DATE = "2019-08-08";
    private final String DEFAULT_CREATE_USER = "5675";


    @RequestMapping(value = {"/InitDataBase"}, method = {RequestMethod.PUT})
    public void initDatabase() {
        dataInitializer();
    }


    @RequestMapping(value = {"/testLog/{data}"}, method = {RequestMethod.GET})
    public String testAOPLogFile(@PathVariable String data) {
        return "This is the data of the input: " + data;
    }


    public void injectInitialData() {
        this.dataScreenSetting.setLoop_interval(5000);
        this.dataScreenSetting.setData_id_one(this.DEFAULT_DATA_TYPES[0]);
        this.dataScreenSetting.setData_id_two(this.DEFAULT_DATA_TYPES[1]);
        this.dataScreenSetting.setData_id_three(this.DEFAULT_DATA_TYPES[2]);
        this.dataScreenSetting.setData_id_four(this.DEFAULT_DATA_TYPES[3]);
        this.dataScreenSetting.setData_id_five(this.DEFAULT_DATA_TYPES[4]);
        this.dataScreenSetting.setData_id_six(this.DEFAULT_DATA_TYPES[0]);
        this.dataScreenSetting.setData_id_seven(this.DEFAULT_DATA_TYPES[0]);
        this.dataScreenSetting.setData_id_eight(this.DEFAULT_DATA_TYPES[0]);
        this.dataScreenSetting.setLoop_data(0);
        this.dataScreenSettingService.insert(this.dataScreenSetting);

        this.mainScreenSetting.setSelected_screen(this.strUtil.constructStringFromList(this.DEFAULT_SELECTED_SCREEN));
        this.mainScreenSetting.setLoop_interval(5000);
        this.mainScreenSetting.setMain_screen(1);
        this.mainScreenSettingService.insert(this.mainScreenSetting);

        this.mapScreenSetting.setMain_map_type(1);
        this.mapScreenSetting.setMap_loop_interval(5000);
        this.mapScreenSetting.setData_loop_interval(5000);
        this.mapScreenSetting.setData_type_one(this.DEFAULT_DATA_TYPES[0]);
        this.mapScreenSetting.setData_type_two(this.DEFAULT_DATA_TYPES[1]);
        this.mapScreenSetting.setAll_display_data(this.strUtil.constructStringFromList(this.DEFAULT_ALL_DATA));
        this.mapScreenSetting.setAll_display_maps(this.strUtil.constructStringFromList(this.DEFAULT_ALL_MAP));
        this.mapScreenSetting.setLoop_map(0);
        this.mapScreenSetting.setLoop_data(0);
        this.mapScreenSettingService.insert(this.mapScreenSetting);

        this.monitorScreenSetting.setDisplay_type(1);
        this.monitorScreenSetting.setLoop_interval(5000);
        this.monitorScreenSetting.setMonitor_id_one(this.DEFAULT_MONITOR_IDS[0]);
        this.monitorScreenSetting.setMonitor_id_two(this.DEFAULT_MONITOR_IDS[1]);
        this.monitorScreenSetting.setMonitor_id_three(this.DEFAULT_MONITOR_IDS[2]);
        this.monitorScreenSetting.setMonitor_id_four(this.DEFAULT_MONITOR_IDS[3]);
        this.monitorScreenSetting.setMonitor_id_five(this.DEFAULT_MONITOR_IDS[4]);
        this.monitorScreenSetting.setMonitor_id_six(this.DEFAULT_MONITOR_IDS[5]);
        this.monitorScreenSetting.setMonitor_id_seven(this.DEFAULT_MONITOR_IDS[6]);
        this.monitorScreenSetting.setMonitor_id_eight(this.DEFAULT_MONITOR_IDS[7]);
        this.monitorScreenSetting.setLoop_data(0);
        this.monitorScreenSettingService.insert(this.monitorScreenSetting);

        this.multiScreenSetting.setDisplay_type(1);
        this.multiScreenSetting.setMulti_path("E:/uploadFiles");
        this.multiScreenSetting.setLoop_interval(5000);
        this.multiScreenSetting.setTrans_style(this.DEFAULT_TRANS_STYLE[0]);
        this.multiScreenSettingService.insert(this.multiScreenSetting);

        this.screenInfo.setCreate_date("2019-08-08");
        this.screenInfo.setModify_date("2019-08-08");
        this.screenInfo.setCreate_user("5675");
        this.screenInfo.setWidth(1);
        this.screenInfo.setLength(1);
        this.screenInfo.setResolution_x(1280);
        this.screenInfo.setResolution_y(720);
        this.screenInfo.setState(1);
        this.screenInfo.setMemo("����������...");
        this.screenInfo.setData_info("��������������...");
        for (int i = 0; i < 6; i++) {
            this.screenInfo.setScreen_num(i + 1);
            this.screenInfo.setScreen_name(this.DEFAULT_SCREEN_NAMES[i]);
            this.screenInfo.setScreen_content(this.DEFAULT_SCREEN_CONTENT[i]);
            this.screenInfo.setContent_type(Integer.valueOf(this.DEFAULT_CONTENT_TYPES[i]).intValue());
            this.screenInfoService.insert(this.screenInfo);
        }


        this.selectionDict.setColors(this.strUtil.constructStringFromList(this.DEFAULT_COLORS));
        this.selectionDict.setData_types(this.strUtil.constructStringFromList(this.DEFAULT_DATA_TYPES));
        this.selectionDict.setMonitor_ids(this.strUtil.constructStringFromList(this.DEFAULT_MONITOR_IDS));
        this.selectionDict.setBg_types(this.strUtil.constructStringFromList(this.DEFAULT_BG_TYPES));
        this.selectionDict.setFonts(this.strUtil.constructStringFromList(this.DEFAULT_FONTS));
        this.selectionDict.setFont_size(this.strUtil.constructStringFromList(this.DEFAULT_FONT_SIZE));
        this.selectionDict.setTrans_styles(this.strUtil.constructStringFromList(this.DEFAULT_TRANS_STYLE));
        this.selectionDict.setUi_types(this.strUtil.constructStringFromList(this.DEFAULT_UI_TYPES));
        this.selectionDict.setMap_types(this.strUtil.constructStringFromList(this.DEFAULT_MAP_TYPES));
        this.selectionDictService.insert(this.selectionDict);

        this.textContent.setTxt_pos_x(0);
        this.textContent.setTxt_pos_y(0);
        this.textContent.setTxt_font(this.DEFAULT_FONTS[0]);
        this.textContent.setTxt_size(Integer.valueOf(this.DEFAULT_FONT_SIZE[2]).intValue());
        this.textContent.setTxt_color(this.DEFAULT_COLORS[4]);
        for (int i = 0; i < 3; i++) {
            this.textContent.setTxt_set_num(i + 1);
            this.textContent.setContent("��������������������-" + String.valueOf(i + 1));
            this.textContentService.insert(this.textContent);
        }

        this.textScreenSetting.setTotal_num(3);
        this.textScreenSetting.setLoop_interval(5000);
        this.textScreenSetting.setTrans_style(this.DEFAULT_TRANS_STYLE[0]);
        this.textScreenSetting.setLoop_data(0);
        this.textScreenSettingService.insert(this.textScreenSetting);

        this.welcomeScreenSetting.setWelcome_text("����xxx����xxx������������xxx������������������");
        this.welcomeScreenSetting.setBg_mode(Integer.valueOf(this.DEFAULT_BG_TYPES_INT[0]).intValue());
        this.welcomeScreenSetting.setBg_color(this.DEFAULT_COLORS[0]);
        this.welcomeScreenSetting.setPic_path("E:/uploadFiles");
        this.welcomeScreenSetting.setTxt_pos_x(0);
        this.welcomeScreenSetting.setTxt_pos_y(0);
        this.welcomeScreenSetting.setTxt_font(this.DEFAULT_FONTS[0]);
        this.welcomeScreenSetting.setTxt_size(Integer.valueOf(this.DEFAULT_FONT_SIZE[3]).intValue());
        this.welcomeScreenSettingService.insert(this.welcomeScreenSetting);
    }

    public void injectMultiInitData() {
        String path = ClassUtils.getDefaultClassLoader().getResource("").getPath();
        File file = new File(path + "/static/132.png");
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        MockMultipartFile mockMultipartFile = null;

        try {
            mockMultipartFile = new MockMultipartFile(file.getName(), file.getName(), ContentType.APPLICATION_OCTET_STREAM.toString(), fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (mockMultipartFile != null) ;
    }

    public boolean checkDBStatus() {
        List<TextContent> tmpData = this.textContentService.getAll();
        if (tmpData.size() > 0) {
            return true;
        }
        return false;
    }

    private void clearAllData() {
        this.dataScreenSettingService.deleteAll();
        this.mainScreenSettingService.deleteAll();
        this.mapScreenSettingService.deleteAll();
        this.monitorScreenSettingService.deleteAll();
        this.multiFilesService.deleteAll();
        this.multiScreenSettingService.deleteAll();
        this.screenInfoService.deleteAll();
        this.selectionDictService.deleteAll();
        this.textContentService.deleteAll();
        this.textScreenSettingService.deleteAll();
        this.welcomeScreenSettingService.deleteAll();
    }

    public void dataInitializer() {
        clearAllData();

        injectInitialData();

        injectMultiInitData();
    }
}
