package com.test.pro04.board.controller;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.test.pro04.board.dto.BoardDTO;
import com.test.pro04.board.service.BoardService;
import com.test.pro04.common.controller.FileController;
import com.test.pro04.common.dto.FileDTO;
import com.test.pro04.common.repository.FileRepository;

import jakarta.servlet.http.HttpSession;



@Controller
@RequestMapping("/board")
public class BoradControllerImpl implements BoardController{
	@Autowired
	BoardService service;
	@Autowired
	FileRepository repository;
	
	@GetMapping("/boardList")
	public String boardList(Model model) {
		List<BoardDTO> boardList = service.boardList();
		model.addAttribute("boardList", boardList);
		return "board/boardList";
	}
	
	@GetMapping("/insertBoard")
	public String insertBoard() {
		return "board/insertBoard";
	}


	@Override
	@GetMapping("/getBoard")
	public String getBoard(int articleNo, Model model) {
		BoardDTO board = service.getBoard(articleNo);
		List<FileDTO> fileList = repository.findByArticleNo(articleNo);
		model.addAttribute("board", board);
		model.addAttribute("fileList",fileList);
		return "board/getBoard";
	}

	@Override
	@PostMapping("/getBoard")
	public String getBoard(BoardDTO dto, Model model) {
		// TODO Auto-generated method stub
		service.updateBoard(dto);
		model.addAttribute("message", "게시글이 수정되었습니다.");
		model.addAttribute("redirectUrl", "/board/getBoard?articleNo="+dto.getArticleNo());
		return "common/alert";
	}

	@Override
	@GetMapping("/deleteBoard")
	public String deleteBoard(int articleNo, Model model) {
		// TODO Auto-generated method stub
		service.deleteBoard(articleNo);
		model.addAttribute("message", "게시글이 삭제되었습니다.");
		model.addAttribute("redirectUrl", "/board/boardList");
		return "common/alert";
	}

	@Override
	@PostMapping("/insertBoard")
	public String insertBoard(BoardDTO dto, @RequestParam("files") List<MultipartFile> files,HttpSession session) throws Exception{
		
		service.insertBoard(dto);	
			for(MultipartFile file : files) {
				if(!files.isEmpty()) {
					String originalFileName = file.getOriginalFilename();
					String saveDIR = FileController.FILE_REFO + "\\" + dto.getArticleNo();
					
					File dir = new File(saveDIR);
					
					if(!dir.exists()) {
						dir.mkdir();
					}
					Path savePath = Paths.get(saveDIR, originalFileName);
					file.transferTo(savePath.toFile());
					
					FileDTO fileDTO = new FileDTO();
					fileDTO.setArticleNo(dto.getArticleNo());
					fileDTO.setFileName(originalFileName);
					repository.save(fileDTO);
					
				}
			}
		return null;
	}

	@Override
	@GetMapping("myList")
	public String myList(HttpSession session,Model model) {
		String myId = (String)session.getAttribute("userId");
		List<BoardDTO> myList = service.myList(myId);
		model.addAttribute("myList",myList);
		return null;
	}
	
}
