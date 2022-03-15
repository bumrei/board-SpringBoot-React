package org.denny.boardprac.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.denny.boardprac.dto.PageRequestDTO;
import org.denny.boardprac.dto.PageResponseDTO;
import org.denny.boardprac.dto.ReplyDTO;
import org.denny.boardprac.entity.Reply;
import org.denny.boardprac.repository.ReplyRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService {

    private final ModelMapper modelMapper;

    private final ReplyRepository replyRepository;

    @Override
    public PageResponseDTO<ReplyDTO> getListOfBoard(Long bno, PageRequestDTO pageRequestDTO) {

        Pageable pageable = null;

        if (pageRequestDTO.getPage() == -1) {
            int lastPage = calcLastPage(bno); // -1 or 댓글 없는 경우 or 마지막 댓글 페이지
            if (lastPage == -1) {
                lastPage = 1;
            }

            pageRequestDTO.setPage(lastPage);
        }

        pageable = PageRequest.of(pageRequestDTO.getPage() - 1, pageRequestDTO.getSize());

        Page<Reply> result =  replyRepository.getListByBno(bno, pageable);

        List<ReplyDTO> dtoList = result.get().map(reply -> modelMapper.map(reply, ReplyDTO.class)).collect(Collectors.toList());

        return new PageResponseDTO<>(pageRequestDTO, (int)result.getTotalElements(), dtoList);
    }

    private int calcLastPage(Long bno) {

        int count = replyRepository.getReplyCountOfBoard(bno);

        int lastPage = (int)(Math.ceil(count/10.0));

        return lastPage;

    }
}
