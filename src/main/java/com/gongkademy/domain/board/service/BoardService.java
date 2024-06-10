package com.gongkademy.domain.board.service;

import com.gongkademy.domain.board.entity.Board;
import com.gongkademy.domain.board.entity.BoardType;
import com.gongkademy.domain.board.entity.QnA;
import com.gongkademy.domain.board.repository.BoardRepository;
import com.gongkademy.domain.board.repository.QnARepository;
import com.gongkademy.domain.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final QnARepository qnaRepository;

    public Board save(Board board) {
        return boardRepository.save(board);
    }

    public Optional<Board> findById(Long id) {
        return boardRepository.findById(id);
    }

    public List<Board> findAll() {
        return boardRepository.findAll();
    }

    public void deleteById(Long id) {
        boardRepository.deleteById(id);
    }

    public Board createBoard(Member member, BoardType boardType, String title, String content) {
        Board board = new Board();
        board.setMember(member);
        board.setBoardType(boardType);
        board.setTitle(title);
        board.setContent(content);

        if (boardType == BoardType.QNA) {
            QnA qna = new QnA();
            qna.setBoard(board);
            qna.setLectureTitle("지정 필요");
            qna.setCourseTitle("지정 필요");

            qnaRepository.save(qna);
        }

        return board;
    }
}
