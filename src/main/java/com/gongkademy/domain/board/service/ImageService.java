package com.gongkademy.domain.board.service;

import com.gongkademy.domain.board.entity.Comment;
import com.gongkademy.domain.board.entity.Image;
import com.gongkademy.domain.board.repository.CommentRepository;
import com.gongkademy.domain.board.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;

    public Image save(Image image) {
        return imageRepository.save(image);
    }

    public Optional<Image> findById(Long id) {
        return imageRepository.findById(id);
    }

    public List<Image> findAll() {
        return imageRepository.findAll();
    }

    public void deleteById(Long id) {
        imageRepository.deleteById(id);
    }
}
