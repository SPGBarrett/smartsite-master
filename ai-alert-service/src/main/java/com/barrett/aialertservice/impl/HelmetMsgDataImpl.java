package com.barrett.aialertservice.impl;

import com.barrett.aialertservice.bean.HelmetMsgData;
import com.barrett.aialertservice.mapper.HelmetMsgDataMapper;
import com.barrett.aialertservice.service.HelmetMsgDataService;
import org.apache.ibatis.plugin.Signature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: smartsite-master
 * @description:
 * @author: Barrett
 * @create: 2020-05-05 14:58
 **/
@Service
public class HelmetMsgDataImpl implements HelmetMsgDataService {
    @Autowired(required = false)
    HelmetMsgDataMapper helmetMsgDataMapper;

    @Override
    public int insert(HelmetMsgData helmetMsgData) {
        return helmetMsgDataMapper.insert(helmetMsgData);
    }

    @Override
    public int update(HelmetMsgData helmetMsgData) {
        return helmetMsgDataMapper.update(helmetMsgData);
    }

    @Override
    public int deleteByGuid(int paramGuid) {
        return helmetMsgDataMapper.deleteByGuid(paramGuid);
    }

    @Override
    public int deleteAll() {
        return helmetMsgDataMapper.deleteAll();
    }

    @Override
    public List<HelmetMsgData> getAllByGuid(String paramGuid) {
        return helmetMsgDataMapper.getAllByGuid(paramGuid);
    }

    @Override
    public List<HelmetMsgData> getAllByParentID(String paramParentID) {
        return helmetMsgDataMapper.getAllByParentID(paramParentID);
    }

    @Override
    public List<HelmetMsgData> getAll() {
        return helmetMsgDataMapper.getAll();
    }
}
