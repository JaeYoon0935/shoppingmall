package com.shoppingmall.example.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.shoppingmall.example.domain.User;
import com.shoppingmall.example.domain.UserInfo;

public interface UserService extends UserDetailsService{

	public User readUser(String username);

	public UserInfo readUser_refresh(String username);

	public List<GrantedAuthority>  readAuthorities_refresh(String username);
	
    public void createUser(User user);

    public List<UserInfo>read_user_list();

    Collection<GrantedAuthority> getAuthorities(String username);

	public void createAuthority(User user);

}
