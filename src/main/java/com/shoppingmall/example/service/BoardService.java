package com.shoppingmall.example.service;

import java.util.List;

import com.shoppingmall.example.domain.Board;

public interface BoardService {

	public List<Board> selectBoardList();
	
	public Board selectBoardDetail(int bId);
	
	public void deleteBoard(int bId);
	
	
}
