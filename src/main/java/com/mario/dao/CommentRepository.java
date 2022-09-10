package com.mario.dao;

import com.mario.pojo.Comment;
import com.mario.pojo.Tag;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {

    //根据博客id查询到当前的评论，以及是否有父评论
    List<Comment> findByBlogIdAndParentCommentNull(Long blogId, Sort sort);
}
