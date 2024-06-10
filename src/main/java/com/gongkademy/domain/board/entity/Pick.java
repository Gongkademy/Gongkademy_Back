package com.gongkademy.domain.board.entity;

import com.gongkademy.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Pick {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "article_id", nullable = false)
    private Board board;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PickType pickType; // 'LIkE', 'SCRAP'

    // 연관 관계 편의 메서드
    public void setBoard(Board board) {
        this.board = board;
        board.getPicks().add(this);
    }

    public void setMember(Member member) {
        this.member = member;
        member.getPicks().add(this);
    }
}
