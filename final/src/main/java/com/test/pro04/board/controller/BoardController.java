package com.test.pro04.board.controller;

import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.test.pro04.board.dto.BoardDTO;

import jakarta.servlet.http.HttpSession;


public interface BoardController {
	public String boardList(Model model);
	public String insertBoard();
	public String insertBoard(BoardDTO dto,@RequestParam("files") List<MultipartFile> files,HttpSession session) throws Exception;
	public String getBoard(@RequestParam("articleNo") int articleNo, Model model);
	public String getBoard(BoardDTO dto, Model model);
	public String deleteBoard(@RequestParam("articleNo") int articleNo, Model model);
	public String myList(HttpSession session,Model model);
}