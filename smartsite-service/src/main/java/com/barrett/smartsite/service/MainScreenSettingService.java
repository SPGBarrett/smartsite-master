package com.barrett.smartsite.service;


import com.barrett.smartsite.bean.MainScreenSetting;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @program: smartsite
 * @description:
 * @author: Barrett
 * @create: 2019-10-07 11:43
 **/
public interface MainScreenSettingService {
    int insert(MainScreenSetting paramMainScreenSetting);

    int update(MainScreenSetting paramMainScreenSetting);

    int updateIsloop(int isloop);

    int delete(int paramInt);

    int deleteAll();

    List<MainScreenSetting> getAllById(int paramInt);

    List<MainScreenSetting> getAll();
}
