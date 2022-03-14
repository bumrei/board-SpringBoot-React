package org.denny.boardprac.repository;

import org.denny.boardprac.entity.Board;
import org.denny.boardprac.repository.search.BoardSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardSearch {

    @Query("select b.bno, b.title, b.writer, b.regDate, count(r) from Board b left join Reply r on r.board = b group by b")
    Page<Object[]> ex1(Pageable pageable);
    // select 에 파라미터가 여러개이다! 그럼 무조건 Object[] 배열로 뽑을것.

}
