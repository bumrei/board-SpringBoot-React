package org.denny.boardprac.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.denny.boardprac.dto.PageRequestDTO;
import org.denny.boardprac.dto.PageResponseDTO;
import org.denny.boardprac.dto.ReplyDTO;
import org.denny.boardprac.entity.Board;
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
import java.util.stream.Stream;

@Service
@Log4j2
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService {

    private final ModelMapper modelMapper;

    private final ReplyRepository replyRepository;

    @Override
    public PageResponseDTO<ReplyDTO> getListOfBoard(Long bno, PageRequestDTO pageRequestDTO) {

        Pageable pageable = null;


        if (pageRequestDTO.getPage() <= -1) {
            int lastPage = calcLastPage(bno, pageRequestDTO.getSize()); // -1 or 댓글 없는 경우 or 마지막 댓글 페이지
            if (lastPage <= 0) {
                lastPage = 1;
            }

            pageRequestDTO.setPage(lastPage);
        }

        if (pageRequestDTO.getSize() <= 0) {
            pageRequestDTO.setSize(10);
        }

        log.info("===================================" + pageRequestDTO.getPage());
        log.info("===================================" + pageRequestDTO.getSize());


        pageable = PageRequest.of(pageRequestDTO.getPage() - 1, pageRequestDTO.getSize());

        Page<Reply> result =  replyRepository.getListByBno(bno, pageable);

        List<ReplyDTO> dtoList = result.get().map(reply -> modelMapper.map(reply, ReplyDTO.class)).collect(Collectors.toList());

        return new PageResponseDTO<>(pageRequestDTO, (int)result.getTotalElements(), dtoList);
    }

    @Override
    public Long register(ReplyDTO replyDTO) {

        // replyDTO 는 bno 이고 Reply 는 Board 라서 mapping 이 잘 안될까봐 걱정했는데
        // bno 를 modelMapper 가 자동으로 mapping 해서 Reply 에 Board.bno 만 들어간 상태로 mapping 을 깔끔하게 시켜준다.
        Reply reply = modelMapper.map(replyDTO, Reply.class);

        replyRepository.save(reply);

        return reply.getRno();

    }

    @Override
    public PageResponseDTO<ReplyDTO> remove(Long bno, Long rno, PageRequestDTO pageRequestDTO) {

        replyRepository.deleteById(rno);

        return getListOfBoard(bno, pageRequestDTO);
    }

    @Override
    public PageResponseDTO<ReplyDTO> modify(ReplyDTO replyDTO, PageRequestDTO pageRequestDTO) {

        // The value type coming up will be optional. So .orElseThrow() should be put like that
        Reply reply = replyRepository.findById(replyDTO.getRno()).orElseThrow();

        reply.setText(replyDTO.getReplyText());

        replyRepository.save(reply);

        return getListOfBoard(replyDTO.getBno(), pageRequestDTO);
    }

    private int calcLastPage(Long bno, double size) {

        if (size <= 0) {
            size = 10.0;
        }

        int count = replyRepository.getReplyCountOfBoard(bno);

        int lastPage = (int)(Math.ceil(count/size));

        return lastPage;

    }
}
