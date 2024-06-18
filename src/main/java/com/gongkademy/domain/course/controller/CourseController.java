package com.gongkademy.domain.course.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gongkademy.domain.course.dto.request.CourseRequestDTO;
import com.gongkademy.domain.course.dto.response.CourseResponseDTO;
import com.gongkademy.domain.course.service.CourseService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/course")
@RequiredArgsConstructor
public class CourseController {

	private final CourseService courseService;
	
	// 1. 전체 강좌 관련
	// - 전체 강좌 목록 조회
	@GetMapping("")
	public ResponseEntity<?> getAllCourses(){
		
	}
	
	// - 목차 조회
	@GetMapping("list/{course_id}")
	public ResponseEntity<?> getCourseContents(){
		
	}
	
	// 2. 강좌 상세 관련
	// - 강좌 수강
	@PostMapping("/regist")
	public ResponseEntity<?> registCourse(@RequestBody CourseRequestDTO courseRequestDTO){
		CourseResponseDTO courseResponseDTO = courseService.registCourse(courseRequestDTO);
        return new ResponseEntity<>(courseResponseDTO, HttpStatus.CREATED);
	}
	
	// - 강좌 저장
	@PostMapping("/scrap")
	public ResponseEntity<?> scrapCourse(@RequestBody CourseRequestDTO courseRequestDTO){
		CourseResponseDTO courseResponseDTO = courseService.scrapCourse(courseRequestDTO);
        return new ResponseEntity<>(courseResponseDTO, HttpStatus.CREATED);
	}
		
	//- 강좌 수강 취소
	@DeleteMapping("/{regist_course_id}")
	public ResponseEntity<?> deleteRegistCourse(@PathVariable("regist_course_id") Long id){
		courseService.deleteRegistCourse(id);
        return ResponseEntity.noContent().build();
	}
	
	// - 강좌 조회
	@GetMapping("/detail/{course_id}")
	public ResponseEntity<?> getCourseDetail(@PathVariable("regist_course_id") Long id){
		CourseResponseDTO courseResponseDTO = courseService.getCourseDetail(id);
		return new ResponseEntity<>(courseResponseDTO, HttpStatus.OK);
	}
	
	// - 최근 강의 조회
	@GetMapping("/recent-lecture/{course_id}")
	public ResponseEntity<?> getRecentLecture(){
		
	}
	
	// - 강좌 소개 조회
	@GetMapping("/info/{course_id}")
	public ResponseEntity<?> getCourseInfo(){
		
	}
	
	// - 공지사항 조회
	@GetMapping("/notice/{course_id}")
	public ResponseEntity<?> getCourseNotices(){
		
	}
	
}
