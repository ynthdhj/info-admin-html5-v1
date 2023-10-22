package com.info.admin.html5.service;

import java.util.List;

import com.info.admin.html5.dto.CircleDto;
import com.info.admin.html5.entity.Admin;
import com.info.admin.html5.entity.Circle;

/**
 *  @作者 段红杰
 *  QQ: 740836
 *  说明：可以任意修改、可以商用，但是需要保留此信息
 */
public interface CircleService {
    void save(CircleDto circleDto);
    Circle findByName(String name);
    List<Circle> findAll();
	void delete(Long id);
}
