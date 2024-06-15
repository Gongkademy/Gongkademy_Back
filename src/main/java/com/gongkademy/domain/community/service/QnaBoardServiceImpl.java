package com.gongkademy.domain.community.service;

import com.gongkademy.domain.community.dto.request.ImageRequestDto;
import com.gongkademy.domain.community.dto.request.QnaBoardRequestDto;
import com.gongkademy.domain.community.dto.response.ImageResponseDto;
import com.gongkademy.domain.community.dto.response.QnaBoardResponseDto;
import com.gongkademy.domain.community.entity.board.ImageBoard;
import com.gongkademy.domain.community.entity.board.QnaBoard;
import com.gongkademy.domain.community.repository.QnaBoardRepository;
import com.gongkademy.domain.member.entity.Member;
import com.gongkademy.domain.member.repository.MemberRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QnaBoardServiceImpl implements QnaBoardService {

    private final QnaBoardRepository qnaBoardRepository;
    private final MemberRepositoryImpl memberRepositoryImpl;

    // 전체 QnaBoard 조회
    public List<QnaBoardResponseDto> findQnaBoardsAll() {
        List<QnaBoard> qnaBoards = qnaBoardRepository.findAll();
        List<QnaBoardResponseDto> qnaBoardResponseDto = new ArrayList<>();

        for (QnaBoard qnaBoard : qnaBoards) {
            qnaBoardResponseDto.add(convertToDto(qnaBoard));
        }

        return qnaBoardResponseDto;
    }

    // QnaBoard 생성
    public QnaBoardResponseDto createQnaBoard(QnaBoardRequestDto qnaBoardRequestDto) {
        QnaBoard qnaBoard = convertToEntity(qnaBoardRequestDto);
        QnaBoard saveBoard = qnaBoardRepository.save(qnaBoard);
        return convertToDto(saveBoard);
    }

    // QnaBoard 조회
    public QnaBoardResponseDto findQnaBoard(Long articleId) {
        Optional<QnaBoard> optionalQnaBoard = qnaBoardRepository.findById(articleId);

        if(optionalQnaBoard.isPresent()) {
            QnaBoard qnaBoard = optionalQnaBoard.get();

            // 조회수 기능 넣어야 함
            return convertToDto(qnaBoard);
        }

        throw new IllegalStateException("게시판 찾을 수 없음");
    }

    // QnaBoard 수정
    public Long updateQnaBoard(Long articleId, QnaBoardRequestDto qnaBoardRequestDto) {
        Optional<QnaBoard> optQnaBoard = qnaBoardRepository.findById(articleId);

        // articleId에 해당하는 게시글이 없는 경우
        if (optQnaBoard.isEmpty()) {
            return null;
        }

        QnaBoard qnaBoard = optQnaBoard.get();

        // 이미지 관련 메서드 정의

        // 항목 수정하기
        qnaBoard.update(qnaBoardRequestDto);
        return qnaBoard.getArticleId();
    }

    // QnaBoard 삭제
    public void deleteQnaBoard(Long articleId) {
        qnaBoardRepository.deleteById(articleId);
    }

    private QnaBoard convertToEntity(QnaBoardRequestDto qnaBoardRequestDto) {
        QnaBoard qnaBoard = new QnaBoard();
        qnaBoard.setBoardType(qnaBoardRequestDto.getBoardType());

        Optional<Member> memberOptional = memberRepositoryImpl.findById(qnaBoardRequestDto.getMemberId());
        if (memberOptional.isPresent()) {
            qnaBoard.setMember(memberOptional.get());
        } else {
            throw new IllegalStateException("사용자 찾을 수 없음");
        }

        List<ImageRequestDto> imageRequestDtoList = qnaBoardRequestDto.getImages();

        if (!imageRequestDtoList.isEmpty()) {
            for (ImageRequestDto imageRequestDto : imageRequestDtoList) {
                qnaBoard.setSaveImage(imageRequestDto.getSaveImage());
                qnaBoard.setOriginalImage(imageRequestDto.getOriginalImage());
                qnaBoard.setSavedFolder(imageRequestDto.getSavedFolder());
            }
        }

        qnaBoard.setTitle(qnaBoardRequestDto.getTitle());
        qnaBoard.setContent(qnaBoardRequestDto.getContent());
        qnaBoard.setHit(0L);
        qnaBoard.setLikeCount(0L);
        return qnaBoard;
    }


    private QnaBoardResponseDto convertToDto(QnaBoard qnaBoard) {
        QnaBoardResponseDto qnaBoardResponseDto = new QnaBoardResponseDto();
        List<ImageResponseDto> imageResponseDtos = new ArrayList<>();
        List<ImageBoard> imageBoards = qnaBoardRepository.findImageBoards(qnaBoard.getBoardType(), qnaBoard.getArticleId());

        for (ImageBoard imageBoard : imageBoards) {
            imageResponseDtos.add(new ImageResponseDto(imageBoard.getOriginalImage(), imageBoard.getSaveImage(), imageBoard.getSavedFolder()));
        }

        qnaBoardResponseDto.setBoardType(qnaBoard.getBoardType());
        qnaBoardResponseDto.setArticleId(qnaBoard.getArticleId());
        qnaBoardResponseDto.setMemberId(qnaBoard.getMember().getId());
        qnaBoardResponseDto.setTitle(qnaBoard.getTitle());
        qnaBoardResponseDto.setContent(qnaBoard.getContent());
        qnaBoardResponseDto.setLikeCount(qnaBoard.getLikeCount());
        qnaBoardResponseDto.setHit(qnaBoard.getHit());
        qnaBoardResponseDto.setCreateTime(qnaBoard.getCreateTime());
        qnaBoardResponseDto.setImages(imageResponseDtos);

        return qnaBoardResponseDto;
    }
}
