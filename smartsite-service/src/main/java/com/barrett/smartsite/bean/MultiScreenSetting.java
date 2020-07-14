package com.barrett.smartsite.bean;

/**
 * @program: smartsite
 * @description: java bean
 * @author: Barrett
 * @create: 2019-10-07 10:09
 **/
public class MultiScreenSetting {
    private int id;
    private int display_type;
    private String multi_path;
    private int loop_interval;
    private String trans_style;

    public int getId() {
        return this.id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public int getDisplay_type() {
        return this.display_type;
    }


    public void setDisplay_type(int display_type) {
        this.display_type = display_type;
    }


    public String getMulti_path() {
        return this.multi_path;
    }


    public void setMulti_path(String multi_path) {
        this.multi_path = multi_path;
    }


    public int getLoop_interval() {
        return this.loop_interval;
    }


    public void setLoop_interval(int loop_interval) {
        this.loop_interval = loop_interval;
    }


    public String getTrans_style() {
        return this.trans_style;
    }


    public void setTrans_style(String trans_style) {
        this.trans_style = trans_style;
    }


    public MultiScreenSetting() {
    }


    public MultiScreenSetting(int id, int display_type, String multi_path, int loop_interval, String trans_style) {
        this.id = id;
        this.display_type = display_type;
        this.multi_path = multi_path;
        this.loop_interval = loop_interval;
        this.trans_style = trans_style;
    }
}
