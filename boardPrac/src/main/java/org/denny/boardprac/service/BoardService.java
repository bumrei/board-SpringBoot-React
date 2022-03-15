package org.denny.boardprac.service;

import org.denny.boardprac.dto.BoardDTO;
import org.denny.boardprac.dto.BoardListDTO;
import org.denny.boardprac.dto.PageRequestDTO;
import org.denny.boardprac.dto.PageResponseDTO;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface BoardService {

    Long register(BoardDTO boardDTO);

    PageResponseDTO<BoardDTO> getList(PageRequestDTO pageRequestDTO);

    PageResponseDTO<BoardListDTO> getListWithReplyCount(PageRequestDTO pageRequestDTO);

    BoardDTO read(Long bno);

    void modify(BoardDTO boardDTO);

    void remove(Long bno);
}
