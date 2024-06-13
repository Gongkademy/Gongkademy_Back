package com.gongkademy.domain.community.entity;

import com.gongkademy.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
@Inheritance(strategy = InheritanceType.JOINED)
public class Board {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_no")
    private Long articleNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bname")
    private BoardType bname; // board type (공지사항, qna, 고민)

    private String title;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private Member member;

    private LocalDateTime createTime; // 생성 날짜 및 시간
    private int likeCnt; // 좋아요 수
    private int hit; // 조회수
}
