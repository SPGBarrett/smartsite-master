package com.barrett.smartsite.impl;

import com.barrett.smartsite.bean.MultiScreenSetting;
import com.barrett.smartsite.impl.MultiScreenSettingImpl;
import com.barrett.smartsite.mapper.MultiScreenSettingDao;
import com.barrett.smartsite.service.MultiScreenSettingService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: smartsite
 * @description:
 * @author: Barrett
 * @create: 2019-10-07 11:13
 **/
@Service
public class MultiScreenSettingImpl
        implements MultiScreenSettingService {
    @Autowired(required = false)
    MultiScreenSettingDao multiScreenSettingDao;

    public int insert(MultiScreenSetting multiScreenSetting) {
        return this.multiScreenSettingDao.insert(multiScreenSetting);
    }

    public int update(MultiScreenSetting multiScreenSetting) {
        return this.multiScreenSettingDao.update(multiScreenSetting);
    }

    public int delete(int id) {
        return this.multiScreenSettingDao.delete(id);
    }

    public int deleteAll() {
        return this.multiScreenSettingDao.deleteAll();
    }

    public List<MultiScreenSetting> getAllById(int id) {
        return this.multiScreenSettingDao.getAllById(id);
    }

    public List<MultiScreenSetting> getAll() {
        return this.multiScreenSettingDao.getAll();
    }
}