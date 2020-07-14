package com.barrett.smartsite.service;

import com.barrett.smartsite.bean.MapScreenSetting;

import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * @program: smartsite
 * @description:
 * @author: Barrett
 * @create: 2019-10-07 11:34
 **/
public interface MapScreenSettingService {
    int insert(MapScreenSetting paramMapScreenSetting);

    int update(MapScreenSetting paramMapScreenSetting);

    int delete(@Param("id") int paramInt);

    int deleteAll();

    List<MapScreenSetting> getAllById(@Param("id") int paramInt);

    List<MapScreenSetting> getAll();
}
