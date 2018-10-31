package com.xdq.blog.manager.controller;

import com.xdq.blog.common.domain.Tag;
import com.xdq.blog.manager.response.Result;
import com.xdq.blog.manager.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TagController {
    @Autowired
    TagService tagService;

    @RequestMapping("/tags")
    public Result findAll(){
        try {
            List<Tag> all = tagService.findAll();
            return Result.success(all);
        } catch (Exception e) {
            return Result.failed("失败");
        }
    }

    @RequestMapping("/tags/delete")
    public Result delete(Integer id){
        try {
            tagService.delete(id);
            return Result.success("成功");
        } catch (Exception e) {
            return Result.failed(e.getMessage());
        }
    }

    @RequestMapping("/tags/add")
    public Result delete(String name){
        try {
            tagService.insert(name);
            return Result.success("成功");
        } catch (Exception e) {
            return Result.failed(e.getMessage());
        }
    }

    @RequestMapping("/tags/update")
    public Result delete(Tag tag){
        try {
            tagService.update(tag);
            return Result.success("成功");
        } catch (Exception e) {
            return Result.failed(e.getMessage());
        }
    }
}
