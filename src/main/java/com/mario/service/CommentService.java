package com.mario.service;

import com.mario.pojo.Comment;

import java.util.List;

public interface CommentService {
    //通过博客id得到当博客的评论
    List<Comment> listCommentByBlogId(Long blogId);

    //保存当前的评论
    Comment saveComment(Comment comment);
}
