package com.xdq.blog.common.dao;

import com.xdq.blog.common.domain.Tag;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface TagDao {
    @Select("select * from tag")
    List<Tag> findAll();

    @Select("select t.* from article a join article_tag at on a.id=at.article_id " +
            "join tag t on t.id=at.tag_id where a.id=#{articleId}")
    List<Tag> findByArticle(@Param("articleId") int articleId);

    @Insert("insert into tag(name) values (#{name})")
    int insert(@Param("name") String name);

    @Delete("delete from tag where id=#{id}")
    int delete(@Param("id") int id);

    @Update("update tag set name=#{name} where id=#{id}")
    int update(Tag tag);

    @Delete("delete from article_tag where tag_id=#{tagId}")
    int deleteRelation(@Param("tagId") int tagId);
}
