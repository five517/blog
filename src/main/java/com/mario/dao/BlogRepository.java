package com.mario.dao;

import com.mario.pojo.Blog;
import com.mario.pojo.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog,Long>, JpaSpecificationExecutor<Blog> {
    @Transactional
    @Modifying
    @Query("update t_blog b set b.views = b.views+1 where b.id = ?1")
    int updateViews(Long id);

    @Query("select b from t_blog b where b.recommend = true")
    List<Blog> findTop(Pageable pageable);

    @Query("select b from t_blog b where b.title like ?1 or b.content like ?1")
    Page<Blog> findByQuery(String query, Pageable pageable);

//    @Query("select function('date_format',b.updateTime,'%Y') as year from t_blog b group by function('date_format',b.updateTime,'%Y') order by function('date_format',b.updateTime,'%Y')  desc ")
//    List<String> findGroupYear();
//
//    @Query("select b from t_blog b where function('date_format',b.updateTime,'%Y') = ?1")
//    List<Blog> findByYear(String year);
    @Query("select function('date_format',b.createTime,'%Y') as year from t_blog b group by function('date_format',b.createTime,'%Y') order by function('date_format',b.createTime,'%Y')  desc ")
    List<String> findGroupYear();

    @Query("select b from t_blog b where function('date_format',b.createTime,'%Y') = ?1")
    List<Blog> findByYear(String year);

}
