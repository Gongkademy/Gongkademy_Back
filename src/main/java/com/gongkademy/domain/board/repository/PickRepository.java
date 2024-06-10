package com.gongkademy.domain.board.repository;

import com.gongkademy.domain.board.entity.Pick;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PickRepository extends JpaRepository<Pick, Long> {
}
