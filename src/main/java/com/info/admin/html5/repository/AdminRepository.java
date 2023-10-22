package com.info.admin.html5.repository;

/*
 *  @author 段洪杰
 */
import org.springframework.data.jpa.repository.JpaRepository;

import com.info.admin.html5.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {
	Admin findByUsername(String username);
}
