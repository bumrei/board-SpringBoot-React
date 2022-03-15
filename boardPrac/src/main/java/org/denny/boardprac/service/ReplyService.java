package org.denny.boardprac.service;

import org.denny.boardprac.dto.PageRequestDTO;
import org.denny.boardprac.dto.PageResponseDTO;
import org.denny.boardprac.dto.ReplyDTO;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface ReplyService {

    PageResponseDTO<ReplyDTO> getListOfBoard(Long bno, PageRequestDTO pageRequestDTO);

}
