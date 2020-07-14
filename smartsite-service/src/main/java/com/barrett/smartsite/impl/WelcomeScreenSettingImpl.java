package com.barrett.smartsite.impl;

import com.barrett.smartsite.bean.WelcomeScreenSetting;
import com.barrett.smartsite.impl.WelcomeScreenSettingImpl;
import com.barrett.smartsite.mapper.WelcomeScreenSettingDao;
import com.barrett.smartsite.service.WelcomeScreenSettingService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: smartsite
 * @description:
 * @author: Barrett
 * @create: 2019-10-07 11:16
 **/
@Service
public class WelcomeScreenSettingImpl
        implements WelcomeScreenSettingService {
    @Autowired(required = false)
    WelcomeScreenSettingDao welcomeScreenSettingDao;

    public int insert(WelcomeScreenSetting welcomeScreenSetting) {
        return this.welcomeScreenSettingDao.insert(welcomeScreenSetting);
    }

    public int update(WelcomeScreenSetting welcomeScreenSetting) {
        return this.welcomeScreenSettingDao.update(welcomeScreenSetting);
    }

    @Override
    public int updatePicPath(int id, String pic_path) {
        return this.welcomeScreenSettingDao.updatePicPath(id, pic_path);
    }

    public int delete(int id) {
        return this.welcomeScreenSettingDao.delete(id);
    }

    public int deleteAll() {
        return this.welcomeScreenSettingDao.deleteAll();
    }

    public List<WelcomeScreenSetting> getAllById(int id) {
        return this.welcomeScreenSettingDao.getAllById(id);
    }

    public List<WelcomeScreenSetting> getAll() {
        return this.welcomeScreenSettingDao.getAll();
    }
}
