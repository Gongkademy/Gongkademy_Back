package com.gongkademy.domain.board.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageId;

    @ManyToOne
    @JoinColumn(name = "article_id", nullable = false)
    private Board board;

    private String saveFolder;

    private String originalImage;

    private String saveImage;

    // 연관 관계 편의 메서드
    public void setBoard(Board board) {
        this.board = board;
        board.getImages().add(this);
    }
}
