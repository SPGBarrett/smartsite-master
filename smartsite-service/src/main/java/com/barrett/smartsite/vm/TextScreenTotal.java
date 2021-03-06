package com.barrett.smartsite.vm;

import com.barrett.smartsite.bean.TextContent;

import java.util.List;

/**
 * @program: smartsite
 * @description:
 * @author: Barrett
 * @create: 2019-10-07 14:24
 **/
public class TextScreenTotal {
    private int total_num;
    private int loop_interval;
    private String trans_style;
    private int loop_data;
    private List<TextContent> textContents;

    public int getTotal_num() {
        return this.total_num;
    }


    public void setTotal_num(int total_num) {
        this.total_num = total_num;
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


    public int getLoop_data() {
        return this.loop_data;
    }


    public void setLoop_data(int loop_data) {
        this.loop_data = loop_data;
    }


    public List<TextContent> getTextContents() {
        return this.textContents;
    }


    public void setTextContents(List<TextContent> textContents) {
        this.textContents = textContents;
    }


    public TextScreenTotal() {
    }


    public TextScreenTotal(int total_num, int loop_interval, String trans_style, int loop_data, List<TextContent> textContents) {
        this.total_num = total_num;
        this.loop_interval = loop_interval;
        this.trans_style = trans_style;
        this.loop_data = loop_data;
        this.textContents = textContents;
    }
}