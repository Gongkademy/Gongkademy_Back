package com.gongkademy.domain.community.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Inheritance(strategy = InheritanceType.JOINED)
public class Image extends Board{
    private String saveFolder;
    private String originalImage;
    private String saveImage;
}
