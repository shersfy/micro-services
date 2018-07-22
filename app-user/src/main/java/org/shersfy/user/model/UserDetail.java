package org.shersfy.user.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

public class UserDetail extends User implements UserDetails {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public UserDetail() {
		super();
	}
	
	public UserDetail(User user) {
		super();
		this.setId(user.getId());
		this.setUsername(user.getUsername());
		this.setPassword(user.getPassword());
		this.setRoles(user.getRoles());
		this.setCreateTime(user.getCreateTime());
		this.setUpdateTime(user.getUpdateTime());
	}
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
		if(StringUtils.isEmpty(this.getRoles())) {
			return list;
		}

		String[] arr = this.getRoles().split(",");
		for(String role :arr) {
			list.add(new SimpleGrantedAuthority(role.trim()));
		}
		return list;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
