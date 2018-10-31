package com.xdq.blog.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xdq.blog.common.dao.ArticleDao;
import com.xdq.blog.common.domain.Article;
import com.xdq.blog.common.domain.Tag;
import com.xdq.blog.manager.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleDao articleDao;

    @Override
    public Article findById(int id) {
        return articleDao.findById(id);
    }

    @Override
    public void updateContent(String content, Integer id) {
        int update = articleDao.updateContent(content,id);
        if (update == 0) throw new RuntimeException("更新失败");
    }

    @Transactional
    @Override
    public void updateMata(Article article, Integer[] tags) {
        articleDao.updateMata(article);
        articleDao.deleteTags(article.getId());
        for (Integer tag : tags) {
            articleDao.addTag(article.getId(),tag);
        }
    }

    @Override
    public PageInfo<Article> search(Integer page, Integer limit, String title) {
        PageHelper.startPage(page,limit);
        List<Article> all = articleDao.findByTitle(title);
        PageInfo<Article> pageInfo = new PageInfo<>(all);
        return pageInfo;
    }

    @Transactional
    @Override
    public void delete(Integer[] ids) {
        for (Integer id : ids) {
            articleDao.delete(id);
        }
    }

    @Override
    public void add(String title) {
        int insert = articleDao.insert(title);
        if (insert == 0) throw new RuntimeException("插入失败");
    }

    @Override
    public PageInfo<Article> findByPage(Integer page, Integer limit) {
        PageHelper.startPage(page,limit);
        List<Article> all = articleDao.findAll();
        PageInfo<Article> pageInfo = new PageInfo<>(all);
        return pageInfo;
    }

    @Override
    public List<Tag> findTags(Integer id) {
        return articleDao.findTags(id);
    }
}
