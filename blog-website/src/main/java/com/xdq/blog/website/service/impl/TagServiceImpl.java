package com.xdq.blog.website.service.impl;

import com.xdq.blog.common.dao.TagDao;
import com.xdq.blog.common.domain.Tag;
import com.xdq.blog.website.service.TagService;
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
}
