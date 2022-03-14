package org.denny.boardprac.repository;

import lombok.extern.log4j.Log4j2;
import org.denny.boardprac.entity.Board;
import org.denny.boardprac.entity.Reply;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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


}
