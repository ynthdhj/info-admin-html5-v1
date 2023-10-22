package com.info.admin.html5.service;

import java.util.List;

import com.info.admin.html5.dto.AdminDto;
import com.info.admin.html5.entity.Admin;

import jakarta.validation.Valid;

/**
 *  @作者 段红杰
 *  QQ: 740836
 *  说明：可以任意修改、可以商用，但是需要保留此信息
 */
public interface AdminService {
	
    void save(AdminDto adminDto);

    Admin findByUsername(String username);

    List<Admin> findAll();

	void delete(Long id);
}
