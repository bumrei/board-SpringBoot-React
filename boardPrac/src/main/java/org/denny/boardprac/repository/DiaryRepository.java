package org.denny.boardprac.repository;

import org.denny.boardprac.entity.Diary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DiaryRepository extends JpaRepository<Diary, Long> {

    @Query("select d from Diary d left join d.tags dt")
    Page<Diary> searchTags(String tag, Pageable pageable);

}
