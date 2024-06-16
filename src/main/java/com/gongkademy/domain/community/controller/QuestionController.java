package com.gongkademy.domain.community.controller;

import com.gongkademy.domain.community.dto.request.QnaBoardRequestDto;
import com.gongkademy.domain.community.dto.response.QnaBoardResponseDto;
import com.gongkademy.domain.community.service.QnaBoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/community")
@RequiredArgsConstructor
@Slf4j
public class QuestionController {
    private final QnaBoardService qnaboardService;

    // Qna 전체 리스트 반환
    @GetMapping("/question")
    public ResponseEntity<?> selectAllQna(){
        List<QnaBoardResponseDto> result = qnaboardService.findQnaBoardsAll();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // Qna 작성
    @PostMapping("/question")
    public ResponseEntity<?> createQna(@RequestBody QnaBoardRequestDto qnaBoardRequestDto) {
        QnaBoardResponseDto qnaBoardResponseDto = qnaboardService.createQnaBoard(qnaBoardRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(qnaBoardResponseDto);
    }

    // Qna 수정
    @PatchMapping("/question/{articleNo}")
    public ResponseEntity<?> updateQna(@PathVariable Long articleNo, @RequestBody QnaBoardRequestDto qnaBoardRequestDto) {
        Long updateArticleNo = qnaboardService.updateQnaBoard(articleNo, qnaBoardRequestDto);

        // 해당 Qna 게시글이 없는 경우
        if (updateArticleNo == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(updateArticleNo);
        }
    }

    // Qna 삭제
    @DeleteMapping("/question/{articleNo}")
    public ResponseEntity<?> deleteQna(@PathVariable Long articleNo) {
        qnaboardService.deleteQnaBoard(articleNo);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}