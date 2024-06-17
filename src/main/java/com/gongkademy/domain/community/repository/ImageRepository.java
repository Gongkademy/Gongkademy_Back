package com.gongkademy.domain.community.repository;

import com.gongkademy.domain.community.entity.board.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
