package com.info.admin.html5.repository;

/*
 *  @author 段洪杰
 */
import org.springframework.data.jpa.repository.JpaRepository;

import com.info.admin.html5.entity.Manage;

public interface ManageRepository extends JpaRepository<Manage, Long> {
	Manage findByPhone(String phone);
}
