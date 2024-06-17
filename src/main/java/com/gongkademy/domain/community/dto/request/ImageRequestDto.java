package com.gongkademy.domain.community.dto.request;


import com.gongkademy.domain.community.entity.board.BoardType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ImageRequestDto {
    private String articleId;
    private BoardType boardType;
    private String savedFolder;
    private String originalImage;
    private String saveImage;
}
