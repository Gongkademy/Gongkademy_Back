package com.gongkademy.domain.community.repository;

import com.gongkademy.domain.community.entity.board.QnaBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QnaBoardRepository extends JpaRepository<QnaBoard, Long> {
//    @Query("SELECT qb FROM QnABoard qb")
//    List<QnaBoard> findQnaBoardsAll();
}
