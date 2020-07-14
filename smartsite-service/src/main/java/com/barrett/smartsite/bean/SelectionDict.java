package com.barrett.smartsite.bean;

/**
 * @program: smartsite
 * @description: java bean
 * @author: Barrett
 * @create: 2019-10-07 10:14
 **/
public class SelectionDict {
    private int id;
    private String colors;
    private String data_types;
    private String monitor_ids;
    private String bg_types;
    private String fonts;
    private String font_size;
    private String trans_styles;
    private String ui_types;
    private String map_types;

    public int getId() {
        return this.id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public String getColors() {
        return this.colors;
    }


    public void setColors(String colors) {
        this.colors = colors;
    }


    public String getData_types() {
        return this.data_types;
    }


    public void setData_types(String data_types) {
        this.data_types = data_types;
    }


    public String getMonitor_ids() {
        return this.monitor_ids;
    }


    public void setMonitor_ids(String monitor_ids) {
        this.monitor_ids = monitor_ids;
    }


    public String getBg_types() {
        return this.bg_types;
    }


    public void setBg_types(String bg_types) {
        this.bg_types = bg_types;
    }


    public String getFonts() {
        return this.fonts;
    }


    public void setFonts(String fonts) {
        this.fonts = fonts;
    }


    public String getFont_size() {
        return this.font_size;
    }


    public void setFont_size(String font_size) {
        this.font_size = font_size;
    }


    public String getTrans_styles() {
        return this.trans_styles;
    }


    public void setTrans_styles(String trans_styles) {
        this.trans_styles = trans_styles;
    }


    public String getUi_types() {
        return this.ui_types;
    }


    public void setUi_types(String ui_types) {
        this.ui_types = ui_types;
    }


    public String getMap_types() {
        return this.map_types;
    }


    public void setMap_types(String map_types) {
        this.map_types = map_types;
    }


    public SelectionDict() {
    }


    public SelectionDict(int id, String colors, String data_types, String monitor_ids, String bg_types, String fonts, String font_size, String trans_styles, String ui_types, String map_types) {
        this.id = id;
        this.colors = colors;
        this.data_types = data_types;
        this.monitor_ids = monitor_ids;
        this.bg_types = bg_types;
        this.fonts = fonts;
        this.font_size = font_size;
        this.trans_styles = trans_styles;
        this.ui_types = ui_types;
        this.map_types = map_types;
    }
}
