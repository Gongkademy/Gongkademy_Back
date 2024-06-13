package com.gongkademy.domain.community.repository;

import com.gongkademy.domain.community.entity.QnaBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QnaBoardRepository extends JpaRepository<QnaBoard, Long> {
    @Query("SELECT qb FROM QnaBoard qb")
    List<QnaBoard> findQnaBoardsAll();
}
