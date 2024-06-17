package com.gongkademy.domain.community.entity.board;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageId;

    @ManyToOne
    @JoinColumn(name = "articleId", nullable = false)
    private Board board;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BoardType boardType;

    private String savedFolder;
    private String originalImage;
    private String saveImage;

    // 연관관계 편의 메서드
    public void setImage(Board board) {
        this.board = board;
        board.getImages().add(this);
    }
}
