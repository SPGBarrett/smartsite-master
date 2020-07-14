package com.barrett.smartsite.impl;

import com.barrett.smartsite.bean.ScreenInfo;
import com.barrett.smartsite.impl.ScreenInfoImpl;
import com.barrett.smartsite.mapper.ScreenInfoDao;
import com.barrett.smartsite.service.ScreenInfoService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: smartsite
 * @description:
 * @author: Barrett
 * @create: 2019-10-07 11:14
 **/

@Service
public class ScreenInfoImpl
        implements ScreenInfoService {
    @Autowired(required = false)
    ScreenInfoDao screenInfoDao;

    public int insert(ScreenInfo screenInfo) {
        return this.screenInfoDao.insert(screenInfo);
    }

    public int update(ScreenInfo screenInfo) {
        return this.screenInfoDao.update(screenInfo);
    }

    public int delete(int id) {
        return this.screenInfoDao.delete(id);
    }

    public int deleteAll() {
        return this.screenInfoDao.deleteAll();
    }

    public List<ScreenInfo> getAllById(int id) {
        return this.screenInfoDao.getAllById(id);
    }

    public List<ScreenInfo> getAll() {
        return this.screenInfoDao.getAll();
    }
}
