package com.barrett.smartsite.bean;

/**
 * @program: smartsite
 * @description: java bean
 * @author: Barrett
 * @create: 2019-10-07 10:05
 **/
public class MainScreenSetting {
    private int id;
    private String selected_screen;
    private int loop_interval;
    private int main_screen;
    private int loop_screen; // mark if loop, 0=not loop, 1=loop

    public MainScreenSetting() {
    }

    public MainScreenSetting(int id, String selected_screen, int loop_interval, int main_screen, int loop_screen) {
        this.id = id;
        this.selected_screen = selected_screen;
        this.loop_interval = loop_interval;
        this.main_screen = main_screen;
        this.loop_screen = loop_screen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSelected_screen() {
        return selected_screen;
    }

    public void setSelected_screen(String selected_screen) {
        this.selected_screen = selected_screen;
    }

    public int getLoop_interval() {
        return loop_interval;
    }

    public void setLoop_interval(int loop_interval) {
        this.loop_interval = loop_interval;
    }

    public int getMain_screen() {
        return main_screen;
    }

    public void setMain_screen(int main_screen) {
        this.main_screen = main_screen;
    }

    public int getLoop_screen() {
        return loop_screen;
    }

    public void setLoop_screen(int loop_screen) {
        this.loop_screen = loop_screen;
    }
}
