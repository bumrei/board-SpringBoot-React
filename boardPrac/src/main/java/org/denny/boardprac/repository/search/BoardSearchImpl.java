package org.denny.boardprac.repository.search;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import org.denny.boardprac.entity.Board;
import org.denny.boardprac.entity.QBoard;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import javax.persistence.Query;
import java.util.List;

@Log4j2
public class BoardSearchImpl extends QuerydslRepositorySupport implements BoardSearch {

    public BoardSearchImpl() {
        super(Board.class); // QBoard 만들기
    }

    @Override
    public Page<Board> search1(char[] typeArr, String keyword, Pageable pageable) {
        log.info("-----------search1");

        // 방법 1 -----------------------------------------------------------------------------------------------
//        Query query = this.getEntityManager().createQuery("select b from Board b order by b.bno desc");
//
//        log.info(query.getResultList());


        // 방법 2 -------------------------------------  정석  ---------------------------------------------------
        QBoard board = QBoard.board;

        JPQLQuery<Board> jpqlQuery = from(board); // from(board) 를 통해 select 생성?

//        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());
//        String keyword = "10";
//        char[] typeArr = {'T', 'C', 'W'};

        // 검색 조건이 있다면 BooleanBuilder 로 sql 동적 조건문을 만들어서 jpqlQuery where 문에 추가시켜 검색 결과가 나오게 해라
        if (typeArr != null && typeArr.length > 0) {

            BooleanBuilder condition = new BooleanBuilder();

            for (char type: typeArr) {
                if (type == 'T') {
                    condition.or(board.title.contains(keyword));
                } else if (type == 'C') {
                    condition.or(board.content.contains(keyword));
                } else if (type =='W') {
                    condition.or(board.writer.contains(keyword));
                }
            }
            jpqlQuery.where(condition);
        }

        jpqlQuery.where(board.bno.gt(0)); // bno > 0 보다 크다. - db full scan 피하는 방법중 하나라고 알고 있다.

        // 이 기능이 정말 혁신인게 applyPagination을 해주면 일일이 직접 paging 을 설정해줄 필요 없이 한번에 적용이 된다.
        JPQLQuery<Board> pagingQuery = this.getQuerydsl().applyPagination(pageable, jpqlQuery);

        List<Board> boardList = pagingQuery.fetch();

        long totalCount = pagingQuery.fetchCount(); // 게시물 total count

        return new PageImpl<>(boardList, pageable, totalCount);

        // 방법 3 -----------------------------------------------------------------------------------------------

//        log.info(this.getQuerydsl().createQuery(QBoard.board).fetch());
    }
}
