package org.denny.boardprac.repository;

import lombok.extern.log4j.Log4j2;
import org.denny.boardprac.entity.Board;
import org.denny.boardprac.entity.Reply;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class ReplyRepositoryTests {

    @Autowired
    private ReplyRepository replyRepository;

    @Test
    public void insert200() {
        IntStream.rangeClosed(1, 200).forEach(i -> {
            Long bno = (long)(200 - (i % 5));

            int replyCount = (i % 5);

            IntStream.rangeClosed(0, replyCount).forEach(j -> {

                Board board = Board.builder()
                        .bno(bno)
                        .build();

                Reply reply = Reply.builder()
                        .replyText("Reply...")
                        .replyer("replyer...")
                        .board(board)
                        .build();

                replyRepository.save(reply);
            });
        });
    }

    @Transactional
    @Test
    public void testReacReply() {
        Long rn = 1L;

        Reply reply = replyRepository.findById(rn).get();

        log.info(reply);

        log.info(reply.getBoard());
    }

    @Test
    public void testByBno() {
        Long bno = 200L;

        List<Reply> replyList = replyRepository.findReplyByBoard_BnoOrderByRno(bno);

        replyList.forEach(reply -> log.info(reply));
    }

    @Test
    public void testListOfBoard() {

        Pageable pageable = PageRequest.of(0, 10, Sort.by("rno").descending());

        Page<Reply> result = replyRepository.getListByBno(197L, pageable);

        log.info(result.getTotalElements());
        result.get().forEach(ia -> log.info(ia));

    }

    @Test
    public void testCountOfBoard() {

        Long bno = 195L;

        int count = replyRepository.getReplyCountOfBoard(bno);

        int lastPage = (int)(Math.ceil(count/10.0));

//        if (count == 0) {
//            lastPage = 1;
//        }

        Pageable pageable = PageRequest.of(lastPage <= 0 ? 0 : lastPage -1, 10);

        Page<Reply> result = replyRepository.getListByBno(bno, pageable);

        log.info("last Page :" + result.getTotalPages());
        log.info("Total :" + result.getTotalElements());

        result.get().forEach(reply -> {
            log.info(reply);
        });

    }


}
