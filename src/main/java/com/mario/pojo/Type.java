package com.mario.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Proxy;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@Proxy(lazy = false)
@Entity(name="t_type")
public class Type {
    @Id
    @GeneratedValue
    private Long id;
    @NotBlank(message = "分类名称不能让那为空")
    private String name;

    //一个类型下面可以有多个博客，所以是一对多的关系
    //Many是关系维护端，是主动维护的一端，One是关系被维护端，被维护端需要加上mappedBy = "关系维护端的Many下的变量"
    //来建立一个关系，以此来说明当前这个类是被维护的
    @OneToMany(mappedBy = "type")
    private List<Blog> blogs=new ArrayList<>();
}
