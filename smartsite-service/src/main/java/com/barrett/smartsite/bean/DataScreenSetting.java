package com.barrett.smartsite.bean;

/**
 * @program: smartsite
 * @description: Java bean
 * @author: Barrett
 * @create: 2019-10-07 09:54
 **/
public class DataScreenSetting {
    private int id;
    private int loop_interval;
    private String data_id_one;
    private String data_id_two;
    private String data_id_three;
    private String data_id_four;
    private String data_id_five;
    private String data_id_six;
    private String data_id_seven;
    private String data_id_eight;
    private int loop_data;

    public int getId() {
        return this.id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public int getLoop_interval() {
        return this.loop_interval;
    }


    public void setLoop_interval(int loop_interval) {
        this.loop_interval = loop_interval;
    }


    public String getData_id_one() {
        return this.data_id_one;
    }


    public void setData_id_one(String data_id_one) {
        this.data_id_one = data_id_one;
    }


    public String getData_id_two() {
        return this.data_id_two;
    }


    public void setData_id_two(String data_id_two) {
        this.data_id_two = data_id_two;
    }


    public String getData_id_three() {
        return this.data_id_three;
    }


    public void setData_id_three(String data_id_three) {
        this.data_id_three = data_id_three;
    }


    public String getData_id_four() {
        return this.data_id_four;
    }


    public void setData_id_four(String data_id_four) {
        this.data_id_four = data_id_four;
    }


    public String getData_id_five() {
        return this.data_id_five;
    }


    public void setData_id_five(String data_id_five) {
        this.data_id_five = data_id_five;
    }


    public String getData_id_six() {
        return this.data_id_six;
    }


    public void setData_id_six(String data_id_six) {
        this.data_id_six = data_id_six;
    }


    public String getData_id_seven() {
        return this.data_id_seven;
    }


    public void setData_id_seven(String data_id_seven) {
        this.data_id_seven = data_id_seven;
    }


    public String getData_id_eight() {
        return this.data_id_eight;
    }


    public void setData_id_eight(String data_id_eight) {
        this.data_id_eight = data_id_eight;
    }


    public int getLoop_data() {
        return this.loop_data;
    }


    public void setLoop_data(int loop_data) {
        this.loop_data = loop_data;
    }


    public DataScreenSetting() {
    }


    public DataScreenSetting(int id, int loop_interval, String data_id_one, String data_id_two, String data_id_three, String data_id_four, String data_id_five, String data_id_six, String data_id_seven, String data_id_eight, int loop_data) {
        this.id = id;
        this.loop_interval = loop_interval;
        this.data_id_one = data_id_one;
        this.data_id_two = data_id_two;
        this.data_id_three = data_id_three;
        this.data_id_four = data_id_four;
        this.data_id_five = data_id_five;
        this.data_id_six = data_id_six;
        this.data_id_seven = data_id_seven;
        this.data_id_eight = data_id_eight;
        this.loop_data = loop_data;
    }
}
