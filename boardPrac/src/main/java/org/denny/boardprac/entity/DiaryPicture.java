package org.denny.boardprac.entity;

import lombok.*;

import javax.persistence.Embeddable;

@Embeddable // 얘를 걸어주면 이 객체는 ElementCollection 으로 처리가 가능하다. 즉 값객체로 만들어 준다.
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(of = "uuid") // 중복된 데이터를 없게 만들어준다.
public class DiaryPicture implements Comparable<DiaryPicture> {

    private String uuid;
    private String fileName;
    private String savePath;
    private int idx;


    // 정렬할때 순서를 앞으로 할꺼냐 뒤로 할꺼냐를 정해줌
    @Override
    public int compareTo(DiaryPicture o) {
        return this.idx - o.idx;
    }
}
