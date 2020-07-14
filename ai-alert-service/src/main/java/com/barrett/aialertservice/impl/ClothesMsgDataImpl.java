package com.barrett.aialertservice.impl;

import com.barrett.aialertservice.bean.ClothesMsgData;
import com.barrett.aialertservice.mapper.ClothesMsgDataMapper;
import com.barrett.aialertservice.service.ClothesMsgDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: smartsite-master
 * @description:
 * @author: Barrett
 * @create: 2020-05-16 10:38
 **/
@Service
public class ClothesMsgDataImpl implements ClothesMsgDataService {
    @Autowired(required = false)
    ClothesMsgDataMapper clothesMsgDataMapper;

    @Override
    public int insert(ClothesMsgData clothesMsgData) {
        return clothesMsgDataMapper.insert(clothesMsgData);
    }

    @Override
    public int update(ClothesMsgData clothesMsgData) {
        return clothesMsgDataMapper.update(clothesMsgData);
    }

    @Override
    public int deleteByGuid(int paramGuid) {
        return clothesMsgDataMapper.deleteByGuid(paramGuid);
    }

    @Override
    public int deleteAll() {
        return clothesMsgDataMapper.deleteAll();
    }

    @Override
    public List<ClothesMsgData> getAllByGuid(String paramGuid) {
        return clothesMsgDataMapper.getAllByGuid(paramGuid);
    }

    @Override
    public List<ClothesMsgData> getAllByParentID(String paramParentID) {
        return clothesMsgDataMapper.getAllByParentID(paramParentID);
    }

    @Override
    public List<ClothesMsgData> getAll() {
        return clothesMsgDataMapper.getAll();
    }
}
