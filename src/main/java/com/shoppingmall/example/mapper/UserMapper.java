package com.shoppingmall.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.security.core.GrantedAuthority;

import com.shoppingmall.example.domain.User;
import com.shoppingmall.example.domain.UserInfo;

@Mapper
public interface UserMapper {

	public User readUser(String username);
	
	public UserInfo readUser_refresh(String username);
	
	public List<GrantedAuthority> readAuthorities_refresh(String username);
	
	public void createUser(User user);
	
	public List<UserInfo>read_user_list();
	
	public List<GrantedAuthority> readAuthorities(String username);
	
	public void createAuthority(User user);
	
	
	//테스트 메서드
	public List<UserInfo> shopping_readUser();
	
	//회원 탈퇴
	public void delete_user(String username);
	
	//회원수정
	public void updateUser(UserInfo userinfo);
	
}
