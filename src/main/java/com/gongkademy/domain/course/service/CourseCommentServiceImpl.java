package com.gongkademy.domain.course.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gongkademy.domain.course.dto.request.CourseCommentRequestDTO;
import com.gongkademy.domain.course.dto.response.CourseCommentResponseDTO;
import com.gongkademy.domain.course.entity.CommentCateg;
import com.gongkademy.domain.course.entity.CourseComment;
import com.gongkademy.domain.course.entity.CourseReview;
import com.gongkademy.domain.course.entity.Notice;
import com.gongkademy.domain.course.repository.CourseCommentRepository;
import com.gongkademy.domain.course.repository.CourseReviewRepository;
import com.gongkademy.domain.course.repository.NoticeRepository;
import com.gongkademy.domain.member.entity.Member;
import com.gongkademy.domain.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class CourseCommentServiceImpl implements CourseCommentService {

	private final CourseCommentRepository courseCommentRepository;
	private final MemberRepository memberRepository;
	private final CourseReviewRepository courseReviewRepository;
	private final NoticeRepository noticeRepository;
	
	@Override
	public CourseCommentResponseDTO createComment(CourseCommentRequestDTO courseCommentRequestDTO, Long currentMemberId) {
		CourseComment comment = convertToEntity(courseCommentRequestDTO);
		comment.setMember(memberRepository.findById(currentMemberId).get()); //댓글 작성자 = 현재이용자
        CourseComment saveComment = courseCommentRepository.save(comment);
        
        if(saveComment.getCommentCateg()==CommentCateg.REVIEW) {
        	CourseReview review = saveComment.getCourseReview();
        	review.addCourseComment(saveComment);
        	review.updateCourseCommentCount();
        }
        
        else if(saveComment.getCommentCateg()==CommentCateg.NOTICE) {
        	Notice notice = saveComment.getNotice();
        	notice.addCourseComment(saveComment);
        	notice.updateCourseCommentCount();
        }
        
        return convertToDTO(saveComment);
	}

	@Override
	public CourseCommentResponseDTO updateComment(Long id, CourseCommentRequestDTO courseCommentRequestDTO) {
        Optional<CourseComment> commentOptional = courseCommentRepository.findById(id);

        if (commentOptional.isPresent()) {
            CourseComment comment = commentOptional.get();
            comment.setContent(courseCommentRequestDTO.getContent());
            courseCommentRepository.save(comment);
            return convertToDTO(comment);
        }

        throw new IllegalStateException("댓글을 찾을 수 없음");
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<CourseCommentResponseDTO> getAllComments(CommentCateg categ, Long id) {
        List<CourseComment> comments = courseCommentRepository.findAllByCommentCategAndId(categ, id);
        List<CourseCommentResponseDTO> courseCommentResponseDTOs = new ArrayList<>();
        for (CourseComment comment : comments) {
        	courseCommentResponseDTOs.add(convertToDTO(comment));
        }
        return courseCommentResponseDTOs;
	}

	@Override
	public void deleteComment(Long id) {
		Optional<CourseComment> comment = courseCommentRepository.findById(id);
		courseCommentRepository.deleteById(id);

		if (comment.get().getCommentCateg() == CommentCateg.REVIEW) {
			CourseReview review = comment.get().getCourseReview();
			review.deleteCourseComment(comment.get());
			review.updateCourseCommentCount();
		}

		else if (comment.get().getCommentCateg() == CommentCateg.NOTICE) {
			Notice notice = comment.get().getNotice();
			notice.deleteCourseComment(comment.get());
			notice.updateCourseCommentCount();
		}
	}
	
    private CourseComment convertToEntity(CourseCommentRequestDTO courseCommentRequestDTO) {
    	CourseComment comment = new CourseComment();
    	
    	if(courseCommentRequestDTO.getCommentType()==CommentCateg.REVIEW) {
    		Optional<CourseReview> reviewOptional = courseReviewRepository.findById(courseCommentRequestDTO.getCourseReviewId());
    		if (reviewOptional.isPresent()) {
    			comment.setCourseReview(reviewOptional.get());
    		} else {
    			throw new IllegalStateException("리뷰 찾을 수 없음");
    		}
    	}
    	if(courseCommentRequestDTO.getCommentType()==CommentCateg.NOTICE) {
    		Optional<Notice> noticeOptional = noticeRepository.findById(courseCommentRequestDTO.getNoticeId());
    		if (noticeOptional.isPresent()) {
    			comment.setNotice(noticeOptional.get());
    		} else {
    			throw new IllegalStateException("공지사항 찾을 수 없음");
    		}
    	}
        comment.setCommentCateg(courseCommentRequestDTO.getCommentType());
        Optional<Member> memberOptional = memberRepository.findById(courseCommentRequestDTO.getMemberId());
        if (memberOptional.isPresent()) {
           comment.setMember(memberOptional.get());
        } else {
            throw new IllegalStateException("사용자 찾을 수 없음");
        }
    	comment.setNickname(courseCommentRequestDTO.getNickname());
    	comment.setContent(courseCommentRequestDTO.getContent());
    	comment.setLikeCount(courseCommentRequestDTO.getLikeCount());
        return comment;
    }

    private CourseCommentResponseDTO convertToDTO(CourseComment comment) {
    	CourseCommentResponseDTO courseCommentResponseDTO = new CourseCommentResponseDTO();
    	courseCommentResponseDTO.setCourseCommentId(comment.getId());
    	courseCommentResponseDTO.setCourseReviewId(comment.getCourseReview().getId());
    	courseCommentResponseDTO.setNoticeId(comment.getNotice().getId());
    	courseCommentResponseDTO.setCommentCateg(comment.getCommentCateg());
    	courseCommentResponseDTO.setMemberId(comment.getMember().getId());
    	courseCommentResponseDTO.setNickname(comment.getNickname());
    	courseCommentResponseDTO.setContent(comment.getContent());
    	courseCommentResponseDTO.setLikeCount(comment.getLikeCount());
        return courseCommentResponseDTO;
    }

}
