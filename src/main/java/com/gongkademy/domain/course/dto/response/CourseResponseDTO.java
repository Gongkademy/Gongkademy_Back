package com.gongkademy.domain.course.dto.response;

public class CourseResponseDTO {
	
	private Long courseId;
	
	private Long totalCourseTime;
	private String title;
	private double avgRating;
	private int reviewCount;
	private int registCount;
	private int lectureCount;
	private String content;
	
	// 해당 강좌 수강&저장 여부?(DTO 만들 떄 판단해서)
//	private Boolean isRegistered;
//	private Boolean isSaved;
}
