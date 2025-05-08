package com.test.pro04.common.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.pro04.common.dto.FileDTO;

public interface FileRepository extends JpaRepository<FileDTO, Integer>{
	List<FileDTO> findByArticleNo(int articleNo);
}
