package com.barrett.smartsite.service;

import com.barrett.smartsite.bean.MultiFiles;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @program: smartsite
 * @description:
 * @author: Barrett
 * @create: 2019-10-07 11:44
 **/
public interface MultiFilesService {
    int insert(MultiFiles paramMultiFiles);

    int update(MultiFiles paramMultiFiles);

    int delete(@Param("id") int paramInt);

    int deleteAll();

    int deleteAllByParams(@Param("type") String typeString, @Param("parent") String parentString);

    List<MultiFiles> getAllById(@Param("id") int paramInt);

    List<MultiFiles> getAll();

    List<MultiFiles> getAllByParent(@Param("parent") String paramString);

    List<MultiFiles> getAllByType(@Param("type") String paramString);

    List<MultiFiles> getAllByParams(@Param("type") String typeString, @Param("parent") String parentString);
}
