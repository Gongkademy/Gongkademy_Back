package com.gongkademy.domain.board.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QnADTO {

    private Long id;
    private Long articleId;
    private String lectureTitle;
    private String courseTitle;
}
