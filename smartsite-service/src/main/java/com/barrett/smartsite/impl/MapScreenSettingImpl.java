package com.barrett.smartsite.impl;

import com.barrett.smartsite.bean.MapScreenSetting;
import com.barrett.smartsite.impl.MapScreenSettingImpl;
import com.barrett.smartsite.mapper.MapScreenSettingDao;
import com.barrett.smartsite.service.MapScreenSettingService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: smartsite
 * @description:
 * @author: Barrett
 * @create: 2019-10-07 11:11
 **/
@Service
public class MapScreenSettingImpl
        implements MapScreenSettingService {
    @Autowired(required = false)
    MapScreenSettingDao mapScreenSettingDao;

    public int insert(MapScreenSetting mapScreenSetting) {
        return this.mapScreenSettingDao.insert(mapScreenSetting);
    }

    public int update(MapScreenSetting mapScreenSetting) {
        return this.mapScreenSettingDao.update(mapScreenSetting);
    }

    public int delete(int id) {
        return this.mapScreenSettingDao.delete(id);
    }

    public int deleteAll() {
        return this.mapScreenSettingDao.deleteAll();
    }

    public List<MapScreenSetting> getAllById(int id) {
        return this.mapScreenSettingDao.getAllById(id);
    }

    public List<MapScreenSetting> getAll() {
        return this.mapScreenSettingDao.getAll();
    }
}