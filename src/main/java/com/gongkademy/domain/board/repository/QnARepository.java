package com.gongkademy.domain.board.repository;

import com.gongkademy.domain.board.entity.QnA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QnARepository extends JpaRepository<QnA, Long> {
}
