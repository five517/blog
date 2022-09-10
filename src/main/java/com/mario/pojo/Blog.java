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
@NoArgsConstructor
@AllArgsConstructor
//表示是数据库实体
//@Proxy(lazy = false)
@Entity(name="t_blog")
public class Blog {
    //文章id,下面两个注解表示为主键与自动生成键值
    @Id
    @GeneratedValue
    private Long id;
    //文章标题
    private String title;
    //文章内容

    //将String表示为大字段，在mysql中不再是varchar255而是longtext
    @Basic(fetch = FetchType.LAZY)
    @Lob
    private String content;
    //文章显示首图
    private String firstPicture;
    //文章是否原创还是转载之类的
    private String flag;
    //文章浏览量
    private Integer views;
    //是否开启二维码赞赏
    private boolean appreciation;
    //是否开启转载声明
    private boolean shareStatement;
    //是否开启文章评论功能
    private boolean commentabled;
    //是否已经发布
    private boolean published;
    //是否放在推荐页
    private boolean recommend;

    //创建时间
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    //更新时间
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    //每篇博客只有一个类型，一位置，多篇博客可以对应同一个类型，所以是多对一的关系
    @ManyToOne
    private Type type;

    //不同的标签可以对应同一个篇博客，同一个博客可以有多个标签，所以是多对多的关系
    // cascade = {CascadeType.PERSIST}级联新增
    @ManyToMany(cascade = {CascadeType.PERSIST})
    private List<Tag> tags=new ArrayList<>();

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "blog")
    private List<Comment> comments=new ArrayList<>();

    //表示不需要被保存进数据库
    @Transient
    private String tagIds;

    //初始化
    public void init() {
        this.tagIds = tagsToIds(this.getTags());
    }

    //1,2,3
    private String tagsToIds(List<Tag> tags) {
        if (!tags.isEmpty()) {
            StringBuffer ids = new StringBuffer();
            boolean flag = false;
            for (Tag tag : tags) {
                if (flag) {
                    ids.append(",");
                } else {
                    flag = true;
                }
                ids.append(tag.getId());
            }
            return ids.toString();
        } else {
            return tagIds;
        }
    }

    private String description;

}
