package com.barrett.aialertservice.impl;

import com.barrett.aialertservice.bean.HeadInfo;
import com.barrett.aialertservice.mapper.HeadInfoMapper;
import com.barrett.aialertservice.service.HeadInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: smartsite-master
 * @description:
 * @author: Barrett
 * @create: 2020-05-05 14:57
 **/
@Service
public class HeadInfoImpl implements HeadInfoService {
    @Autowired(required = false)
    HeadInfoMapper headInfoMapper;

    @Override
    public int insert(HeadInfo headInfo) {
        return headInfoMapper.insert(headInfo);
    }

    @Override
    public int update(HeadInfo headInfo) {
        return headInfoMapper.update(headInfo);
    }

    @Override
    public int deleteByGuid(int paramGuid) {
        return headInfoMapper.deleteByGuid(paramGuid);
    }

    @Override
    public int deleteAll() {
        return headInfoMapper.deleteAll();
    }

    @Override
    public List<HeadInfo> getAllByGuid(String paramGuid) {
        return headInfoMapper.getAllByGuid(paramGuid);
    }

    @Override
    public List<HeadInfo> getAllByParentID(String paramParentID) {
        return headInfoMapper.getAllByParentID(paramParentID);
    }

    @Override
    public List<HeadInfo> getAll() {
        return headInfoMapper.getAll();
    }
}
