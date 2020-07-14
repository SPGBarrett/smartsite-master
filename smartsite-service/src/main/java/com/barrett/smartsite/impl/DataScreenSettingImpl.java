package com.barrett.smartsite.impl;

import com.barrett.smartsite.bean.DataScreenSetting;
import com.barrett.smartsite.impl.DataScreenSettingImpl;
import com.barrett.smartsite.mapper.DataScreenSettingDao;
import com.barrett.smartsite.service.DataScreenSettingService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: smartsite
 * @description:
 * @author: Barrett
 * @create: 2019-10-07 11:08
 **/
@Service
public class DataScreenSettingImpl
        implements DataScreenSettingService {
    @Autowired(required = false)
    DataScreenSettingDao dataScreenSettingDao;

    public int insert(DataScreenSetting dataScreenSetting) {
        return this.dataScreenSettingDao.insert(dataScreenSetting);
    }

    public int update(DataScreenSetting dataScreenSetting) {
        return this.dataScreenSettingDao.update(dataScreenSetting);
    }

    public int delete(int id) {
        return this.dataScreenSettingDao.delete(id);
    }

    public int deleteAll() {
        return this.dataScreenSettingDao.deleteAll();
    }

    public List<DataScreenSetting> getAllById(int id) {
        return this.dataScreenSettingDao.getAllById(id);
    }

    public List<DataScreenSetting> getAll() {
        return this.dataScreenSettingDao.getAll();
    }
}
