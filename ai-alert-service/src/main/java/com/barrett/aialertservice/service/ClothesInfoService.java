package com.barrett.aialertservice.service;

import com.barrett.aialertservice.bean.ClothesInfo;
import com.barrett.aialertservice.bean.HeadInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ClothesInfoService {
    int insert(ClothesInfo clothesInfo);

    int update(ClothesInfo clothesInfo);

    int deleteByGuid(@Param("guid") int paramGuid);

    int deleteAll();

    List<ClothesInfo> getAllByGuid(@Param("guid") String paramGuid);

    List<ClothesInfo> getAllByParentID(@Param("parentID") String paramParentID);

    List<ClothesInfo> getAll();
}
