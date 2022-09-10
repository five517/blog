package com.mario.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@Proxy(lazy = false)
@Entity(name="t_comment")
public class Comment {
    @Id
    @GeneratedValue
    private Long id;
    private String nickname;
    private String email;
    private String content;
    private String avatar;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    @ManyToOne
    private Blog blog;

    //判断是否是博主的评论
    private boolean adminComment;

    //评论的父子对象关系
    //一个父对象可以对应多个子对象，即一个父评论下可以有多个子评论
    @OneToMany(mappedBy = "parentComment")
    private List<Comment> replyComments = new ArrayList<>();

    //而每个子评论只有一个唯一的父对象，即多个子评论可以有同一个父对象
    @ManyToOne
    private Comment parentComment;

}
