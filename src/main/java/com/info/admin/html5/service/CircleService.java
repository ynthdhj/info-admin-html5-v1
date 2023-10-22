package com.info.admin.html5.service;

import java.util.List;

import com.info.admin.html5.dto.CircleDto;
import com.info.admin.html5.entity.Admin;
import com.info.admin.html5.entity.Circle;

/*
 *  @author 段洪杰
 */
public interface CircleService {
    void save(CircleDto circleDto);
    Circle findByName(String name);
    List<Circle> findAll();
	void delete(Long id);
}
