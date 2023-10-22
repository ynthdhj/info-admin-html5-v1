package com.info.admin.html5.service;

import java.util.List;

import com.info.admin.html5.dto.Key2Dto;
import com.info.admin.html5.entity.Admin;
import com.info.admin.html5.entity.Key2;

/*
 *  @author 段洪杰
 */
public interface Key2Service {
    void save(Key2Dto key2Dto);
    Key2 findByName(String name);
    List<Key2> findAll();
	void delete(Long id);
}
