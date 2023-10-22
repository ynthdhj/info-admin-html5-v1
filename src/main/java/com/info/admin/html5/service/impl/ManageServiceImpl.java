package com.info.admin.html5.service.impl;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.info.admin.html5.dto.ManageDto;
import com.info.admin.html5.entity.Manage;
import com.info.admin.html5.repository.ManageRepository;
import com.info.admin.html5.service.ManageService;

/*
 *  @作者 段红杰
 *  QQ: 740836
 *  说明：可以任意修改、可以商用，但是需要保留此信息
 */
@Service
public class ManageServiceImpl implements ManageService {

	private ManageRepository manageRepository;
	private PasswordEncoder passwordEncoder;

	public ManageServiceImpl(ManageRepository manageRepository, PasswordEncoder passwordEncoder) {
		this.manageRepository = manageRepository;
		this.passwordEncoder = passwordEncoder;
	}

	public Manage findByPhone(String phone) {
		return manageRepository.findByPhone(phone);
	}	

	public void save(ManageDto manageDto) {
		Manage manage = new Manage();
		manage.setPhone(manageDto.getPhone());
		manage.setPassword(passwordEncoder.encode(manageDto.getPassword()));
		manage.setName(manageDto.getName());
		manage.setMessage(manageDto.getMessage());
		manage.setCreatedate(new Date());
		manageRepository.save(manage);
	}

	public Page<Manage> list(PageRequest pageRequest) {
		Page<Manage> pages = manageRepository.findAll(pageRequest);
		return pages;
	}

	public Manage view(Long id) {
		Manage manage = manageRepository.findById(id).orElse(null);
		return manage;
	}

	public Manage edit(Long id) {
		Manage manage = manageRepository.findById(id).orElse(null);
		return manage;
	}
	
	public Manage editPassword(Long id) {
		Manage manage = manageRepository.findById(id).orElse(null);
		return manage;
	}

	public void update(Manage manage) {
		manageRepository.save(manage);
	}
	
	public void updatePassword(Manage manage) {
		manage.setPassword(passwordEncoder.encode(manage.getPassword()));
		manageRepository.save(manage);
	}
	

	public void delete(Long id) {
		manageRepository.deleteById(id);
	}

}
