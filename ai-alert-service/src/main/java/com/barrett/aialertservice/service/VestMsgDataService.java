package com.barrett.aialertservice.service;

import com.barrett.aialertservice.bean.VestMsgData;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface VestMsgDataService {
    int insert(VestMsgData vestMsgData);

    int update(VestMsgData vestMsgData);

    int deleteByGuid(@Param("guid") int paramGuid);

    int deleteAll();

    List<VestMsgData> getAllByGuid(@Param("guid") String paramGuid);

    List<VestMsgData> getAllByParentID(@Param("parentID") String paramParentID);

    List<VestMsgData> getAll();
}
