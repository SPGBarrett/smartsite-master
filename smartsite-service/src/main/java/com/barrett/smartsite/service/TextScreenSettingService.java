package com.barrett.smartsite.service;

import com.barrett.smartsite.bean.TextScreenSetting;

import java.util.List;

/**
 * @program: smartsite
 * @description:
 * @author: Barrett
 * @create: 2019-10-07 14:22
 **/
public interface TextScreenSettingService {
    int insert(TextScreenSetting paramTextScreenSetting);

    int update(TextScreenSetting paramTextScreenSetting);

    int delete(int paramInt);

    int deleteAll();

    List<TextScreenSetting> getAllById(int paramInt);

    List<TextScreenSetting> getAll();
}
