package com.xdq.blog.manager.service;

import com.github.pagehelper.PageInfo;
import com.xdq.blog.common.domain.Article;
import com.xdq.blog.common.domain.Tag;

import java.util.List;

public interface ArticleService {
    Article findById(int id);

    void updateContent(String content, Integer id);

    void delete(Integer[] ids);

    void add(String title);

    PageInfo<Article> findByPage(Integer page, Integer limit);

    List<Tag> findTags(Integer id);

    void updateMata(Article article, Integer[] tags);

    PageInfo<Article> search(Integer page, Integer limit, String title);
}
