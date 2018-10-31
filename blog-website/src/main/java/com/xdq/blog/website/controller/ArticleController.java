package com.xdq.blog.website.controller;

import com.xdq.blog.common.domain.Article;
import com.xdq.blog.common.domain.PageResult;
import com.xdq.blog.common.domain.Tag;
import com.xdq.blog.website.constant.Constant;
import com.xdq.blog.website.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class ArticleController {
    @Autowired
    ArticleService articleService;

    @GetMapping("/articles/latest")
    public List<Article> latest(){
        return articleService.latest(Constant.LATEST_NUM);
    }

    @GetMapping("/articles/hot")
    public List<Article> hot(){
        return articleService.hot(Constant.HOT_NUM);
    }

    @GetMapping("/articles/page_{pageNum}")
    public PageResult articles(@PathVariable Integer pageNum){
        return articleService.find(pageNum);
    }

    @GetMapping("/articles/tag_{tagId}/page_{pageNum}")
    public PageResult articlesByTag(@PathVariable Integer tagId,@PathVariable Integer pageNum){
        return articleService.findByTag(tagId,pageNum);
    }
    @GetMapping("/articles/{title}/page_{pageNum}")
    public PageResult articlesByTitle(@PathVariable String title,@PathVariable Integer pageNum){
        return articleService.findByTitle(title,pageNum);
    }

    @GetMapping("/articles/{id}")
    public Article articlesByTitle(@PathVariable Integer id){
        return articleService.view(id);
    }

    @GetMapping("/articles/{id}/tags")
    public List<Tag> tagsByArticleId(@PathVariable Integer id){
        return articleService.findTags(id);
    }

    @GetMapping("/articles/{id}/pre_next")
    public Map<String,Article> preAndNextArticle(@PathVariable Integer id){
        return articleService.preAndNextArticle(id);
    }
}
