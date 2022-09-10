package com.mario.service;

import com.mario.NotFoundException;
import com.mario.dao.TypeRepository;
import com.mario.pojo.Type;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService{
    //引入Dao
    @Autowired
    private TypeRepository typeRepository;

    //放进事务里面
    @Transactional
    @Override
    public Type saveType(Type type) {
        return typeRepository.save(type);
    }

    @Transactional
    @Override
    public Type getType(Long id) {
        return typeRepository.getOne(id);
    }

    @Transactional
    @Override
    public Type getTypeByName(String name) {
        return typeRepository.findByName(name);
    }

    @Transactional
    @Override
    public Page<Type> listType(Pageable pageable) {
        return typeRepository.findAll(pageable);
    }

    @Transactional
    @Override
    public List<Type> listType() {
        return typeRepository.findAll();
    }

    @Transactional
    @Override
    public List<Type> listTypeTop(Integer size) {
        //按照每个type下blog数量降序排列
        Sort sort=  Sort.by(Sort.Direction.DESC, "blogs.size");
        //排序的条数就是传递进来的size，意思是总共排序size个，按照sort的方法排序
        Pageable pageable =  PageRequest.of(0,size,sort);
        return typeRepository.findTop(pageable);
    }

    @Transactional
    @Override
    public Type updateType(Long id, Type type) {
        //先查询到要更新的这个type
        Type t = typeRepository.getOne(id);
        if (t==null){
            throw new NotFoundException("都没有这个类型，更新个锤子");
        }
        //存在的话就更新
        BeanUtils.copyProperties(type,t);
        return typeRepository.save(t);
    }

    @Transactional
    @Override
    public void deleteType(Long id) {
        typeRepository.deleteById(id);
    }
}
