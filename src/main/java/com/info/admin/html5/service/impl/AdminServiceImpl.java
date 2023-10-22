package com.info.admin.html5.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.info.admin.html5.dto.AdminDto;
import com.info.admin.html5.entity.Admin;
import com.info.admin.html5.repository.AdminRepository;
import com.info.admin.html5.service.AdminService;

/**
 *  @作者 段红杰
 *  QQ: 740836
 *  说明：可以任意修改、可以商用，但是需要保留此信息
 */
@Service

public class AdminServiceImpl implements AdminService {

    private AdminRepository adminRepository;
    
    private PasswordEncoder passwordEncoder;

    public AdminServiceImpl(AdminRepository adminRepository,                      
                           PasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository; 
        this.passwordEncoder = passwordEncoder;
    }
    
 
    public void save(AdminDto adminDto) {
    	Admin admin=new Admin();
    	admin.setUsername(adminDto.getUsername());
    	admin.setPassword(passwordEncoder.encode(adminDto.getPassword()));
    	admin.setCreatedate(new Date());
        adminRepository.save(admin);
    }


    public Admin findByUsername(String username) {
        return adminRepository.findByUsername(username);
    }


    public List<Admin> findAll() {
        List<Admin> admins = adminRepository.findAll();
        return admins;
    }
   
    public void delete(Long id) {
    	 adminRepository.deleteById(id);
    }
   
}
