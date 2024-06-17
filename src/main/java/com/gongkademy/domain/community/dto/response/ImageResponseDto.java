package com.gongkademy.domain.community.dto.response;

import com.gongkademy.domain.community.entity.board.BoardType;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImageResponseDto {
    private Long articleId;
    private BoardType boardType;
    private String savedFolder;
    private String originalImage;
    private String saveImage;
}
