package com.barrett.smartsite.bean;

import lombok.Data;

/**
 * @program: smartsite
 * @description: java bean
 * @author: Barrett
 * @create: 2019-10-07 10:16
 **/
@Data
public class WelcomeScreenSetting {
    private int id;
    private String welcome_text;
    private int bg_mode;
    private String bg_color;
    private String pic_path;
    private int txt_pos_x;
    private int txt_pos_y;
    private String txt_font;
    private int txt_size;
    private String txt_color;
    private String line_height;

}
