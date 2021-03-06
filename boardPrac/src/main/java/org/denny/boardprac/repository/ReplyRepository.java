package org.denny.boardprac.repository;

import org.denny.boardprac.entity.Reply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

    List<Reply> findReplyByBoard_BnoOrderByRno(Long bno);

    @Query("select r from Reply r where r.board.bno = :bno")
    Page<Reply> getListByBno(Long bno, Pageable pageable);

    @Query("select count(r) from Reply r where r.board.bno = :bno")
    int getReplyCountOfBoard(Long bno);
}
