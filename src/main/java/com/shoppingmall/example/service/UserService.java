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
	
    public int createUser(User user);

    public List<UserInfo>read_user_list();

    Collection<GrantedAuthority> getAuthorities(String username);

	public void createAuthority(User user);

	
	//-----------------------아래 부터 쇼핑몰 프로젝트---------------------------------
	
	
	//중복검사하기
	public User duplicate(User user);
	
	//회원 정보 불러오기
	public List<UserInfo> shopping_readUser();
	
	//로그인 시 사용자 정보 조회
	public UserInfo getUser(UserInfo user);

	//회원탈퇴
	public void delete_user(String username);

	//회원수정
	public void updateUser(UserInfo userinfo);
}
