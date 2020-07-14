package com.barrett.smartsite.impl;

import com.barrett.smartsite.bean.TextContent;
import com.barrett.smartsite.impl.TextContentImpl;
import com.barrett.smartsite.mapper.TextContentDao;
import com.barrett.smartsite.service.TextContentService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: smartsite
 * @description:
 * @author: Barrett
 * @create: 2019-10-07 11:15
 **/
@Service
public class TextContentImpl
        implements TextContentService {
    @Autowired(required = false)
    TextContentDao textContentDao;

    public int insert(TextContent textContent) {
        return this.textContentDao.insert(textContent);
    }

    public int update(TextContent textContent) {
        return this.textContentDao.update(textContent);
    }

    public int delete(int id) {
        return this.textContentDao.delete(id);
    }

    public int deleteAll() {
        return this.textContentDao.deleteAll();
    }

    public List<TextContent> getAllById(int id) {
        return this.textContentDao.getAllById(id);
    }

    public List<TextContent> getAll() {
        return this.textContentDao.getAll();
    }
}
