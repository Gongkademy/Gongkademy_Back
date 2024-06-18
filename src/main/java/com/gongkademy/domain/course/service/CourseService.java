package com.gongkademy.domain.course.service;

import java.util.List;

import com.gongkademy.domain.course.dto.request.CourseRequestDTO;
import com.gongkademy.domain.course.dto.response.CourseResponseDTO;
import com.gongkademy.domain.course.entity.Course;
import com.gongkademy.domain.course.entity.Lecture;

public interface CourseService {

	List<Course> getAllCourses();
	
	List<Lecture> getCourseContents();
	
	CourseResponseDTO registCourse(CourseRequestDTO courseRequestDTO);
	
	CourseResponseDTO scrapCourse(CourseRequestDTO courseRequestDTO);

	void deleteRegistCourse(Long registCourseId);
	
	/*
	 * TODO
	 * - 강좌 조회, 최근 강의 조회
	 * - 강좌 소개 조회, 공지사항 조회
	 * */
	
}
