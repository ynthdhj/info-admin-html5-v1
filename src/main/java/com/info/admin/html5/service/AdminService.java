package com.info.admin.html5.service;

import java.util.List;

import com.info.admin.html5.dto.AdminDto;
import com.info.admin.html5.entity.Admin;

import jakarta.validation.Valid;

/*
 *  @author 段洪杰
 */
public interface AdminService {
	
    void save(AdminDto adminDto);

    Admin findByUsername(String username);

    List<Admin> findAll();

	void delete(Long id);
}
