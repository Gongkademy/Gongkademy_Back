package com.gongkademy.domain.community.repository;

import com.gongkademy.domain.community.entity.board.BoardType;
import com.gongkademy.domain.community.entity.board.Image;
import com.gongkademy.domain.community.entity.board.QnaBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QnaBoardRepository extends JpaRepository<QnaBoard, Long> {

    @Query("SELECT i FROM Image i WHERE i.boardType = :boardType AND i.articleId = :articleId")
    List<Image> findImages(BoardType boardType, Long articleId);

    Page<QnaBoard> findAll(Pageable pageable);
}
