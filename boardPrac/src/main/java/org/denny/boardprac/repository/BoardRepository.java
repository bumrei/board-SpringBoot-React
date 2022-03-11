package org.denny.boardprac.repository;

import org.denny.boardprac.entity.Board;
import org.denny.boardprac.repository.search.BoardSearch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardSearch {
}
