package com.barrett.smartsite.service;

import com.barrett.smartsite.bean.ProgScreenSetting;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ProgScreenSettingService {
    int insert(ProgScreenSetting progScreenSetting);

    int update(ProgScreenSetting progScreenSetting);

    int delete(int paramInt);

    int deleteAll();

    List<ProgScreenSetting> getAllById(int paramInt);

    List<ProgScreenSetting> getAll();
}
