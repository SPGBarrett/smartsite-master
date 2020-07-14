package com.barrett.facedetectservice.impl;

import com.barrett.facedetectservice.bean.UserData;
import com.barrett.facedetectservice.mapper.UserDataMapper;
import com.barrett.facedetectservice.service.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: smartsite-master
 * @description:
 * @author: Barrett
 * @create: 2020-05-10 21:42
 **/
@Service
public class UserDataImpl implements UserDataService {
    @Autowired(required = false)
    UserDataMapper userDataMapper;

    @Override
    public int insert(UserData userData) {
        return userDataMapper.insert(userData);
    }

    @Override
    public int insertOrUpdate(UserData userData) {
        return userDataMapper.insertOrUpdate(userData);
    }

    @Override
    public int update(UserData userData) {
        return userDataMapper.update(userData);
    }

    @Override
    public int updateByWorkNo(UserData userData) {
        return userDataMapper.update(userData);
    }

    @Override
    public int deleteByGuid(int paramGuid) {
        return userDataMapper.deleteByGuid(paramGuid);
    }

    @Override
    public int deleteAll() {
        return userDataMapper.deleteAll();
    }

    @Override
    public List<UserData> getAllByGuid(String paramGuid) {
        return userDataMapper.getAllByGuid(paramGuid);
    }

    @Override
    public List<UserData> getAll() {
        return userDataMapper.getAll();
    }

    @Override
    public List<Integer> getIdByCardNo(String card_no) {
        return userDataMapper.getIdByCardNo(card_no);
    }

    @Override
    public List<UserData> getByCardNo(String card_no) {
        return userDataMapper.getByCardNo(card_no);
    }
}
