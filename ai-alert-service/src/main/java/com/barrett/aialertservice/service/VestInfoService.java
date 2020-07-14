package com.barrett.aialertservice.service;

import com.barrett.aialertservice.bean.VestInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface VestInfoService {
    int insert(VestInfo vestInfo);

    int update(VestInfo vestInfo);

    int deleteByGuid(@Param("guid") int paramGuid);

    int deleteAll();

    List<VestInfo> getAllByGuid(@Param("guid") String paramGuid);

    List<VestInfo> getAllByParentID(@Param("parentID") String paramParentID);

    List<VestInfo> getAll();
}
