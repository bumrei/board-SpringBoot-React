package org.denny.boardprac.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "board") // Reply select 해올때 board 가 Lazy 상태라 select 해오지 않기 때문에 ToString에서 문제가 발생한다.
// 왜냐 board 에 대해서는 log.info 에서 ToString 이 돌지 않아 정보가 없기 때문에. 그래서 Reply 할때는 board 를 ToString에서 제외 시키는 것이다.
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno;

    private String replyText;

    private String replyer;

    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;

    @CreationTimestamp
    private LocalDateTime replyDate;


}
