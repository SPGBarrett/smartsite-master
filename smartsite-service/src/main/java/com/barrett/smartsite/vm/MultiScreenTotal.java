package com.barrett.smartsite.vm;


import com.barrett.smartsite.bean.MultiFiles;

import java.util.List;

/**
 * @program: smartsite
 * @description:
 * @author: Barrett
 * @create: 2019-10-07 14:23
 **/
public class MultiScreenTotal {
    private int display_type;
    private String multi_path;
    private int loop_interval;
    private String trans_style;
    private List<MultiFiles> multi_files;

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


    public List<MultiFiles> getMulti_files() {
        return this.multi_files;
    }


    public void setMulti_files(List<MultiFiles> multi_files) {
        this.multi_files = multi_files;
    }


    public MultiScreenTotal() {
    }


    public MultiScreenTotal(int display_type, String multi_path, int loop_interval, String trans_style, List<MultiFiles> multi_files) {
        this.display_type = display_type;
        this.multi_path = multi_path;
        this.loop_interval = loop_interval;
        this.trans_style = trans_style;
        this.multi_files = multi_files;
    }
}
