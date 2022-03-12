package org.denny.boardprac.repository;

import lombok.extern.log4j.Log4j2;
import org.denny.boardprac.dto.BoardDTO;
import org.denny.boardprac.entity.Board;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@SpringBootTest
@Log4j2
public class BoardRepositoryTests {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Test
    public void testSearch1() {

        char[] typeArr = null;
        String keyword = null;
        Pageable pageable = PageRequest.of(0,10, Sort.by("bno").descending());

        Page<Board> result = boardRepository.search1(typeArr, keyword, pageable);

        result.get().forEach(board -> {
            log.info("board : " + board);
            log.info("=========================");
            BoardDTO boardDTO = modelMapper.map(board, BoardDTO.class);
            log.info("boardDTO : " + boardDTO);
        });

    }
}
