package com.shoppingmall.example.domain;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

public class UserInfo {

	private static final long serialVersionUID = 1L;
	
	//새롭게 변경되는 id이름
	private String new_username;
	//기존 id를 담아두기 위한 변수
	private String temp;
	
	private String username;
	private String name;
	private String address;
	private String phone;
	private String email;
	private String password;
	private String point;
	private Collection<? extends GrantedAuthority> authorities;
	private String auth;
	
	public String getTemp() {
		return temp;
	}
	public void setTemp(String temp) {
		this.temp = temp;
	}
	public String getNew_username() {
		return new_username;
	}
	public void setNew_username(String new_username) {
		this.new_username = new_username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPoint() {
		return point;
	}
	public void setPoint(String point) {
		this.point = point;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}
	public String getAuth() {
		return auth;
	}
	public void setAuth(String auth) {
		this.authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(auth);
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public String toString() {
		return "UserInfo [username=" + username + ", name=" + name + ", phone=" + phone + ", authorities=" + authorities
				+ ", auth=" + auth + "]";
	}
	
}
