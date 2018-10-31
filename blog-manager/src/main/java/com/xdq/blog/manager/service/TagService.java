package com.xdq.blog.manager.service;

import com.xdq.blog.common.domain.Tag;

import java.util.List;

public interface TagService {
    List<Tag> findAll();

    void insert(String name);

    void delete(int id);

    void update(Tag tag);
}
