package com.gongkademy.domain.course.service;

import java.util.List;

import com.gongkademy.domain.course.dto.request.CourseRequestDTO;
import com.gongkademy.domain.course.dto.response.CourseResponseDTO;
import com.gongkademy.domain.course.entity.Course;
import com.gongkademy.domain.course.entity.Lecture;
import com.gongkademy.domain.course.entity.Notice;

public interface CourseService {

	List<CourseResponseDTO> getAllCourses();
	
	List<Lecture> getCourseContents();
	
	CourseResponseDTO registCourse(CourseRequestDTO courseRequestDTO);
	
	CourseResponseDTO scrapCourse(CourseRequestDTO courseRequestDTO);

	void deleteRegistCourse(Long registCourseId);
	
	CourseResponseDTO getCourseDetail(Long courseId);
	
	List<Notice> getCourseNotices(Long courseId);
	
	
	/*
	 * TODO
	 * - 최근 강의 조회
	 * - 강좌 소개 조회,
	 * */
	
	
}
