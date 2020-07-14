package com.barrett.aialertservice.service;

import com.barrett.aialertservice.bean.HeadInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface HeadInfoService {
    int insert(HeadInfo headInfo);

    int update(HeadInfo headInfo);

    int deleteByGuid(@Param("guid") int paramGuid);

    int deleteAll();

    List<HeadInfo> getAllByGuid(@Param("guid") String paramGuid);

    List<HeadInfo> getAllByParentID(@Param("parentID") String paramParentID);

    List<HeadInfo> getAll();
}
