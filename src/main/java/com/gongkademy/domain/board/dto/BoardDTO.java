package com.gongkademy.domain.board.dto;

import com.gongkademy.domain.board.entity.BoardType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO {

    private Long articleId;
    private Long memberId;
    private BoardType boardType;
    private String title;
    private String content;
    private LocalDateTime createTime;
    private Long likeCount;
    private Long hit;
}
