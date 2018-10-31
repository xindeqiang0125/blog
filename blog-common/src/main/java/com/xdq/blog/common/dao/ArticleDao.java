package com.xdq.blog.common.dao;

import com.xdq.blog.common.domain.Article;
import com.xdq.blog.common.domain.Tag;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ArticleDao {

    @Select("select * from article where id = #{id}")
    Article findById(@Param("id") Integer id);

    @Select("select id,title,page_views,published,update_time from article order by id desc")
    List<Article> findAll();

    @Select("select id,title,page_views,published,update_time from article where published=true order by id desc")
    List<Article> findAllPublished();

    @Select("select a.id,a.title,a.page_views,a.update_time from article a join article_tag at on a.id=at.article_id " +
            "join tag t on t.id=at.tag_id where a.published=true and t.id=#{tagId}")
    List<Article> findByTag(@Param("tagId") int tagId);

    @Select("select id,title,page_views,published,update_time from article where title like concat('%',#{title},'%')")
    List<Article> findByTitle(@Param("title") String title);

    @Select("select id,title,page_views,published,update_time from article where published=true and title like concat('%',#{title},'%')")
    List<Article> findByTitlePublished(@Param("title") String title);

    @Insert("insert into article(title) values(#{title})")
    int insert(@Param("title") String title);

    @Update("update article set " +
            "title=#{title}," +
            "published=#{published} " +
            "where id=#{id}")
    int updateMata(Article article);

    @Update("update article set content=#{content} where id=#{id}")
    int updateContent(@Param("content") String content,@Param("id") int id);

    @Delete("delete from article where id = #{id}")
    int delete(@Param("id") int id);

    @Select("select id,title,page_views,update_time from article where published=true order by page_views desc limit #{num}")
    List<Article> getHot(@Param("num") int num);

    @Select("select id,title from article where published=true order by update_time desc limit #{num}")
    List<Article> getLatest(@Param("num") int num);

    @Update("update article set page_views=page_views + 1,update_time=update_time where id=#{id}")
    int increase(int id);

    @Select("select t.id,t.name from tag t join article_tag at on t.id=at.tag_id " +
            "join article a on a.id=at.article_id where a.id=#{articleId}")
    List<Tag> findTags(Integer articleId);

    @Select("select id,title from article where published=true and id<#{id} order by id desc limit 1")
    Article preArticle(@Param("id") int id);

    @Select("select id,title from article where published=true and id>#{id} order by id asc limit 1")
    Article nextArticle(@Param("id") int id);

    @Delete("delete from article_tag where article_id=#{articleId}")
    void deleteTags(@Param("articleId") int articleId);

    @Insert("insert into article_tag(article_id, tag_id) VALUES (#{articleId},#{tagId})")
    void addTag(@Param("articleId") int articleId, @Param("tagId") int tagId);
}
