package com.gongkademy.domain.board.service;

import com.gongkademy.domain.board.entity.QnA;
import com.gongkademy.domain.board.repository.QnARepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class QnAService {

    final QnARepository qnaRepository;

    public QnA save(QnA qna) {
        return qnaRepository.save(qna);
    }

    public Optional<QnA> findById(Long id) {
        return qnaRepository.findById(id);
    }

    public List<QnA> findAll() {
        return qnaRepository.findAll();
    }

    public void deleteById(Long id) {
        qnaRepository.deleteById(id);
    }
}
