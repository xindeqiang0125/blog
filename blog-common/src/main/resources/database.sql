create database blog;
use blog;

create table article(
  id int primary key auto_increment,
  title varchar(128),
  content varchar(5000),
  page_views int default 0,
  published bit default 0,
  update_time timestamp default current_timestamp on update current_timestamp
);
insert into article(title, content) values
('使用CSDN-markdown编辑器','这里写自定义目录标题欢迎使用Markdown编辑器新创建标题，有助于目录的生'),
('Linux下sqlplus方向键、退格键出现乱码解决方法','这里写自定义目录标题欢迎使用Markdown编辑器新创建标题，有助于目录的生'),
('网赚最火项目花生日记月收入过万很轻松','这里写自定义目录标题欢迎使用Markdown编辑器新创建标题，有助于目录的生'),
('node循环中的异步','这里写自定义目录标题欢迎使用Markdown编辑器新创建标题，有助于目录的生'),
('git 常用命令','这里写自定义目录标题欢迎使用Markdown编辑器新创建标题，有助于目录的生'),
('UDP---之QT的QUdpSocket使用','这里写自定义目录标题欢迎使用Markdown编辑器新创建标题，有助于目录的生');

create table tag(
  id int primary key auto_increment,
  name varchar(32)
);

insert into tag(name) values ('HTML5'),('CSS3'),('Java'),('Spring'),('Mybatis');

create table article_tag(
  id int primary key auto_increment,
  article_id int,
  tag_id int
);
insert into article_tag(article_id, tag_id) VALUES (1,1),(1,2),(2,4),(2,5),(4,1),(5,3);

select t.* from article a join article_tag at on a.id=at.article_id
join tag t on t.id=at.tag_id where a.id=2;

select a.* from article a join article_tag at on a.id=at.article_id
                          join tag t on t.id=at.tag_id where t.id=1;