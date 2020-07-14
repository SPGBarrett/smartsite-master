package com.barrett.smartsite.impl;

import com.barrett.smartsite.bean.ProgScreenSetting;
import com.barrett.smartsite.mapper.ProgScreenSettingDao;
import com.barrett.smartsite.service.ProgScreenSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: smartsite
 * @description: Implementation of service
 * @author: Barrett
 * @create: 2020-01-02 19:47
 **/
@Service
public class ProgScreenSettingImpl implements ProgScreenSettingService {
    @Autowired(required = false)
    ProgScreenSettingDao progScreenSettingDao;

    @Override
    public int insert(ProgScreenSetting progScreenSetting) {
        return progScreenSettingDao.insert(progScreenSetting);
    }

    @Override
    public int update(ProgScreenSetting progScreenSetting) {
        return progScreenSettingDao.update(progScreenSetting);
    }

    @Override
    public int delete(int paramInt) {
        return progScreenSettingDao.delete(paramInt);
    }

    @Override
    public int deleteAll() {
        return progScreenSettingDao.deleteAll();
    }

    @Override
    public List<ProgScreenSetting> getAllById(int paramInt) {
        List<ProgScreenSetting> resultList = progScreenSettingDao.getAllById(paramInt);
        return resultList;
    }

    @Override
    public List<ProgScreenSetting> getAll() {
        List<ProgScreenSetting> resultList = progScreenSettingDao.getAll();
        return resultList;
    }
}
