package com.barrett.smartsite.impl;

import com.barrett.smartsite.bean.DemoTest;
import com.barrett.smartsite.impl.DemoTestServiceImpl;
import com.barrett.smartsite.mapper.DemoTestDao;
import com.barrett.smartsite.service.DemoTestService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: smartsite
 * @description:
 * @author: Barrett
 * @create: 2019-10-07 11:09
 **/
@Service
public class DemoTestServiceImpl
        implements DemoTestService {
    @Autowired(required = false)
    private DemoTestDao demoTestDao;

    public List<DemoTest> findAll() {
        return this.demoTestDao.findAll();
    }

    public int insert(String paramOne, String paramTwo, String demoOne) {
        return this.demoTestDao.insert(paramOne, paramTwo, demoOne);
    }
}

