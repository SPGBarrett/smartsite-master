package com.barrett.smartsite.bean;

import lombok.Data;

/**
 * @program: smartsite
 * @description: java bean
 * @author: Barrett
 * @create: 2019-10-07 10:14
 **/
@Data
public class TextContent {
    private int id;
    private int txt_set_num;
    private String content;
    private int txt_pos_x;
    private int txt_pos_y;
    private String txt_font;
    private int txt_size;
    private String txt_color;
    private String title_txt;
}
