package com.xdq.blog.manager.controller;

import com.github.pagehelper.PageInfo;
import com.xdq.blog.common.domain.Article;
import com.xdq.blog.manager.response.Result;
import com.xdq.blog.manager.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ArticleController {
    @Autowired
    ArticleService articleService;

    @RequestMapping("/article/save_content")
    public Result saveContent(String content, Integer id) {
        try {
            articleService.updateContent(content, id);
            return Result.success("成功");
        } catch (Exception e) {
            return Result.failed(e.getMessage());
        }
    }

    @RequestMapping("/article/save_mata")
    public Result saveContent(Article article,@RequestParam("tags[]") Integer []tags) {
        try {
            articleService.updateMata(article, tags);
            return Result.success("成功");
        } catch (Exception e) {
            return Result.failed(e.getMessage());
        }
    }

    @RequestMapping("/articles")
    public Result findByPage(Integer page, Integer limit) {
        try {
            PageInfo<Article> pageInfo = articleService.findByPage(page, limit);
            Map<String, Object> map = new HashMap<>();
            map.put("code", 0);
            map.put("msg", "");
            map.put("count", pageInfo.getTotal());
            map.put("data", pageInfo.getList());
            return Result.success(map);
        } catch (Exception e) {
            return Result.failed(e.getMessage());
        }
    }

    @RequestMapping("/articles/search")
    public Result search(Integer page, Integer limit, String title) {
        try {
            PageInfo<Article> pageInfo = articleService.search(page, limit ,title);
            Map<String, Object> map = new HashMap<>();
            map.put("code", 0);
            map.put("msg", "");
            map.put("count", pageInfo.getTotal());
            map.put("data", pageInfo.getList());
            return Result.success(map);
        } catch (Exception e) {
            return Result.failed(e.getMessage());
        }
    }

    @RequestMapping("/article/add")
    public Result add(String title){
        try {
            articleService.add(title);
            return Result.success("成功");
        } catch (Exception e) {
            return Result.failed(e.getMessage());
        }
    }

    @RequestMapping("/article/delete")
    public Result delete(@RequestParam("ids[]") Integer[] ids){
        try {
            articleService.delete(ids);
            return Result.success("成功");
        } catch (Exception e) {
            return Result.failed("失败");
        }
    }
}
