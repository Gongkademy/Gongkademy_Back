package com.gongkademy.domain.course.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Lecture {

	@Id
	@GeneratedValue
	@Column(name = "lecture_id")
	private Long id;
	
	private int lectureOrder;
	
	private Long time;
	
	private String link;
	
	private String title;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="course_id")
	private Course course;
	
	@OneToMany(mappedBy = "lecture",cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<RegistLecture> registLectures = new ArrayList<>();
	
	//== 연관관계 메소드 == //
	public void addRegistLecture(RegistLecture registLecture) {
		registLectures.add(registLecture);
		registLecture.setLecture(this);
	}


}
