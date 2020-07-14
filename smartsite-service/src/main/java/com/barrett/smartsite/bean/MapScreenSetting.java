package com.barrett.smartsite.bean;

/**
 * @program: smartsite
 * @description: java bean
 * @author: Barrett
 * @create: 2019-10-07 10:07
 **/
public class MapScreenSetting {
    private int id;
    private int main_map_type;
    private int map_loop_interval;
    private int data_loop_interval;
    private String data_type_one;
    private String data_type_two;
    private String all_display_data;
    private String all_display_maps;
    private int loop_map;
    private int loop_data;

    public int getId() {
        return this.id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public int getMain_map_type() {
        return this.main_map_type;
    }


    public void setMain_map_type(int main_map_type) {
        this.main_map_type = main_map_type;
    }


    public int getMap_loop_interval() {
        return this.map_loop_interval;
    }


    public void setMap_loop_interval(int map_loop_interval) {
        this.map_loop_interval = map_loop_interval;
    }


    public int getData_loop_interval() {
        return this.data_loop_interval;
    }


    public void setData_loop_interval(int data_loop_interval) {
        this.data_loop_interval = data_loop_interval;
    }


    public String getData_type_one() {
        return this.data_type_one;
    }


    public void setData_type_one(String data_type_one) {
        this.data_type_one = data_type_one;
    }


    public String getData_type_two() {
        return this.data_type_two;
    }


    public void setData_type_two(String data_type_two) {
        this.data_type_two = data_type_two;
    }


    public String getAll_display_data() {
        return this.all_display_data;
    }


    public void setAll_display_data(String all_display_data) {
        this.all_display_data = all_display_data;
    }


    public String getAll_display_maps() {
        return this.all_display_maps;
    }


    public void setAll_display_maps(String all_display_maps) {
        this.all_display_maps = all_display_maps;
    }


    public int getLoop_map() {
        return this.loop_map;
    }


    public void setLoop_map(int loop_map) {
        this.loop_map = loop_map;
    }


    public int getLoop_data() {
        return this.loop_data;
    }


    public void setLoop_data(int loop_data) {
        this.loop_data = loop_data;
    }


    public MapScreenSetting() {
    }


    public MapScreenSetting(int id, int main_map_type, int map_loop_interval, int data_loop_interval, String data_type_one, String data_type_two, String all_display_data, String all_display_maps, int loop_map, int loop_data) {
        this.id = id;
        this.main_map_type = main_map_type;
        this.map_loop_interval = map_loop_interval;
        this.data_loop_interval = data_loop_interval;
        this.data_type_one = data_type_one;
        this.data_type_two = data_type_two;
        this.all_display_data = all_display_data;
        this.all_display_maps = all_display_maps;
        this.loop_map = loop_map;
        this.loop_data = loop_data;
    }
}
