package com.barrett.smartsite.bean;

import java.util.List;

/**
 * @program: smartsite
 * @description: Java bean that stores all params required for program screen display
 * @author: Barrett
 * @create: 2020-01-02 20:05
 **/
public class ProgScreenSettingParams {
    int loop_screen;// 0=no 1=yes
    int main_screen;
    int loop_interval;
    List<ProgScreenSetting> progScreenSettingList;

    public int getLoop_screen() {
        return loop_screen;
    }

    public void setLoop_screen(int loop_screen) {
        this.loop_screen = loop_screen;
    }

    public int getMain_screen() {
        return main_screen;
    }

    public void setMain_screen(int main_screen) {
        this.main_screen = main_screen;
    }

    public int getLoop_interval() {
        return loop_interval;
    }

    public void setLoop_interval(int loop_interval) {
        this.loop_interval = loop_interval;
    }

    public List<ProgScreenSetting> getProgScreenSettingList() {
        return progScreenSettingList;
    }

    public void setProgScreenSettingList(List<ProgScreenSetting> progScreenSettingList) {
        this.progScreenSettingList = progScreenSettingList;
    }

    public ProgScreenSettingParams(int loop_screen, int main_screen, int loop_interval, List<ProgScreenSetting> progScreenSettingList) {
        this.loop_screen = loop_screen;
        this.main_screen = main_screen;
        this.loop_interval = loop_interval;
        this.progScreenSettingList = progScreenSettingList;
    }

    public ProgScreenSettingParams() {
    }
}
