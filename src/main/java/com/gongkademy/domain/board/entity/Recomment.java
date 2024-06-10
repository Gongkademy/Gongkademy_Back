package com.gongkademy.domain.board.entity;

import com.gongkademy.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Recomment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recommentId;

    @ManyToOne
    @JoinColumn(name = "comment_id", nullable = false)
    private Comment comment;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(nullable = false, length = 30)
    private String nickname;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Long likeCount;

    @OneToMany(mappedBy = "recomment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CommentLike> commentLikes = new ArrayList<>();

    // 연관 관계 편의 메서드
    public void addCommentLike(CommentLike commentLike) {
        commentLikes.add(commentLike);
        commentLike.setRecomment(this);
    }
}
