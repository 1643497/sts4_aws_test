package com.test.pro04.board.dao;

import java.util.List;

import com.test.pro04.board.dto.BoardDTO;

public interface BoardDAO {
	List<BoardDTO> boardList();
	void insertBoard(BoardDTO dto);
	BoardDTO getBoard(int articleNo);
	void update(BoardDTO dto);
	void delete(int articleNo);
	List<BoardDTO> myList(String myId);
}