package com.gongkademy.domain.board.entity;

import com.gongkademy.domain.member.entity.Member;
import jakarta.persistence.*;
import jdk.jfr.consumer.RecordedMethod;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @ManyToOne
    @JoinColumn(name = "aritcle_id", nullable = false)
    private Board board;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false, length = 30)
    private String nickname;

    @OneToMany(mappedBy = "comment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Recomment> recomments = new ArrayList<>();

    @OneToMany(mappedBy = "comment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CommentLike> commentLikes = new ArrayList<>();

    // 연관 관계 편의 메서드
    public void addRecomment(Recomment recomment) {
        recomments.add(recomment);
        recomment.setComment(this);
    }

    public void addCommentLike(CommentLike commentLike) {
        commentLikes.add(commentLike);
        commentLike.setComment(this);
    }
}
