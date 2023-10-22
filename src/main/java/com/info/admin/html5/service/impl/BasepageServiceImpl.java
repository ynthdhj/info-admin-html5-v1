package com.info.admin.html5.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.info.admin.html5.dto.BasepageDto;
import com.info.admin.html5.entity.Basepage;
import com.info.admin.html5.repository.BasepageRepository;
import com.info.admin.html5.service.BasepageService;
import com.info.util.upload.UploadHelper;

/**
 *  @作者 段红杰
 *  QQ: 740836
 *  说明：可以任意修改、可以商用，但是需要保留此信息
 */
@Service
public class BasepageServiceImpl implements BasepageService {

	private BasepageRepository basepageRepository;

	public BasepageServiceImpl(BasepageRepository basepageRepository) {
		this.basepageRepository = basepageRepository;
	}

	public void save(Basepage basepage) {
		basepageRepository.save(basepage);
	}

	public Page<Basepage> list(PageRequest pageRequest) {
		Page<Basepage> pages = basepageRepository.findAll(pageRequest);
		return pages;
	}

	public Basepage view(Long id) {
		Basepage basepage = basepageRepository.findById(id).orElse(null);
		return basepage;
	}

	public Basepage edit(Long id) {
		Basepage basepage = basepageRepository.findById(id).orElse(null);
		return basepage;
	}

	public void update(Basepage basepage) {
		basepageRepository.save(basepage);
	}

	public void delete(Long id) {
		basepageRepository.deleteById(id);
	}

	public String uploadFile(MultipartFile file, String modelName) {
		// 上传文件，上传的资源文件路径，路径在部署jar包同级目录
		String path=null;
		if (!file.isEmpty()) {
			path = new UploadHelper(file, modelName).execute();
		}
		return path; 
	}

}
