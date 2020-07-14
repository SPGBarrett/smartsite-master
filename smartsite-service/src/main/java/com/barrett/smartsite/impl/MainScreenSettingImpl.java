package com.barrett.smartsite.impl;

import com.barrett.smartsite.bean.MainScreenSetting;
import com.barrett.smartsite.impl.MainScreenSettingImpl;
import com.barrett.smartsite.mapper.MainScreenSettingDao;
import com.barrett.smartsite.service.MainScreenSettingService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: smartsite
 * @description:
 * @author: Barrett
 * @create: 2019-10-07 11:10
 **/
@Service
public class MainScreenSettingImpl
        implements MainScreenSettingService {
    @Autowired(required = false)
    MainScreenSettingDao mainScreenSettingDao;

    public int insert(MainScreenSetting mainScreenSetting) {
        return this.mainScreenSettingDao.insert(mainScreenSetting);
    }

    public int update(MainScreenSetting mainScreenSetting) {
        return this.mainScreenSettingDao.update(mainScreenSetting);
    }

    @Override
    public int updateIsloop(int isloop) {
        return this.mainScreenSettingDao.updateIsloop(isloop);
    }

    public int delete(int id) {
        return this.mainScreenSettingDao.delete(id);
    }

    public int deleteAll() {
        return this.mainScreenSettingDao.deleteAll();
    }

    public List<MainScreenSetting> getAllById(int id) {
        return this.mainScreenSettingDao.getAllById(id);
    }

    public List<MainScreenSetting> getAll() {
        return this.mainScreenSettingDao.getAll();
    }
}
