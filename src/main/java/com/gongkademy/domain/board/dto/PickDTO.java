package com.gongkademy.domain.board.dto;

import com.gongkademy.domain.board.entity.PickType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PickDTO {

    private Long id;
    private Long articleId;
    private Long memberId;
    private PickType pickType;
}
