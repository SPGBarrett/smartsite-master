package com.barrett.aialertservice.service;

import com.barrett.aialertservice.bean.ClothesMsgData;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ClothesMsgDataService {
    int insert(ClothesMsgData clothesMsgData);

    int update(ClothesMsgData clothesMsgData);

    int deleteByGuid(@Param("guid") int paramGuid);

    int deleteAll();

    List<ClothesMsgData> getAllByGuid(@Param("guid") String paramGuid);

    List<ClothesMsgData> getAllByParentID(@Param("parentID") String paramParentID);

    List<ClothesMsgData> getAll();
}
