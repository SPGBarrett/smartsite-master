package com.barrett.smartsite.service;

import com.barrett.smartsite.bean.WelcomeScreenSetting;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @program: smartsite
 * @description:
 * @author: Barrett
 * @create: 2019-10-07 14:23
 **/
public interface WelcomeScreenSettingService {
    int insert(WelcomeScreenSetting paramWelcomeScreenSetting);

    int update(WelcomeScreenSetting paramWelcomeScreenSetting);

    int updatePicPath(int id, String pic_path);

    int delete(int paramInt);

    int deleteAll();

    List<WelcomeScreenSetting> getAllById(int paramInt);

    List<WelcomeScreenSetting> getAll();
}
