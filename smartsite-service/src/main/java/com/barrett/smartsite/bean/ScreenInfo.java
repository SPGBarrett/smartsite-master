package com.barrett.smartsite.bean;

/**
 * @program: smartsite
 * @description: java bean
 * @author: Barrett
 * @create: 2019-10-07 10:13
 **/
public class ScreenInfo {
    private int id;
    private int screen_num;
    private String screen_name;
    private String screen_content;
    private int content_type;
    private String create_date;
    private String modify_date;
    private String create_user;
    private int width;
    private int length;
    private int resolution_x;
    private int resolution_y;
    private int state;
    private String memo;
    private String data_info;

    public int getId() {
        return this.id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public int getScreen_num() {
        return this.screen_num;
    }


    public void setScreen_num(int screen_num) {
        this.screen_num = screen_num;
    }


    public String getScreen_name() {
        return this.screen_name;
    }


    public void setScreen_name(String screen_name) {
        this.screen_name = screen_name;
    }


    public String getScreen_content() {
        return this.screen_content;
    }


    public void setScreen_content(String screen_content) {
        this.screen_content = screen_content;
    }


    public int getContent_type() {
        return this.content_type;
    }


    public void setContent_type(int content_type) {
        this.content_type = content_type;
    }


    public String getCreate_date() {
        return this.create_date;
    }


    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }


    public String getModify_date() {
        return this.modify_date;
    }


    public void setModify_date(String modify_date) {
        this.modify_date = modify_date;
    }


    public String getCreate_user() {
        return this.create_user;
    }


    public void setCreate_user(String create_user) {
        this.create_user = create_user;
    }


    public int getWidth() {
        return this.width;
    }


    public void setWidth(int width) {
        this.width = width;
    }


    public int getLength() {
        return this.length;
    }


    public void setLength(int length) {
        this.length = length;
    }


    public int getResolution_x() {
        return this.resolution_x;
    }


    public void setResolution_x(int resolution_x) {
        this.resolution_x = resolution_x;
    }


    public int getResolution_y() {
        return this.resolution_y;
    }


    public void setResolution_y(int resolution_y) {
        this.resolution_y = resolution_y;
    }


    public int getState() {
        return this.state;
    }


    public void setState(int state) {
        this.state = state;
    }


    public String getMemo() {
        return this.memo;
    }


    public void setMemo(String memo) {
        this.memo = memo;
    }


    public String getData_info() {
        return this.data_info;
    }


    public void setData_info(String data_info) {
        this.data_info = data_info;
    }


    public ScreenInfo() {
    }


    public ScreenInfo(int id, int screen_num, String screen_name, String screen_content, int content_type, String create_date, String modify_date, String create_user, int width, int length, int resolution_x, int resolution_y, int state, String memo, String data_info) {
        this.id = id;
        this.screen_num = screen_num;
        this.screen_name = screen_name;
        this.screen_content = screen_content;
        this.content_type = content_type;
        this.create_date = create_date;
        this.modify_date = modify_date;
        this.create_user = create_user;
        this.width = width;
        this.length = length;
        this.resolution_x = resolution_x;
        this.resolution_y = resolution_y;
        this.state = state;
        this.memo = memo;
        this.data_info = data_info;
    }
}
