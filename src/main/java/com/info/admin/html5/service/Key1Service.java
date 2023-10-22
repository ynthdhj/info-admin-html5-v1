package com.info.admin.html5.service;

import java.util.List;

import com.info.admin.html5.dto.Key1Dto;
import com.info.admin.html5.entity.Admin;
import com.info.admin.html5.entity.Key1;

/*
 *  @author 段洪杰
 */
public interface Key1Service {
    void save(Key1Dto key1Dto);
    Key1 findByName(String name);
    List<Key1> findAll();
	void delete(Long id);
}
