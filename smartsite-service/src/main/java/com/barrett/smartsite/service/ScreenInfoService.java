package com.barrett.smartsite.service;

import com.barrett.smartsite.bean.ScreenInfo;

import java.util.List;

/**
 * @program: smartsite
 * @description:
 * @author: Barrett
 * @create: 2019-10-07 13:55
 **/
public interface ScreenInfoService {
    int insert(ScreenInfo paramScreenInfo);

    int update(ScreenInfo paramScreenInfo);

    int delete(int paramInt);

    int deleteAll();

    List<ScreenInfo> getAllById(int paramInt);

    List<ScreenInfo> getAll();
}
