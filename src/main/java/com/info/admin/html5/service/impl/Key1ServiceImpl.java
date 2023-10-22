package com.info.admin.html5.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.info.admin.html5.dto.Key1Dto;
import com.info.admin.html5.entity.Key1;
import com.info.admin.html5.repository.Key1Repository;
import com.info.admin.html5.service.Key1Service;

/*
 * @author 段洪杰
 */
@Service
public class Key1ServiceImpl implements Key1Service {

    private Key1Repository key1Repository;

    public Key1ServiceImpl(Key1Repository key1Repository) {
        this.key1Repository = key1Repository;     
    }


    public void save(Key1Dto key1Dto) { 
    	Key1 key1 = new Key1();
    	key1.setName(key1Dto.getName());
    	key1.setCreatedate(new Date());
    	key1Repository.save(key1);
    }
    
    public Key1 findByName(String name) {
        return key1Repository.findByName(name);
    }


    
    public List<Key1> findAll() {
        List<Key1> key1s = key1Repository.findAll();
        return key1s;
    }
   
    public void delete(Long id) {
    	 key1Repository.deleteById(id);
    }

   

   
}
