package com.gongkademy.domain.community.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class QnaBoard extends Image {
    private String lecture_title;
    private String course_title;
}
