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
	
	public int createUser(User user);
	
	public List<UserInfo>read_user_list();
	
	public List<GrantedAuthority> readAuthorities(String username);
	
	public void createAuthority(User user);
	
	//중복검사하기
	public User duplicate(User user);
	
	//테스트 메서드
	public List<UserInfo> shopping_readUser();
	
	//회원 탈퇴
	public void delete_user(String username);
	
	//회원수정
	public void updateUser(UserInfo userinfo);
	
	//로그인 시 사용자정보 조회
	public UserInfo getUser(UserInfo user);
	
}
