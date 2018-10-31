package com.xdq.blog.website.controller;

import com.xdq.blog.common.domain.Tag;
import com.xdq.blog.website.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TagController {

    @Autowired
    TagService tagService;

    @GetMapping("/tags")
    List<Tag> all(){
        return tagService.findAll();
    }
}
