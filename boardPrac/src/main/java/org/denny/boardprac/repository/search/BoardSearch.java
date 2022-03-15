package org.denny.boardprac.repository.search;

import org.denny.boardprac.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardSearch {

    Page<Board> search1(char[] typeArr, String keyword, Pageable pageable);

    Page<Object[]> searchWithReplyCount(char[] typeArr, String keyword, Pageable pageable);

}
