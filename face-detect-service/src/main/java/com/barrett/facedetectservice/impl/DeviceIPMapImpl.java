package com.barrett.facedetectservice.impl;

import com.barrett.facedetectservice.bean.DeviceIPMap;
import com.barrett.facedetectservice.mapper.DeviceIPMapMapper;
import com.barrett.facedetectservice.service.DeviceIPMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: smartsite-master
 * @description:
 * @author: Barrett
 * @create: 2020-05-10 21:49
 **/
@Service
public class DeviceIPMapImpl implements DeviceIPMapService {
    @Autowired(required = false)
    DeviceIPMapMapper deviceIPMapMapper;

    @Override
    public int insert(DeviceIPMap deviceIPMap) {
        return deviceIPMapMapper.insert(deviceIPMap);
    }

    @Override
    public int update(DeviceIPMap deviceIPMap) {
        return deviceIPMapMapper.update(deviceIPMap);
    }

    @Override
    public int deleteByGuid(int paramGuid) {
        return deviceIPMapMapper.deleteByGuid(paramGuid);
    }

    @Override
    public int deleteAll() {
        return deviceIPMapMapper.deleteAll();
    }

    @Override
    public List<DeviceIPMap> getAllByGuid(String paramGuid) {
        return deviceIPMapMapper.getAllByGuid(paramGuid);
    }

    @Override
    public List<DeviceIPMap> getAll() {
        return deviceIPMapMapper.getAll();
    }

    @Override
    public List<DeviceIPMap> getByIP(String ip) {
        return deviceIPMapMapper.getByIP(ip);
    }

    @Override
    public List<String> getDeviceNoByIP(String ip) {
        return deviceIPMapMapper.getDeviceNoByIP(ip);
    }

    @Override
    public List<String> getAllDeviceIP() {
        return deviceIPMapMapper.getAllDeviceIP();
    }

    @Override
    public List<DeviceIPMap> getByIpAndPort(String ip, String port) {
        return deviceIPMapMapper.getByIpAndPort(ip, port);
    }
}
