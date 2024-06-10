package com.gongkademy.domain.board.repository;

import com.gongkademy.domain.board.entity.Recomment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecommentRepository extends JpaRepository<Recomment, Long> {

}
