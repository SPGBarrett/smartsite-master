package com.barrett.aialertservice.impl;

import com.barrett.aialertservice.bean.VestMsgData;
import com.barrett.aialertservice.mapper.VestMsgDataMapper;
import com.barrett.aialertservice.service.VestMsgDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: smartsite-master
 * @description:
 * @author: Barrett
 * @create: 2020-05-16 10:53
 **/
@Service
public class VestMsgDataImpl implements VestMsgDataService {
    @Autowired(required = false)
    VestMsgDataMapper vestMsgDataMapper;

    @Override
    public int insert(VestMsgData vestMsgData) {
        return vestMsgDataMapper.insert(vestMsgData);
    }

    @Override
    public int update(VestMsgData vestMsgData) {
        return vestMsgDataMapper.update(vestMsgData);
    }

    @Override
    public int deleteByGuid(int paramGuid) {
        return vestMsgDataMapper.deleteByGuid(paramGuid);
    }

    @Override
    public int deleteAll() {
        return vestMsgDataMapper.deleteAll();
    }

    @Override
    public List<VestMsgData> getAllByGuid(String paramGuid) {
        return vestMsgDataMapper.getAllByGuid(paramGuid);
    }

    @Override
    public List<VestMsgData> getAllByParentID(String paramParentID) {
        return vestMsgDataMapper.getAllByParentID(paramParentID);
    }

    @Override
    public List<VestMsgData> getAll() {
        return vestMsgDataMapper.getAll();
    }
}
