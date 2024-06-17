package com.gongkademy.domain.community.entity.board;

import com.gongkademy.domain.community.dto.request.ImageRequestDto;
import com.gongkademy.domain.community.dto.request.QnaBoardRequestDto;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class QnaBoard extends Board {

    private String lectureTitle;
    private String courseTitle;

    public void update(QnaBoardRequestDto qnaBoardRequestDto) {
        List<Image> images = new ArrayList<>();
        List<ImageRequestDto> imageRequestDtos = qnaBoardRequestDto.getImages();

        if (!imageRequestDtos.isEmpty()) {
            for (ImageRequestDto imageRequestDto : imageRequestDtos) {
                images.add(Image.builder()
                        .boardType(qnaBoardRequestDto.getBoardType())
                        .originalImage(imageRequestDto.getOriginalImage())
                        .saveImage(imageRequestDto.getSaveImage())
                        .savedFolder(imageRequestDto.getSavedFolder()).build());
            }
        }

        this.setTitle(qnaBoardRequestDto.getTitle());
        this.setContent(qnaBoardRequestDto.getContent());
        this.setImages(!(images.isEmpty()) ? images : new ArrayList<>());
        this.setLectureTitle(qnaBoardRequestDto.getLectureTitle());
        this.setCourseTitle(qnaBoardRequestDto.getCourseTitle());
    }
}
