package com.gongkademy.domain.board.service;

import com.gongkademy.domain.board.entity.Pick;
import com.gongkademy.domain.board.entity.Recomment;
import com.gongkademy.domain.board.repository.PickRepository;
import com.gongkademy.domain.board.repository.RecommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class RecommentService {

    private final RecommentRepository recommentRepository;

    public Recomment save(Recomment recomment) {
        return recommentRepository.save(recomment);
    }

    public Optional<Recomment> findById(Long id) {
        return recommentRepository.findById(id);
    }

    public List<Recomment> findAll() {
        return recommentRepository.findAll();
    }

    public void deleteById(Long id) {
        recommentRepository.deleteById(id);
    }
}
