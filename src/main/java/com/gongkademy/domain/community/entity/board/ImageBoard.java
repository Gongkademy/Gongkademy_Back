package com.gongkademy.domain.community.entity.board;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class ImageBoard extends Board {

    private String savedFolder;

    private String originalImage;

    private String saveImage;
}