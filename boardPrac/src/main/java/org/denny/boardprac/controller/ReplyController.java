package org.denny.boardprac.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.denny.boardprac.dto.PageRequestDTO;
import org.denny.boardprac.dto.PageResponseDTO;
import org.denny.boardprac.dto.ReplyDTO;
import org.denny.boardprac.service.ReplyService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/replies")
public class ReplyController {

    private final ReplyService replyService;

    @GetMapping("/list/{bno}")
    public PageResponseDTO<ReplyDTO> getListOfBoard(@PathVariable("bno") Long bno, PageRequestDTO pageRequestDTO) {


        return replyService.getListOfBoard(bno, pageRequestDTO);
    }

    @PostMapping("")
    public PageResponseDTO<ReplyDTO> register(@RequestBody ReplyDTO replyDTO) {

        replyService.register(replyDTO); // 댓글 등록하고나서

        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().page(-1).build();

        return replyService.getListOfBoard(replyDTO.getBno(), pageRequestDTO); // 댓글 새로운 정보 불러오기

    }

    @DeleteMapping("/{bno}/{rno}")
    public PageResponseDTO<ReplyDTO> remove(@PathVariable("bno") Long bno , @PathVariable("rno") Long rno, PageRequestDTO pageRequestDTO) {

        return replyService.remove(bno, rno, pageRequestDTO);

    }

    @PutMapping("/{bno}/{rno}")
    public PageResponseDTO<ReplyDTO> modify(@PathVariable("bno") Long bno, @PathVariable("rno") Long rno, @RequestBody ReplyDTO replyDTO, PageRequestDTO pageRequestDTO) {

        log.info("bno : " + bno);
        log.info("rno : " + rno);
        log.info("replyDTO : " + replyDTO);

        return replyService.modify(replyDTO, pageRequestDTO);

    }

}
