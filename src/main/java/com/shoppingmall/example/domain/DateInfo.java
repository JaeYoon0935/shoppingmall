package com.shoppingmall.example.domain;

public class DateInfo {
	
	private DateInfo dateinfo;
	
	public String date1;
	public String date2;
	
	
	public String name; //날짜에 의한 검색을 하는 경우 제품의 이름도 편의상 dateinfo에서 받도록함.
	
	public String day;
	public String day1;
	public String day2;
	public String month1;
	public String month2;
	public String year1;
	public String year2;

	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public DateInfo getDateinfo() {
		return dateinfo;
	}
	public void setDateinfo(DateInfo dateinfo) {
		this.dateinfo = dateinfo;
	}
	
	public String getDate1() { 
		return date1;
	}
	public void setDate1(String date1) {
		this.date1 = date1;
	}
	public String getDate2() {
		return date2;
	}
	public void setDate2(String date2) {
		this.date2 = date2;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getDay1() {
		return day1;
	}
	public void setDay1(String day1) {
		this.day1 = day1;
	}
	public String getDay2() {
		return day2;
	}
	public void setDay2(String day2) {
		this.day2 = day2;
	}
	public String getMonth1() {
		return month1;
	}
	public void setMonth1(String month1) {
		this.month1 = month1;
	}
	public String getMonth2() {
		return month2;
	}
	public void setMonth2(String month2) {
		this.month2 = month2;
	}
	public String getYear1() {
		return year1;
	}
	public void setYear1(String year1) {
		this.year1 = year1;
	}
	public String getYear2() {
		return year2;
	}
	public void setYear2(String year2) {
		this.year2 = year2;
	}
	
	
	@Override
	public String toString() {
		return "DateInfo [date1=" + date1 + ", date2=" + date2 + ", day=" + day + ", day1=" + day1 + ", day2=" + day2
				+ ", month1=" + month1 + ", month2=" + month2 + ", year1=" + year1 + ", year2=" + year2 + "]";
	}
	

	
	
	
}
