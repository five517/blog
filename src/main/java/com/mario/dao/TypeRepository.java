package com.mario.dao;


import com.mario.pojo.Type;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TypeRepository extends JpaRepository<Type,Long> {
    Type findByName(String name);

    //查询top个分类，按照条目数从上到下排列
    @Query("select t from t_type t")
    List<Type> findTop(Pageable pageable);
}
