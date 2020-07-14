package com.barrett.aialertservice.service;

import com.barrett.aialertservice.bean.AlertMsg;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AlertMsgService {
    int insert(AlertMsg alertMsg);

    int update(AlertMsg alertMsg);

    int deleteByGuid(@Param("guid") int paramGuid);

    int deleteAll();

    List<AlertMsg> getAllByGuid(@Param("guid") String paramGuid);

    List<AlertMsg> getAll();
}
