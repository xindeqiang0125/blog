package com.xdq.blog.common.annotation;

import org.mybatis.spring.annotation.MapperScan;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@MapperScan(basePackages = {"com.xdq.blog.common.dao"})
public @interface EnableDao {
}
