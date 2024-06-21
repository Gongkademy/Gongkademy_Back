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
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Course {

	@Id @GeneratedValue
	@Column(name="course_id")
	private Long id;
	
	private Long totalCourseTime;
	
	private String title;
	
	private double avgRating;
	
	private Long reviewCount;
	
	private Long registCount;
	
	private Long lectureCount;
	
	private String content;
	
	private String status;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="course_img_id")
	private CourseFile courseImg; // 강좌 대표 이미지
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="course_note_id")
	private CourseFile courseNote; // 강좌 자료
	
	@OneToMany(mappedBy="course")
	private List<Lecture> lectures = new ArrayList<>();
	
	@OneToMany(mappedBy="course" , cascade = CascadeType.ALL, orphanRemoval = true)
	private List<RegistCourse> registCourses = new ArrayList<>();
	
	@OneToMany(mappedBy="course", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Scrap> scraps = new ArrayList<>();
	
	@OneToMany(mappedBy="course" , cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Notice> notices = new ArrayList<>();
	
	@OneToMany(mappedBy="course")
	private List<CourseReview> courseReviews = new ArrayList<>();
	
//	@OneToMany(mappedBy="preCourse" , cascade = CascadeType.ALL, orphanRemoval = true)
//	private List<PreCourse> preCourses = new ArrayList<>();
//	
//	@OneToMany(mappedBy="nextCourse" , cascade = CascadeType.ALL, orphanRemoval = true)
//	private List<PreCourse> nextCourses = new ArrayList<>();

	// ==연관관계 메서드==//
	//== add ==//
	// 강의 add
	public void addLecture(Lecture lecture) {
		this.lectures.add(lecture);
		lecture.setCourse(this);
	}
	// 수강생 add
	public void addRegist(RegistCourse registCourse) {
		this.registCourses.add(registCourse);
		registCourse.setCourse(this);
	}
	// 스크랩 add
	public void addScrap(Scrap scrap) {
		this.scraps.add(scrap);
		scrap.setCourse(this);
	}
	// 공지사항 add
	public void addNotice(Notice notice) {
		this.notices.add(notice);
		notice.setCourse(this);
	}
	// 수강평 add
	public void addReveiw(CourseReview courseReview) {
		this.courseReviews.add(courseReview);
		courseReview.setCourse(this);
	}
	
	
	//== delete ==//
	// 강의 delete
	public void deleteLecture(Lecture lecture) {
		this.lectures.remove(lecture);
		lecture.setCourse(null);
	}
	// 수강생 delete
	public void deleteRegist(RegistCourse registCourse) {
		this.registCourses.remove(registCourse);
	}
	// 스트랩 delete
	public void deleteScrap(Scrap scrap) {
		this.scraps.remove(scrap);
	}
	// 공지사항 delete
	public void deleteNotice(Notice notice) {
		this.notices.remove(notice);
	}
	// 수강평 delete
	public void deleteReveiw(CourseReview courseReview) {
		this.courseReviews.remove(courseReview);
		courseReview.setCourse(null);
	}
	
	
	//== 비즈니스 로직== //
	//== update ==//
	// 수강평수 count
	public void updateReviewCount() {
        this.reviewCount = (long) courseReviews.size();
    }
	
	// 수강생 수 count
	public void updateRegistCount() {
        this.registCount = (long) registCourses.size();
    }
	
	// lecture 수 count
	public void updateLectureCount() {
        this.lectureCount = (long) lectures.size();
    }
	
	// 평점 평균 구하기
	public void updateAvgRating() {
		double avg = 0;
		
		if (courseReviews.isEmpty()) this.avgRating = avg;
		
		else {
			for (CourseReview review : courseReviews) {
		        avg += review.getRating();
		    }
			
			this.avgRating = avg / courseReviews.size();
		}
	}
}
