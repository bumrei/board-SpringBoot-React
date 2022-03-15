package org.denny.boardprac.dto;

import lombok.*;
import org.denny.boardprac.entity.Board;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReplyDTO {

    private Long rno;

    private String replyText;

    private String replyer;

    private Long bno;

    private LocalDateTime replyDate;

}
