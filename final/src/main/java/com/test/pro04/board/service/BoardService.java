package com.test.pro04.board.service;

import java.util.List;

import com.test.pro04.board.dto.BoardDTO;

public interface BoardService {
	List<BoardDTO> boardList();
	void insertBoard(BoardDTO dto);
	BoardDTO getBoard(int articleNo);
	void updateBoard(BoardDTO dto);
	void deleteBoard(int articleNo);
	List<BoardDTO> myList(String myId);
}
