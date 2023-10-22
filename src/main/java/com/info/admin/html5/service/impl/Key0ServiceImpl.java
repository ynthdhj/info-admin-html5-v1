package com.info.admin.html5.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.info.admin.html5.dto.Key0Dto;
import com.info.admin.html5.entity.Key0;
import com.info.admin.html5.repository.Key0Repository;
import com.info.admin.html5.service.Key0Service;

/**
 *  @作者 段红杰
 *  QQ: 740836
 *  说明：可以任意修改、可以商用，但是需要保留此信息
 */
@Service
public class Key0ServiceImpl implements Key0Service {

    private Key0Repository key0Repository;

    public Key0ServiceImpl(Key0Repository key0Repository) {
        this.key0Repository = key0Repository;     
    }


    public void save(Key0Dto key0Dto) { 
    	Key0 key0 = new Key0();
    	key0.setName(key0Dto.getName());
    	key0.setCreatedate(new Date());
    	key0Repository.save(key0);
    }
    
    public Key0 findByName(String name) {
        return key0Repository.findByName(name);
    }


    
    public List<Key0> findAll() {
        List<Key0> key0s = key0Repository.findAll();
        return key0s;
    }
   
    public void delete(Long id) {
    	 key0Repository.deleteById(id);
    }

   

   
}
