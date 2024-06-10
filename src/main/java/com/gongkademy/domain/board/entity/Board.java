package com.gongkademy.domain.board.entity;

import com.gongkademy.domain.member.entity.Member;
import com.gongkademy.domain.member.entity.MemberRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long articleId;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BoardType boardType;    // NOTICE, QNA, CONSULT

    private String title;

    private String content;

    private LocalDateTime createTime = LocalDateTime.now();

    private Long likeCount;

    private Long hit;

    @OneToOne(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private QnA qna;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Image> images = new ArrayList<>();

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pick> picks = new ArrayList<>();


    // 연관 관계 편의 메서드
    public void addComment(Comment comment) {
        comments.add(comment);
        comment.setBoard(this);
    }

    public void addImage(Image image) {
        images.add(image);
        image.setBoard(this);
    }

    public void addPick(Pick pick) {
        picks.add(pick);
        pick.setBoard(this);
    }

    public void setQnA(QnA qna) {
        this.qna = qna;
        if (qna != null && qna.getBoard() != null) {
            qna.setBoard(this);
        }
    }
}
