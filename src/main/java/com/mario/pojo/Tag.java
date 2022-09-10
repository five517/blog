package com.mario.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Proxy;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@Proxy(lazy = false)
@Entity(name="t_tag")
public class Tag {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    //多对多则任选一个作为被维护端加入mappedBy
    @ManyToMany(mappedBy = "tags")
    private List<Blog> blogs=new ArrayList<>();
}
