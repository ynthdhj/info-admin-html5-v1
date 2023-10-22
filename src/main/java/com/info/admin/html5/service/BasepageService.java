package com.info.admin.html5.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.multipart.MultipartFile;

import com.info.admin.html5.dto.BasepageDto;
import com.info.admin.html5.entity.Basepage;

/*
 *  @author 段洪杰
 */
public interface BasepageService {
	void save(Basepage basepage);

	Page<Basepage> list(PageRequest pageRequest);

	Basepage view(Long id);

	Basepage edit(Long id);

	void update(Basepage basepage);

	void delete(Long id);
	
	String uploadFile(MultipartFile file, String modelName);
}
