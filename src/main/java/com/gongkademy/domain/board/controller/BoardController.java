package com.gongkademy.domain.board.controller;

import com.gongkademy.domain.board.dto.BoardDTO;
import com.gongkademy.domain.board.entity.Board;
import com.gongkademy.domain.board.service.BoardService;
import com.gongkademy.domain.board.service.QnAService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final QnAService qnaService;

    @PostMapping
    public ResponseEntity<?> createBoard(@RequestBody BoardDTO boardDTO) {
        Board board = convertToEntity(boardDTO);
        Board saveBoard = boardService.save(board);
        return new ResponseEntity<>(convertToDTO(saveBoard), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBoard(@PathVariable Long id) {
        Optional<Board> board = boardService.findById(id);
        if (board.isPresent()) {
            return new ResponseEntity<>(convertToDTO(board.get(), HttpStatus.OK));
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> getAllBoard() {
        List<Board> boards = boardService.findAll();
        List<BoardDTO> boardDTOS = new ArrayList<>();
        for (Board board : boards) {
            BoardDTO boardDTO = convertToDTO(board);
            boardDTOS.add(boardDTO);
        }
        return new ResponseEntity<>(boardDTOS, HttpStatus.OK);
    }

    private BoardDTO convertToDTO(Board board) {
        BoardDTO boardDTO = new BoardDTO();

        boardDTO.setArticleId(board.getArticleId());
        boardDTO.setMemberId(board.getMember().getId());
        boardDTO.setBoardType(board.getBoardType());
        boardDTO.setTitle(board.getTitle());
        boardDTO.setContent(board.getContent());
        boardDTO.setCreateTime(board.getCreateTime());
        boardDTO.setLikeCount(board.getLikeCount());
        boardDTO.setHit(board.getHit());

        return boardDTO;
    }

    private Board convertToEntity(BoardDTO boardDTO) {
        Board board = new Board();

        board.setBoardType(boardDTO.getBoardType());
        board.setTitle(boardDTO.getTitle());
        board.setContent(boardDTO.getContent());
        board.setCreateTime(boardDTO.getCreateTime());
        board.setLikeCount(boardDTO.getLikeCount());
        board.setHit(boardDTO.getHit());

        // 여러 로직 추가로 필요함 필수

        return board;
    }
}
