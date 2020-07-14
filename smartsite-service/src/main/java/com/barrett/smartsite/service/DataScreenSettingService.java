package com.barrett.smartsite.service;

import com.barrett.smartsite.bean.DataScreenSetting;

import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * @program: smartsite
 * @description:
 * @author: Barrett
 * @create: 2019-10-07 11:40
 **/
public interface DataScreenSettingService {
    int insert(DataScreenSetting paramDataScreenSetting);

    int update(DataScreenSetting paramDataScreenSetting);

    int delete(@Param("id") int paramInt);

    int deleteAll();

    List<DataScreenSetting> getAllById(@Param("id") int paramInt);

    List<DataScreenSetting> getAll();
}