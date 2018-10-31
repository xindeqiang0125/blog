package com.xdq.blog.website.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xdq.blog.common.dao.ArticleDao;
import com.xdq.blog.common.domain.Article;
import com.xdq.blog.common.domain.PageResult;
import com.xdq.blog.common.domain.Tag;
import com.xdq.blog.website.constant.Constant;
import com.xdq.blog.website.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ArticleDao articleDao;

    @Override
    public PageResult find(int pageNum) {
        PageHelper.startPage(pageNum, Constant.PAGE_SIZE);
        List<Article> all = articleDao.findAllPublished();
        PageInfo info = new PageInfo(all);
        return new PageResult(info);
    }

    @Override
    public List<Article> latest(int num) {
        return articleDao.getLatest(num);
    }

    @Override
    public List<Article> hot(int num) {
        return articleDao.getHot(num);
    }

    @Override
    public PageResult findByTag(Integer tagId, Integer pageNum) {
        PageHelper.startPage(pageNum, Constant.PAGE_SIZE);
        List<Article> all = articleDao.findByTag(tagId);
        PageInfo info = new PageInfo(all);
        return new PageResult(info);
    }

    @Override
    public PageResult findByTitle(String title, Integer pageNum) {
        PageHelper.startPage(pageNum, Constant.PAGE_SIZE);
        List<Article> all = articleDao.findByTitlePublished(title);
        PageInfo info = new PageInfo(all);
        return new PageResult(info);
    }

    @Transactional
    @Override
    public Article view(Integer id) {
        articleDao.increase(id);
        Article article = articleDao.findById(id);
        return article;
    }

    @Override
    public List<Tag> findTags(Integer id) {
        return articleDao.findTags(id);
    }

    @Transactional
    @Override
    public Map<String, Article> preAndNextArticle(Integer id) {
        Map<String, Article> map = new HashMap<>();
        map.put("pre",articleDao.preArticle(id));
        map.put("next",articleDao.nextArticle(id));
        return map;
    }
}
