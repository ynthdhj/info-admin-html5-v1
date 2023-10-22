package com.info.admin.html5.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.info.admin.html5.dto.ManageDto;
import com.info.admin.html5.entity.Manage;

/*
 *  @作者 段红杰
 *  QQ: 740836
 *  说明：可以任意修改、可以商用，但是需要保留此信息
 */
public interface ManageService {
	
	void save(ManageDto manageDto);
	
	Manage findByPhone(String phone);

	Page<Manage> list(PageRequest pageRequest);

	Manage view(Long id);

	Manage edit(Long id);
	
	Manage editPassword(Long id);

	void update(Manage manage);
	
	void updatePassword(Manage manage);

	void delete(Long id);
	
}
