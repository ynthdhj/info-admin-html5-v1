package com.info.admin.html5.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.info.admin.html5.dto.Key2Dto;
import com.info.admin.html5.entity.Key2;
import com.info.admin.html5.repository.Key2Repository;
import com.info.admin.html5.service.Key2Service;

/**
 *  @作者 段红杰
 *  QQ: 740836
 *  说明：可以任意修改、可以商用，但是需要保留此信息
 */
@Service
public class Key2ServiceImpl implements Key2Service {

    private Key2Repository key2Repository;

    public Key2ServiceImpl(Key2Repository key2Repository) {
        this.key2Repository = key2Repository;     
    }


    public void save(Key2Dto key2Dto) { 
    	Key2 key2 = new Key2();
    	key2.setName(key2Dto.getName());
    	key2.setCreatedate(new Date());
    	key2Repository.save(key2);
    }
    
    public Key2 findByName(String name) {
        return key2Repository.findByName(name);
    }


    
    public List<Key2> findAll() {
        List<Key2> key2s = key2Repository.findAll();
        return key2s;
    }
   
    public void delete(Long id) {
    	 key2Repository.deleteById(id);
    }

   

   
}
