package com.xdq.blog.manager.controller;

import com.xdq.blog.common.domain.Article;
import com.xdq.blog.common.domain.Tag;
import com.xdq.blog.manager.service.ArticleService;
import com.xdq.blog.manager.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class PageController {

    @Autowired
    ArticleService articleService;

    @Autowired
    TagService tagService;

    @RequestMapping("/update_form")
    public String pageUpdateForm(Model model,@RequestParam(defaultValue = "1") Integer articleId){
        Article article = articleService.findById(articleId);
        List<Tag> hasTags = articleService.findTags(articleId);
        List<Tag> tags = tagService.findAll();
        List<Map<String,Object>> list=new ArrayList<>();
        for (Tag tag : tags) {
            Map<String,Object> map=new HashMap<>();
            map.put("id",tag.getId());
            map.put("name",tag.getName());
            map.put("has",hasTags.contains(tag));
            list.add(map);
        }
        model.addAttribute("article",article);
        model.addAttribute("tags",list);
        return "update_form";
    }

    @RequestMapping("/write")
    public String pageWrite(Model model,@RequestParam(defaultValue = "1") Integer articleId){
        Article article = articleService.findById(articleId);
        model.addAttribute("article",article);
        return "write";
    }
}
