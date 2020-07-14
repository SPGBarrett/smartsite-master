package com.barrett.smartsite.service;

import com.barrett.smartsite.bean.MonitorScreenSetting;

import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * @program: smartsite
 * @description:
 * @author: Barrett
 * @create: 2019-10-07 11:44
 **/
public interface MonitorScreenSettingService {
    int insert(MonitorScreenSetting paramMonitorScreenSetting);

    int update(MonitorScreenSetting paramMonitorScreenSetting);

    int delete(@Param("id") int paramInt);

    int deleteAll();

    List<MonitorScreenSetting> getAllById(@Param("id") int paramInt);

    List<MonitorScreenSetting> getAll();
}
