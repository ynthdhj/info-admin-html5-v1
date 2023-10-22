package com.info.admin.html5.security;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.info.admin.html5.entity.Admin;
import com.info.admin.html5.repository.AdminRepository;

/**
 *  @作者 段红杰
 *  QQ: 740836
 *  说明：可以任意修改、可以商用，但是需要保留此信息
 */
@Service
public class AdminDetailsService implements UserDetailsService {

	private AdminRepository adminRepository;

	public AdminDetailsService(AdminRepository adminRepository) {
		this.adminRepository = adminRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Admin admin = adminRepository.findByUsername(username);

		if (admin != null) {
			return new org.springframework.security.core.userdetails.User(admin.getUsername(), admin.getPassword(),
					// mapRolesToAuthorities(admin.getRoles())
					mapRolesToAuthorities()

			);
		} else {
			throw new UsernameNotFoundException("无效的用户名和密码.");
		}
	}

	/*
	 * private Collection < ? extends GrantedAuthority>
	 * mapRolesToAuthorities(Collection <Role> roles) { Collection < ? extends
	 * GrantedAuthority> mapRoles = roles.stream() .map(role -> new
	 * SimpleGrantedAuthority(role.getName())) .collect(Collectors.toList()); return
	 * mapRoles; }
	 * 
	 */

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities() {
		Collection<GrantedAuthority> grantedAuthorities = new HashSet<>();
		// grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()))
		grantedAuthorities.add(new SimpleGrantedAuthority("Admin"));
		return grantedAuthorities;
	}
}
