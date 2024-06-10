package com.gongkademy.domain.board.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {
    private Long commentId;
    private Long articleId;
    private Long memberId;
    private String content;
    private String nickname;
    private Long likeCount;
}
