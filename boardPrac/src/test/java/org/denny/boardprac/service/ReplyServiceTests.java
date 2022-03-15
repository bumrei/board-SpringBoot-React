package org.denny.boardprac.service;

import lombok.extern.log4j.Log4j2;
import org.denny.boardprac.dto.PageRequestDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
public class ReplyServiceTests {

    @Autowired
    private ReplyService replyService;


    @Test
    public void testList() {
        Long bno = 199L;

        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(-1)
                .build();

        log.info(replyService.getListOfBoard(bno, pageRequestDTO));
    }

}
