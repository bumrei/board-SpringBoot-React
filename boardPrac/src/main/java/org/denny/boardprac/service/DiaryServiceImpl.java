package org.denny.boardprac.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.denny.boardprac.dto.DiaryDTO;
import org.denny.boardprac.dto.PageRequestDTO;
import org.denny.boardprac.dto.PageResponseDTO;
import org.denny.boardprac.entity.Diary;
import org.denny.boardprac.repository.DiaryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class DiaryServiceImpl implements DiaryService {

    private final ModelMapper modelMapper;

    private final DiaryRepository diaryRepository;

    @Override
    public Long register(DiaryDTO dto) {

        Diary diary = modelMapper.map(dto, Diary.class);
        // ModelMapper converts List to Set data structure which is very interesting.

        log.info(diary);
        log.info(diary.getTags());
        log.info(diary.getPictures());

        diaryRepository.save(diary);

        return diary.getDno();

    }

    @Override
    public DiaryDTO read(Long dno) {

        Optional<Diary> optionalDiary = diaryRepository.findById(dno);

        Diary diary = optionalDiary.orElseThrow();

        DiaryDTO dto = modelMapper.map(diary, DiaryDTO.class);

        return dto;
    }

    @Override
    public PageResponseDTO<DiaryDTO> getList(PageRequestDTO pageRequestDTO) {

        Pageable pageable = PageRequest.of(
                pageRequestDTO.getPage() - 1,
                pageRequestDTO.getSize(),
                Sort.by("dno").descending());

        Page<Diary> result = diaryRepository.findAll(pageable);

        long totalCount = result.getTotalElements();

        List<DiaryDTO> dtoList = result.get().map(diary -> modelMapper.map(diary, DiaryDTO.class)).collect(Collectors.toList());

        return new PageResponseDTO<>(pageRequestDTO, (int) totalCount, dtoList);
    }
}
