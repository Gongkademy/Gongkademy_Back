package com.gongkademy.domain.board.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RecommentDTO {

    private Long recommentId;
    private Long commentId;
    private Long memberId;
    private String content;
    private String nickname;
    private Long likeCount;
}
