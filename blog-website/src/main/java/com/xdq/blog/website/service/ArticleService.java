package com.xdq.blog.website.service;

import com.xdq.blog.common.domain.Article;
import com.xdq.blog.common.domain.PageResult;
import com.xdq.blog.common.domain.Tag;

import java.util.List;
import java.util.Map;

public interface ArticleService {
    PageResult find(int pageNum);

    List<Article> latest(int num);

    List<Article> hot(int num);

    PageResult findByTag(Integer tagId, Integer pageNum);

    PageResult findByTitle(String title, Integer pageNum);

    Article view(Integer id);

    List<Tag> findTags(Integer id);

    Map<String, Article> preAndNextArticle(Integer id);
}
