package com.barrett.smartsite.vm;

/**
 * @program: smartsite
 * @description:
 * @author: Barrett
 * @create: 2019-10-07 14:25
 **/
public class WelcomeScreenTotal {
    private String welcome_text;
    private int bg_mode;
    private String bg_color;
    private String pic_path;
    private int txt_pos_x;
    private int txt_pos_y;
    private String txt_font;
    private int txt_size;
    private String url;

    public String getWelcome_text() {
        return this.welcome_text;
    }


    public void setWelcome_text(String welcome_text) {
        this.welcome_text = welcome_text;
    }


    public int getBg_mode() {
        return this.bg_mode;
    }


    public void setBg_mode(int bg_mode) {
        this.bg_mode = bg_mode;
    }


    public String getBg_color() {
        return this.bg_color;
    }


    public void setBg_color(String bg_color) {
        this.bg_color = bg_color;
    }


    public String getPic_path() {
        return this.pic_path;
    }


    public void setPic_path(String pic_path) {
        this.pic_path = pic_path;
    }


    public int getTxt_pos_x() {
        return this.txt_pos_x;
    }


    public void setTxt_pos_x(int txt_pos_x) {
        this.txt_pos_x = txt_pos_x;
    }


    public int getTxt_pos_y() {
        return this.txt_pos_y;
    }


    public void setTxt_pos_y(int txt_pos_y) {
        this.txt_pos_y = txt_pos_y;
    }


    public String getTxt_font() {
        return this.txt_font;
    }


    public void setTxt_font(String txt_font) {
        this.txt_font = txt_font;
    }


    public int getTxt_size() {
        return this.txt_size;
    }


    public void setTxt_size(int txt_size) {
        this.txt_size = txt_size;
    }


    public String getUrl() {
        return this.url;
    }


    public void setUrl(String url) {
        this.url = url;
    }


    public WelcomeScreenTotal() {
    }


    public WelcomeScreenTotal(String welcome_text, int bg_mode, String bg_color, String pic_path, int txt_pos_x, int txt_pos_y, String txt_font, int txt_size, String url) {
        this.welcome_text = welcome_text;
        this.bg_mode = bg_mode;
        this.bg_color = bg_color;
        this.pic_path = pic_path;
        this.txt_pos_x = txt_pos_x;
        this.txt_pos_y = txt_pos_y;
        this.txt_font = txt_font;
        this.txt_size = txt_size;
        this.url = url;
    }
}
