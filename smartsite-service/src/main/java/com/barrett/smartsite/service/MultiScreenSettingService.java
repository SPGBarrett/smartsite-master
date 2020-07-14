package com.barrett.smartsite.service;

import com.barrett.smartsite.bean.MultiScreenSetting;

import java.util.List;

/**
 * @program: smartsite
 * @description:
 * @author: Barrett
 * @create: 2019-10-07 11:45
 **/
public interface MultiScreenSettingService {
    int insert(MultiScreenSetting paramMultiScreenSetting);

    int update(MultiScreenSetting paramMultiScreenSetting);

    int delete(int paramInt);

    int deleteAll();

    List<MultiScreenSetting> getAllById(int paramInt);

    List<MultiScreenSetting> getAll();
}
