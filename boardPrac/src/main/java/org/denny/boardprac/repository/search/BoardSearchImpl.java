package org.denny.boardprac.repository.search;

import lombok.extern.log4j.Log4j2;
import org.denny.boardprac.entity.Board;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

@Log4j2
public class BoardSearchImpl extends QuerydslRepositorySupport implements BoardSearch {

    public BoardSearchImpl() {
        super(Board.class);
    }

    @Override
    public void search1() {
        log.info("-----------search1");
    }
}
