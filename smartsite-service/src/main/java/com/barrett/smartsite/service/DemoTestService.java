package com.barrett.smartsite.service;

import com.barrett.smartsite.bean.DemoTest;

import java.util.List;

/**
 * @program: smartsite
 * @description:
 * @author: Barrett
 * @create: 2019-10-07 11:41
 **/
public interface DemoTestService {
    List<DemoTest> findAll();

    int insert(String paramString1, String paramString2, String paramString3);
}
