package com.barrett.smartsite.bean;

/**
 * @program: smartsite
 * @description: java bean
 * @author: Barrett
 * @create: 2019-10-07 10:08
 **/
public class MonitorScreenSetting {
    private int id;
    private int display_type;
    private int loop_interval;
    private String monitor_id_one;
    private String monitor_id_two;
    private String monitor_id_three;
    private String monitor_id_four;
    private String monitor_id_five;
    private String monitor_id_six;
    private String monitor_id_seven;
    private String monitor_id_eight;
    private int loop_data;

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


    public int getLoop_interval() {
        return this.loop_interval;
    }


    public void setLoop_interval(int loop_interval) {
        this.loop_interval = loop_interval;
    }


    public String getMonitor_id_one() {
        return this.monitor_id_one;
    }


    public void setMonitor_id_one(String monitor_id_one) {
        this.monitor_id_one = monitor_id_one;
    }


    public String getMonitor_id_two() {
        return this.monitor_id_two;
    }


    public void setMonitor_id_two(String monitor_id_two) {
        this.monitor_id_two = monitor_id_two;
    }


    public String getMonitor_id_three() {
        return this.monitor_id_three;
    }


    public void setMonitor_id_three(String monitor_id_three) {
        this.monitor_id_three = monitor_id_three;
    }


    public String getMonitor_id_four() {
        return this.monitor_id_four;
    }


    public void setMonitor_id_four(String monitor_id_four) {
        this.monitor_id_four = monitor_id_four;
    }


    public String getMonitor_id_five() {
        return this.monitor_id_five;
    }


    public void setMonitor_id_five(String monitor_id_five) {
        this.monitor_id_five = monitor_id_five;
    }


    public String getMonitor_id_six() {
        return this.monitor_id_six;
    }


    public void setMonitor_id_six(String monitor_id_six) {
        this.monitor_id_six = monitor_id_six;
    }


    public String getMonitor_id_seven() {
        return this.monitor_id_seven;
    }


    public void setMonitor_id_seven(String monitor_id_seven) {
        this.monitor_id_seven = monitor_id_seven;
    }


    public String getMonitor_id_eight() {
        return this.monitor_id_eight;
    }


    public void setMonitor_id_eight(String monitor_id_eight) {
        this.monitor_id_eight = monitor_id_eight;
    }


    public int getLoop_data() {
        return this.loop_data;
    }


    public void setLoop_data(int loop_data) {
        this.loop_data = loop_data;
    }


    public MonitorScreenSetting() {
    }


    public MonitorScreenSetting(int id, int display_type, int loop_interval, String monitor_id_one, String monitor_id_two, String monitor_id_three, String monitor_id_four, String monitor_id_five, String monitor_id_six, String monitor_id_seven, String monitor_id_eight, int loop_data) {
        this.id = id;
        this.display_type = display_type;
        this.loop_interval = loop_interval;
        this.monitor_id_one = monitor_id_one;
        this.monitor_id_two = monitor_id_two;
        this.monitor_id_three = monitor_id_three;
        this.monitor_id_four = monitor_id_four;
        this.monitor_id_five = monitor_id_five;
        this.monitor_id_six = monitor_id_six;
        this.monitor_id_seven = monitor_id_seven;
        this.monitor_id_eight = monitor_id_eight;
        this.loop_data = loop_data;
    }
}
