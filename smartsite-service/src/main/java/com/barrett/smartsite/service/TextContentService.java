package com.barrett.smartsite.service;

import com.barrett.smartsite.bean.TextContent;

import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * @program: smartsite
 * @description:
 * @author: Barrett
 * @create: 2019-10-07 14:22
 **/
public interface TextContentService {
    int insert(TextContent paramTextContent);

    int update(TextContent paramTextContent);

    int delete(@Param("id") int paramInt);

    int deleteAll();

    List<TextContent> getAllById(@Param("id") int paramInt);

    List<TextContent> getAll();
}
