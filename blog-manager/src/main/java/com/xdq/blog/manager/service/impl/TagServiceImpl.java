package com.xdq.blog.manager.service.impl;

import com.xdq.blog.common.dao.TagDao;
import com.xdq.blog.common.domain.Tag;
import com.xdq.blog.manager.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TagServiceImpl implements TagService {
    @Autowired
    TagDao tagDao;

    @Override
    public List<Tag> findAll() {
        return tagDao.findAll();
    }

    @Override
    public void insert(String name) {
        int num = tagDao.insert(name);
        if (num==0) throw new RuntimeException("插入失败");
    }

    @Transactional
    @Override
    public void delete(int id) {
        int num = tagDao.delete(id);
        if (num==0) throw new RuntimeException("删除失败");
        tagDao.deleteRelation(id);
    }

    @Override
    public void update(Tag tag) {
        int num = tagDao.update(tag);
        if (num==0) throw new RuntimeException("更新失败");
    }
}
