package org.denny.boardprac.entity;

import lombok.*;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tbl_diary")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = {"tags", "pictures"})
public class Diary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dno;

    private String title;

    private String content;

    private String writer;

    @CreationTimestamp
    private LocalDateTime regDate;

    @UpdateTimestamp
    private LocalDateTime modDate;

    // 태그 나 첨부파일같은경우는 자체적 CRUD 가 불가능하고, Entity 가 수정될때 insert 와 delete 만 되는 아주 종속적인 애이다.
    // 즉 update 가 없다. 댓글은 Board 에 종속되어있지만 CRUD 가 가능하여 insert, delete 뿐만 아니라 update 또한 가능하다. 그래서
    // 댓글은 entity 로 인정을 받는다.
    // 하지만 첨부파일과 태그는 그것이 아니기때문에 value 객체의 범주 안에 드는것이다.
    // value 객체들은 ManyToOne 이나 OneToMany 가 아닌 ElementCollection 으로 Entity 에서 관리가 된다.
    // 태그를 관리하는 diary_tags 테이블이 자동으로 생긴다. 그다음 diary 테이블에 dno 가 diary_tags 테이블 안에
    // diary_dno 가 fk 로 생기면서 테이블이 alter 된다.
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "tbl_diary_tag")
    @Fetch(value = FetchMode.JOIN)
    @BatchSize(size = 50)
    @Builder.Default
    private Set<String> tags = new HashSet<>();


    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "tbl_diary_picture")
    @Fetch(value = FetchMode.JOIN)
    @BatchSize(size = 50)
    private Set<DiaryPicture> pictures;

}
