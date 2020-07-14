package com.barrett.smartsite.impl;

import com.barrett.smartsite.bean.MultiFiles;
import com.barrett.smartsite.impl.MultiFilesImpl;
import com.barrett.smartsite.mapper.MultiFilesDao;
import com.barrett.smartsite.service.MultiFilesService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: smartsite
 * @description:
 * @author: Barrett
 * @create: 2019-10-07 11:13
 **/
@Service
public class MultiFilesImpl
        implements MultiFilesService {
    @Autowired(required = false)
    MultiFilesDao multiFilesDao;

    public int insert(MultiFiles multiFiles) {
        return this.multiFilesDao.insert(multiFiles);
    }

    public int update(MultiFiles multiFiles) {
        return this.multiFilesDao.update(multiFiles);
    }

    public int delete(int id) {
        return this.multiFilesDao.delete(id);
    }

    public int deleteAll() {
        return this.multiFilesDao.deleteAll();
    }

    @Override
    public int deleteAllByParams(String typeString, String parentString) {
        return this.multiFilesDao.deleteAllByParams(typeString, parentString);
    }

    public List<MultiFiles> getAllById(int id) {
        return this.multiFilesDao.getAllById(id);
    }

    public List<MultiFiles> getAll() {
        return this.multiFilesDao.getAll();
    }

    public List<MultiFiles> getAllByParent(String parent) {
        return this.multiFilesDao.getAllByParent(parent);
    }

    public List<MultiFiles> getAllByType(String type) {
        return this.multiFilesDao.getAllByType(type);
    }

    @Override
    public List<MultiFiles> getAllByParams(String typeString, String parentString) {
        return multiFilesDao.getAllByParams(typeString,parentString);
    }
}
