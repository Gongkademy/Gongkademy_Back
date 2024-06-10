package com.gongkademy.domain.board.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentLikeDTO {

    private Long id;
    private Long memberId;
    private Long commentId;
    private Long recommentId;
    private int type;
}
