package com.barrett.aialertservice.impl;

import com.barrett.aialertservice.bean.ClothesInfo;
import com.barrett.aialertservice.mapper.ClothesInfoMapper;
import com.barrett.aialertservice.service.ClothesInfoService;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: smartsite-master
 * @description:
 * @author: Barrett
 * @create: 2020-05-16 10:35
 **/
@Service
public class ClothesInfoImpl implements ClothesInfoService {
    @Autowired(required = false)
    ClothesInfoMapper clothesInfoMapper;

    @Override
    public int insert(ClothesInfo clothesInfo) {
        return clothesInfoMapper.insert(clothesInfo);
    }

    @Override
    public int update(ClothesInfo clothesInfo) {
        return clothesInfoMapper.update(clothesInfo);
    }

    @Override
    public int deleteByGuid(int paramGuid) {
        return clothesInfoMapper.deleteByGuid(paramGuid);
    }

    @Override
    public int deleteAll() {
        return clothesInfoMapper.deleteAll();
    }

    @Override
    public List<ClothesInfo> getAllByGuid(String paramGuid) {
        return clothesInfoMapper.getAllByGuid(paramGuid);
    }

    @Override
    public List<ClothesInfo> getAllByParentID(String paramParentID) {
        return clothesInfoMapper.getAllByParentID(paramParentID);
    }

    @Override
    public List<ClothesInfo> getAll() {
        return clothesInfoMapper.getAll();
    }
}
