# Spring Boot个人博客项目

这是一个基于springboot的简易个人博客网站，主要是实现个人博客的增删改查相关的功能。

![Java](https://img.shields.io/badge/language-Java-brightgreen.svg?style=plastic)![Java](https://img.shields.io/badge/frame-SpringBoot-brightgreen.svg?style=plastic)![](https://img.shields.io/badge/frame-Semantic UI-brightgreen.svg?style=plastic)![](https://img.shields.io/badge/database-MySQL-brightgreen.svg?style=plastic)

## 主要功能：

- 文章，页面，分类目录，标签的添加，删除，编辑等。文章、评论及页面支持`Markdown`，支持代码高亮。
- 支持文章全文搜索。
- 完整的评论功能，包括发表回复评论。
- 侧边栏功能，最新文章，分类，标签等。
- 集成了简单的图床功能。
- 归档按到博客发布时间顺序进行时间轴排序。
- 集成后台管理系统。

## 运行

目录中有blog.sql文件，可以直接执行生成对应的数据库和表，记得修改application.yml中关于数据库的相关配置

后台管理界面为：locahost:8080/admin 账号密码都为admin

## 展示界面

![image-20220910175058932](https://mario517-1303843217.cos.ap-nanjing.myqcloud.com/img/image-20220910175058932.png)

![image-20220910175127592](https://mario517-1303843217.cos.ap-nanjing.myqcloud.com/img/image-20220910175127592.png)