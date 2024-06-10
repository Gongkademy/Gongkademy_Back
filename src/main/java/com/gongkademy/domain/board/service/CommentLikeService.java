package com.gongkademy.domain.board.service;

import com.gongkademy.domain.board.entity.Comment;
import com.gongkademy.domain.board.entity.CommentLike;
import com.gongkademy.domain.board.entity.QnA;
import com.gongkademy.domain.board.repository.CommentLikeRepository;
import com.gongkademy.domain.board.repository.QnARepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentLikeService {

    private final CommentLikeRepository commentLikeRepository;

    public CommentLike save(CommentLike commentLike) {
        return commentLikeRepository.save(commentLike);
    }

    public Optional<CommentLike> findById(Long id) {
        return commentLikeRepository.findById(id);
    }

    public List<CommentLike> findAll() {
        return commentLikeRepository.findAll();
    }

    public void deleteById(Long id) {
        commentLikeRepository.deleteById(id);
    }
}
