package com.barrett.aialertservice.service;

import com.barrett.aialertservice.bean.HelmetMsgData;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface HelmetMsgDataService {
    int insert(HelmetMsgData helmetMsgData);

    int update(HelmetMsgData helmetMsgData);

    int deleteByGuid(@Param("guid") int paramGuid);

    int deleteAll();

    List<HelmetMsgData> getAllByGuid(@Param("guid") String paramGuid);


    List<HelmetMsgData> getAllByParentID(@Param("parentID") String paramParentID);


    List<HelmetMsgData> getAll();
}
