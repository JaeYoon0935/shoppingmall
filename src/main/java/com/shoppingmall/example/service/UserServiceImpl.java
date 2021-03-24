package com.shoppingmall.example.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.shoppingmall.example.domain.User;
import com.shoppingmall.example.domain.UserInfo;
import com.shoppingmall.example.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserMapper userMapper;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userMapper.readUser(username);
		user.setAuthorities(getAuthorities(username));	
		return user;
	}


	@Override
	public User readUser(String username) {
		return userMapper.readUser(username);
	}


	@Override
	public Collection<GrantedAuthority> getAuthorities(String username) {
		List<GrantedAuthority> authorities = userMapper.readAuthorities(username);
		return authorities;
	}

	@Override
	public int createUser(User user) {
	     return userMapper.createUser(user);
	   }

	@Override
	public void createAuthority(User user) {
		userMapper.createAuthority(user);
	}
	
	@Override
	public List<UserInfo>read_user_list() {
		return userMapper.read_user_list();
	}
	
	//유저읽기 새로고침
	@Override
	public UserInfo readUser_refresh(String username) {
		return userMapper.readUser_refresh(username);
	}
	//유저권한읽기 새로고침
	@Override
	public List<GrantedAuthority> readAuthorities_refresh(String username) {
		return userMapper.readAuthorities_refresh(username);
	}
	//회원수정
	@Override
	public void updateUser(UserInfo userinfo) {
		userMapper.updateUser(userinfo);
	}
	
	//테스트 메서드
	@Override
	public List<UserInfo> shopping_readUser() {
		return userMapper.shopping_readUser();
	};
	
	//회원탈퇴
	@Override
	public void delete_user(String username){
		userMapper.delete_user(username);
	}
	
	//중복검사하기
	@Override
	public User duplicate(User user){
		return userMapper.duplicate(user);
	}
	
	//로그인 시 사용자 정보 조회
	@Override
	public UserInfo getUser(UserInfo user) {
		return userMapper.getUser(user);
	}
	
}
