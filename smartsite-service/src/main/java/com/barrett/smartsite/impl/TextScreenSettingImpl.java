package com.barrett.smartsite.impl;

import com.barrett.smartsite.bean.TextScreenSetting;
import com.barrett.smartsite.impl.TextScreenSettingImpl;
import com.barrett.smartsite.mapper.TextScreenSettingDao;
import com.barrett.smartsite.service.TextScreenSettingService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: smartsite
 * @description:
 * @author: Barrett
 * @create: 2019-10-07 11:15
 **/
@Service
public class TextScreenSettingImpl
        implements TextScreenSettingService {
    @Autowired(required = false)
    TextScreenSettingDao textScreenSettingDao;

    public int insert(TextScreenSetting textScreenSetting) {
        return this.textScreenSettingDao.insert(textScreenSetting);
    }

    public int update(TextScreenSetting textScreenSetting) {
        return this.textScreenSettingDao.update(textScreenSetting);
    }

    public int delete(int id) {
        return this.textScreenSettingDao.delete(id);
    }

    public int deleteAll() {
        return this.textScreenSettingDao.deleteAll();
    }

    public List<TextScreenSetting> getAllById(int id) {
        return this.textScreenSettingDao.getAllById(id);
    }

    public List<TextScreenSetting> getAll() {
        return this.textScreenSettingDao.getAll();
    }
}
