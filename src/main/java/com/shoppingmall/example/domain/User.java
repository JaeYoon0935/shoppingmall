package com.shoppingmall.example.domain;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class User implements UserDetails{
	
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	private String name;
	private String phone;
	private String address;
	private String email;
	private String point; //일단은 테스트를 위해 int 대신 string으로 사용
	private String uDateTime;
	private Collection<? extends GrantedAuthority> authorities;
	private Boolean isAccountNonExpired;
	private Boolean isAccountNonLocked;
	private Boolean isCredentialsNonExpired;
	private Boolean isEnabled;

	private String date; // 회원가입시 회원가입 축하 포인트를 줄때 사용할 날짜.

	private List<User> user_map;
	
	
	
	
	public Boolean getIsAccountNonExpired() {
		return isAccountNonExpired;
	}

	public void setIsAccountNonExpired(Boolean isAccountNonExpired) {
		this.isAccountNonExpired = isAccountNonExpired;
	}

	public Boolean getIsAccountNonLocked() {
		return isAccountNonLocked;
	}

	public void setIsAccountNonLocked(Boolean isAccountNonLocked) {
		this.isAccountNonLocked = isAccountNonLocked;
	}

	public Boolean getIsCredentialsNonExpired() {
		return isCredentialsNonExpired;
	}

	public void setIsCredentialsNonExpired(Boolean isCredentialsNonExpired) {
		this.isCredentialsNonExpired = isCredentialsNonExpired;
	}

	public Boolean getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<User> getUser_map() {
		return user_map;
	}

	public void setUser_map(List<User> user_map) {
		this.user_map = user_map;
	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPoint() {
		return point;
	}

	public void setPoint(String point) {
		this.point = point;
	}


//	@Override
//	public String toString() {
//		return "User [username=" + username + ", password=" + password + ", name=" + name + ", phone=" + phone
//				+ ", uDateTime=" + uDateTime + ", authorities=" + authorities + ", isAccountNonExpired="
//				+ isAccountNonExpired + ", isAccountNonLocked=" + isAccountNonLocked + ", isCredentialsNonExpired="
//				+ isCredentialsNonExpired + ", isEnabled=" + isEnabled + "]";
//	}

	public String getuDateTime() {
		return uDateTime;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setuDateTime(String uDateTime) {
		this.uDateTime = uDateTime;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	
	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	} 

	@Override
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	
	@Override
	public String getUsername() {
		return username;
	}
	
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setAccountNonExpired(boolean isAccountNonExpired) {
		this.isAccountNonExpired = isAccountNonExpired;
	}

	public void setAccountNonLocked(boolean isAccountNonLocked) {
		this.isAccountNonLocked = isAccountNonLocked;
	}

	public void setCredentialsNonExpired(boolean isCredentialsNonExpired) {
		this.isCredentialsNonExpired = isCredentialsNonExpired;
	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return isAccountNonExpired;
	}
	
	@Override
	public boolean isAccountNonLocked() {
		return isAccountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return isCredentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return isEnabled;
	}

}
