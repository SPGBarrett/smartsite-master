package com.barrett.smartsite.impl;

import com.barrett.smartsite.bean.MonitorScreenSetting;
import com.barrett.smartsite.impl.MonitorScreenSettingImpl;
import com.barrett.smartsite.mapper.MonitorScreenSettingDao;
import com.barrett.smartsite.service.MonitorScreenSettingService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: smartsite
 * @description:
 * @author: Barrett
 * @create: 2019-10-07 11:12
 **/

@Service
public class MonitorScreenSettingImpl
        implements MonitorScreenSettingService {
    @Autowired(required = false)
    MonitorScreenSettingDao monitorScreenSettingDao;

    public int insert(MonitorScreenSetting monitorScreenSetting) {
        return this.monitorScreenSettingDao.insert(monitorScreenSetting);
    }

    public int update(MonitorScreenSetting monitorScreenSetting) {
        return this.monitorScreenSettingDao.update(monitorScreenSetting);
    }

    public int delete(int id) {
        return this.monitorScreenSettingDao.delete(id);
    }

    public int deleteAll() {
        return this.monitorScreenSettingDao.deleteAll();
    }

    public List<MonitorScreenSetting> getAllById(int id) {
        return this.monitorScreenSettingDao.getAllById(id);
    }

    public List<MonitorScreenSetting> getAll() {
        return this.monitorScreenSettingDao.getAll();
    }
}
