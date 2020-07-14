package com.barrett.smartsite.service;

import com.barrett.smartsite.bean.SelectionDict;

import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * @program: smartsite
 * @description:
 * @author: Barrett
 * @create: 2019-10-07 14:21
 **/
public interface SelectionDictService {
    int insert(SelectionDict paramSelectionDict);

    int update(SelectionDict paramSelectionDict);

    int delete(@Param("id") int paramInt);

    int deleteAll();

    List<SelectionDict> getAllById(@Param("id") int paramInt);

    List<SelectionDict> getAll();
}
