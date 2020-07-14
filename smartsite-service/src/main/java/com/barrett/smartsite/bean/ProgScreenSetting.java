package com.barrett.smartsite.bean;

import java.util.Date;

/**
 * @program: smartsite
 * @description: Java bean for data table prog_screen_setting
 * @author: Barrett
 * @create: 2020-01-02 11:31
 **/
public class ProgScreenSetting {
    int id;
    int screen_no; // Start from 0
    Date start_time;
    Date end_time;
    Date set_time;
    String set_user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getScreen_no() {
        return screen_no;
    }

    public void setScreen_no(int screen_no) {
        this.screen_no = screen_no;
    }

    public Date getStart_time() {
        return start_time;
    }

    public void setStart_time(Date start_time) {
        this.start_time = start_time;
    }

    public Date getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Date end_time) {
        this.end_time = end_time;
    }

    public Date getSet_time() {
        return set_time;
    }

    public void setSet_time(Date set_time) {
        this.set_time = set_time;
    }

    public String getSet_user() {
        return set_user;
    }

    public void setSet_user(String set_user) {
        this.set_user = set_user;
    }

    public ProgScreenSetting(int id, int screen_no, Date start_time, Date end_time, Date set_time, String set_user) {
        this.id = id;
        this.screen_no = screen_no;
        this.start_time = start_time;
        this.end_time = end_time;
        this.set_time = set_time;
        this.set_user = set_user;
    }

    public ProgScreenSetting() {
    }
}
