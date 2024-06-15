package com.gongkademy.domain.community.service;

import com.gongkademy.domain.community.dto.request.CommentRequestDTO;
import com.gongkademy.domain.community.dto.response.CommentResponseDTO;

import java.util.List;

public interface CommentService {

    CommentResponseDTO createComment(CommentRequestDTO commentRequestDTO);

    // 댓글 수정 필요한 지
//    CommentResponseDTO updateComment(Long id, CommentRequestDTO commentRequestDTO);

    List<CommentResponseDTO> getComments(Long articleId);

    void deleteComment(Long id);
}
