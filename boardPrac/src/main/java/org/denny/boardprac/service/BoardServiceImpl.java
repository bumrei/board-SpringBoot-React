package org.denny.boardprac.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.denny.boardprac.dto.BoardDTO;
import org.denny.boardprac.dto.PageRequestDTO;
import org.denny.boardprac.dto.PageResponseDTO;
import org.denny.boardprac.entity.Board;
import org.denny.boardprac.repository.BoardRepository;
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
public class BoardServiceImpl implements BoardService {

    private final ModelMapper modelMapper;
    private final BoardRepository boardRepository;

    @Override
    public Long register(BoardDTO boardDTO) {
        // DTO -> Entity
        Board board = modelMapper.map(boardDTO, Board.class);

        // repository save() 등록 수정시 사용 -> Long
        boardRepository.save(board); // save 되면 entity 는 자동으로 id 값을 갖게 될 것이다.

        return board.getBno();
    }

    @Override
    public PageResponseDTO<BoardDTO> getList(PageRequestDTO pageRequestDTO) {

        char[] typeArr = pageRequestDTO.getTypes();
        String keyword = pageRequestDTO.getKeyword();


        Pageable pageable = PageRequest.of(pageRequestDTO.getPage() - 1,
                                                pageRequestDTO.getSize(),
                                                Sort.by("bno").descending());
        Page<Board> result = boardRepository.search1(typeArr, keyword, pageable);

        List<BoardDTO> dtoList = result.get().map(board -> modelMapper.map(board, BoardDTO.class)).collect(Collectors.toList());
        long totalCount = result.getTotalElements();

        return new PageResponseDTO<>(pageRequestDTO, (int) totalCount, dtoList);
    }

    @Override
    public BoardDTO read(Long bno) {

        Optional<Board> result = boardRepository.findById(bno);

        if (result.isEmpty()) {
            throw new RuntimeException("Not Found");
        }

        BoardDTO dto = modelMapper.map(result.get(), BoardDTO.class);

        return dto;
    }

    @Override
    public void modify(BoardDTO boardDTO) {
        Optional<Board> result = boardRepository.findById(boardDTO.getBno());

        if(result.isEmpty()) {
            throw new RuntimeException("Not Found");
        }
        Board board = result.get();
        board.change(boardDTO.getTitle(), boardDTO.getContent());
        boardRepository.save(board);

    }

    @Override
    public void remove(Long bno) {

        boardRepository.deleteById(bno);

    }
}
