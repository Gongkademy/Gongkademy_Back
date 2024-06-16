package com.gongkademy.domain.community.service;

import com.gongkademy.domain.community.dto.request.CommentLikeRequestDTO;
import com.gongkademy.domain.community.dto.response.CommentLikeResponseDTO;
import com.gongkademy.domain.community.entity.comment.Comment;
import com.gongkademy.domain.community.entity.comment.CommentLike;
import com.gongkademy.domain.community.repository.CommentLikeRepository;
import com.gongkademy.domain.community.repository.CommentRepository;
import com.gongkademy.domain.member.entity.Member;
import com.gongkademy.domain.member.repository.MemberRepositoryImpl;
import com.gongkademy.global.exception.CustomException;
import com.gongkademy.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentLikeServiceImpl implements CommentLikeService {

    private final CommentLikeRepository commentLikeRepository;
    private final CommentRepository commentRepository;
    private final MemberRepositoryImpl memberRepositoryImpl;

    @Override
    public CommentLikeResponseDTO createCommentLike(CommentLikeRequestDTO commentLikeRequestDTO) {
        CommentLike commentLike = convertToEntity(commentLikeRequestDTO);
        CommentLike saveCommentLike = commentLikeRepository.save(commentLike);
        incrementLikeCount(commentLike.getComment().getCommentId());
        return convertToDTO(saveCommentLike);
    }

    @Override
    public CommentLikeResponseDTO updateCommentLike(Long id, CommentLikeRequestDTO commentLikeRequestDTO) {
        Optional<CommentLike> commentLikeOptional = commentLikeRepository.findById(id);

        if (commentLikeOptional.isPresent()) {
            CommentLike commentLike = commentLikeOptional.get();
            commentLike.setCommentType(commentLikeRequestDTO.getCommentType());
            commentLikeRepository.save(commentLike);
            return convertToDTO(commentLike);
        }
        throw new CustomException(ErrorCode.INVALID_COMMENTLIKE_ID);
    }

    @Override
    public CommentLikeResponseDTO getCommentLike(Long id) {
        Optional<CommentLike> commentLikeOptional = commentLikeRepository.findById(id);

        if (commentLikeOptional.isPresent()) {
            return convertToDTO(commentLikeOptional.get());
        }
        throw new CustomException(ErrorCode.INVALID_COMMENTLIKE_ID);
    }

    @Override
    public List<CommentLikeResponseDTO> getAllCommentLikes() {
        List<CommentLike> commentLikes = commentLikeRepository.findAll();
        List<CommentLikeResponseDTO> commentLikeResponseDTOS = new ArrayList<>();
        for (CommentLike commentLike : commentLikes) {
            commentLikeResponseDTOS.add(convertToDTO(commentLike));
        }
        return commentLikeResponseDTOS;
    }

    @Override
    public void deleteCommentLike(Long id) {
        Optional<CommentLike> commentLikeOptional = commentLikeRepository.findById(id);
        if (commentLikeOptional.isPresent()) {
            decrementLikeCount(commentLikeOptional.get().getComment().getCommentId());
            commentLikeRepository.deleteById(id);
        } else {
            throw new CustomException(ErrorCode.INVALID_COMMENTLIKE_ID);
        }
    }

    @Override
    public void incrementLikeCount(Long commentId) {
        Optional<Comment> commentOptional = commentRepository.findById(commentId);
        if (commentOptional.isPresent()) {
            Comment comment = commentOptional.get();
            comment.setLikeCount(comment.getLikeCount() + 1);
            commentRepository.save(comment);
        } else {
            throw new CustomException(ErrorCode.INVALID_COMMENT_ID);
        }
    }

    @Override
    public void decrementLikeCount(Long commentId) {
        Optional<Comment> commentOptional = commentRepository.findById(commentId);
        if (commentOptional.isPresent()) {
            Comment comment = commentOptional.get();
            comment.setLikeCount(comment.getLikeCount() - 1);
            commentRepository.save(comment);
        } else {
            throw new CustomException(ErrorCode.INVALID_COMMENT_ID);
        }
    }

    private CommentLike convertToEntity(CommentLikeRequestDTO commentLikeRequestDTO) {
        CommentLike commentLike = new CommentLike();

        Optional<Comment> commentOptional = commentRepository.findById(commentLikeRequestDTO.getCommentId());
        if (commentOptional.isPresent()) {
            commentLike.setComment(commentOptional.get());
        } else {
            throw new CustomException(ErrorCode.INVALID_COMMENT_ID);
        }

        Optional<Member> memberOptional = memberRepositoryImpl.findById(commentLikeRequestDTO.getMemberId());
        if (memberOptional.isPresent()) {
            commentLike.setMember(memberOptional.get());
        } else {
            throw new CustomException(ErrorCode.INVALID_MEMBER_ID);
        }

        commentLike.setCommentType(commentLikeRequestDTO.getCommentType());
        return commentLike;
    }

    private CommentLikeResponseDTO convertToDTO(CommentLike commentLike) {
        CommentLikeResponseDTO commentLikeResponseDTO = new CommentLikeResponseDTO();
        commentLikeResponseDTO.setId(commentLike.getId());
        commentLikeResponseDTO.setCommentId(commentLike.getComment().getCommentId());
        commentLikeResponseDTO.setMemberId(commentLike.getMember().getId());
        commentLikeResponseDTO.setCommentType(commentLike.getCommentType());
        return commentLikeResponseDTO;
    }
}
