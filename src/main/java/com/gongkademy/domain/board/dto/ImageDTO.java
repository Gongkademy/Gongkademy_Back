package com.gongkademy.domain.board.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ImageDTO {
    private Long imageId;
    private Long articleId;
    private String saveFolder;
    private String originalImage;
    private String saveImage;
}
