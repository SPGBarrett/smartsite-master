package com.barrett.aialertservice.impl;

import com.barrett.aialertservice.bean.AlertMsg;
import com.barrett.aialertservice.mapper.AlertMsgMapper;
import com.barrett.aialertservice.service.AlertMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: smartsite-master
 * @description: Implementation of dao funcs of table alert_msg
 * @author: Barrett
 * @create: 2020-05-16 09:46
 **/
@Service
public class AlertMsgImpl implements AlertMsgService {
    @Autowired(required = false)
    AlertMsgMapper alertMsgMapper;

    @Override
    public int insert(AlertMsg alertMsg) {
        return alertMsgMapper.insert(alertMsg);
    }

    @Override
    public int update(AlertMsg alertMsg) {
        return alertMsgMapper.update(alertMsg);
    }

    @Override
    public int deleteByGuid(int paramGuid) {
        return alertMsgMapper.deleteByGuid(paramGuid);
    }

    @Override
    public int deleteAll() {
        return alertMsgMapper.deleteAll();
    }

    @Override
    public List<AlertMsg> getAllByGuid(String paramGuid) {
        return alertMsgMapper.getAllByGuid(paramGuid);
    }

    @Override
    public List<AlertMsg> getAll() {
        return alertMsgMapper.getAll();
    }
}
