package com.barrett.smartsite.impl;

import com.barrett.smartsite.bean.SelectionDict;
import com.barrett.smartsite.impl.SelectionDictImpl;
import com.barrett.smartsite.mapper.SelectionDictDao;
import com.barrett.smartsite.service.SelectionDictService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: smartsite
 * @description:
 * @author: Barrett
 * @create: 2019-10-07 11:14
 **/
@Service
public class SelectionDictImpl
        implements SelectionDictService {
    @Autowired(required = false)
    SelectionDictDao selectionDictDao;

    public int insert(SelectionDict selectionDict) {
        return this.selectionDictDao.insert(selectionDict);
    }

    public int update(SelectionDict selectionDict) {
        return this.selectionDictDao.update(selectionDict);
    }

    public int delete(int id) {
        return this.selectionDictDao.delete(id);
    }

    public int deleteAll() {
        return this.selectionDictDao.deleteAll();
    }

    public List<SelectionDict> getAllById(int id) {
        return this.selectionDictDao.getAllById(id);
    }

    public List<SelectionDict> getAll() {
        return this.selectionDictDao.getAll();
    }
}
