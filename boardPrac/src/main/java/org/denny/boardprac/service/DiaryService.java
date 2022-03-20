package org.denny.boardprac.service;

import org.denny.boardprac.dto.DiaryDTO;
import org.denny.boardprac.dto.PageRequestDTO;
import org.denny.boardprac.dto.PageResponseDTO;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface DiaryService {

    Long register(DiaryDTO dto);

    DiaryDTO read(Long dno);

    PageResponseDTO<DiaryDTO> getList(PageRequestDTO pageRequestDTO);
}
