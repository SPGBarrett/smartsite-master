package com.barrett.aialertservice.impl;

import com.barrett.aialertservice.bean.VestInfo;
import com.barrett.aialertservice.mapper.VestInfoMapper;
import com.barrett.aialertservice.service.VestInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: smartsite-master
 * @description:
 * @author: Barrett
 * @create: 2020-05-16 10:42
 **/
@Service
public class VestInfoImpl implements VestInfoService {
    @Autowired(required = false)
    VestInfoMapper vestInfoMapper;

    @Override
    public int insert(VestInfo vestInfo) {
        return vestInfoMapper.insert(vestInfo);
    }

    @Override
    public int update(VestInfo vestInfo) {
        return vestInfoMapper.update(vestInfo);
    }

    @Override
    public int deleteByGuid(int paramGuid) {
        return vestInfoMapper.deleteByGuid(paramGuid);
    }

    @Override
    public int deleteAll() {
        return vestInfoMapper.deleteAll();
    }

    @Override
    public List<VestInfo> getAllByGuid(String paramGuid) {
        return vestInfoMapper.getAllByGuid(paramGuid);
    }

    @Override
    public List<VestInfo> getAllByParentID(String paramParentID) {
        return vestInfoMapper.getAllByParentID(paramParentID);
    }

    @Override
    public List<VestInfo> getAll() {
        return vestInfoMapper.getAll();
    }
}
