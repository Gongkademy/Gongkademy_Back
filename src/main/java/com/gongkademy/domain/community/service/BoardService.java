package com.gongkademy.domain.community.service;

import com.gongkademy.domain.community.dto.request.BoardRequestDTO;
import com.gongkademy.domain.community.dto.response.BoardResponseDTO;

import java.util.List;

public interface BoardService {

    BoardResponseDTO getBoard(Long id, Long memberId);

    void incrementHit(Long id);

    List<BoardResponseDTO> getLatestBoards(int index);

    void toggleLikeBoard(Long articleId, Long memberId);

    void toggleScrapBoard(Long articleId, Long memberId);

    List<BoardResponseDTO> getLikeBoards(Long memberId);

    List<BoardResponseDTO> getScrapBoards(Long memberId);

}
