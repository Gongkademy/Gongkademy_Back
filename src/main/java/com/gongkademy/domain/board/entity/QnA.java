package com.gongkademy.domain.board.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class QnA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    @JoinColumn(name = "article_id", nullable = false)
    private Board board;

    private String lectureTitle;

    private String courseTitle;

    // 연관 관계 편의 메서드
    public void setBoard(Board board) {
        this.board = board;
        board.setQna(this);
    }
}
