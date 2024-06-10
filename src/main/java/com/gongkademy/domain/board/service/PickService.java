package com.gongkademy.domain.board.service;

import com.gongkademy.domain.board.entity.Image;
import com.gongkademy.domain.board.entity.Pick;
import com.gongkademy.domain.board.repository.ImageRepository;
import com.gongkademy.domain.board.repository.PickRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class PickService {

    private final PickRepository pickRepository;

    public Pick save(Pick pick) {
        return pickRepository.save(pick);
    }

    public Optional<Pick> findById(Long id) {
        return pickRepository.findById(id);
    }

    public List<Pick> findAll() {
        return pickRepository.findAll();
    }

    public void deleteById(Long id) {
        pickRepository.deleteById(id);
    }
}
