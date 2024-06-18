package com.gongkademy.domain.course.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gongkademy.domain.course.dto.request.CourseRequestDTO;
import com.gongkademy.domain.course.dto.response.CourseResponseDTO;
import com.gongkademy.domain.course.entity.Course;
import com.gongkademy.domain.course.entity.Lecture;
import com.gongkademy.domain.course.entity.Notice;
import com.gongkademy.domain.course.entity.RegistCourse;
import com.gongkademy.domain.course.entity.Scrap;
import com.gongkademy.domain.course.repository.CourseRepository;
import com.gongkademy.domain.course.repository.NoticeRepository;
import com.gongkademy.domain.course.repository.RegistCourseRepository;
import com.gongkademy.domain.course.repository.ScrapRepository;
import com.gongkademy.domain.member.entity.Member;
import com.gongkademy.domain.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
	
	private final RegistCourseRepository registCourseRepository;
	private final ScrapRepository scrapRepository;
	private final MemberRepository memberRepository;
	private final CourseRepository courseRepository;
	private final NoticeRepository noticeRepository;

	@Override
	public List<CourseResponseDTO> getAllCourses() {
		List<CourseResponseDTO> courseResponseDTOs = new ArrayList<>();
		
		List<Course> courses = courseRepository.findAll();
		for(Course course : courses) {
			courseResponseDTOs.add(this.convertToDTO(course));
		}
		return courseResponseDTOs;
	}

	@Override
	public List<Lecture> getCourseContents() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CourseResponseDTO registCourse(CourseRequestDTO courseRequestDTO) {
		RegistCourse registCourse = this.converToEntityRegistCourse(courseRequestDTO);
		
		registCourseRepository.save(registCourse);
		
		// 수강생 수 update
		Optional<Course> course = courseRepository.findById(courseRequestDTO.getCourseId());
		course.get().updateLectureCount();
		
		CourseResponseDTO courseResponseDTO = this.convertToDTO(course.get());
		return courseResponseDTO;
	}

	@Override
	public CourseResponseDTO scrapCourse(CourseRequestDTO courseRequestDTO) {
		Scrap scrap = this.convertToEntityScrap(courseRequestDTO);
		scrapRepository.save(scrap);
		
		Optional<Course> course = courseRepository.findById(courseRequestDTO.getCourseId());
		CourseResponseDTO courseResponseDTO = this.convertToDTO(course.get());
		return courseResponseDTO;
	}

	@Override
	public void deleteRegistCourse(Long registCourseId) {
		RegistCourse registCourse = registCourseRepository.findById(registCourseId)
				.orElseThrow(() -> new IllegalArgumentException("수강 강좌 찾을 수 없음"));
		
		registCourseRepository.delete(registCourse);
	}

	@Override
	public CourseResponseDTO getCourseDetail(Long courseId) {
		Course course = courseRepository.findById(courseId)
				.orElseThrow(() -> new IllegalArgumentException("강좌 찾을 수 없음"));
		
		CourseResponseDTO courseResponseDTO = this.convertToDTO(course);
		return courseResponseDTO;
	}
	
	private CourseResponseDTO convertToDTO(Course course) {
		CourseResponseDTO courseResponseDTO = new CourseResponseDTO();
		courseResponseDTO.setCourseId(course.getId());
		courseResponseDTO.setTotalCourseTime(course.getTotalCourseTime());
		courseResponseDTO.setAvgRating(course.getAvgRating());
		courseResponseDTO.setReviewCount(course.getReviewCount());
		courseResponseDTO.setCourseId(course.getId());
		courseResponseDTO.setCourseId(course.getId());

		return courseResponseDTO;
	}

	private RegistCourse converToEntityRegistCourse(CourseRequestDTO courseRequestDTO) {
		Course course = courseRepository.findById(courseRequestDTO.getCourseId())
				.orElseThrow(() -> new IllegalArgumentException("강좌 찾을 수 없음"));
		
		Member member = memberRepository.findById(courseRequestDTO.getMemberId())
				.orElseThrow(() -> new IllegalArgumentException("회원 찾을 수 없음"));
		
		RegistCourse registCourse = new RegistCourse();
		registCourse.setCourse(course);
		registCourse.setMember(member);
		return registCourse;
	}
	
	private Scrap convertToEntityScrap(CourseRequestDTO courseRequestDTO) {
		Course course = courseRepository.findById(courseRequestDTO.getCourseId())
				.orElseThrow(() -> new IllegalArgumentException("강좌 찾을 수 없음"));
		
		Member member = memberRepository.findById(courseRequestDTO.getMemberId())
				.orElseThrow(() -> new IllegalArgumentException("회원 찾을 수 없음"));
		
		Scrap scrap = new Scrap();
		scrap.setCourse(course);
		scrap.setMember(member);
		return scrap;
	}

	@Override
	public List<Notice> getCourseNotices(Long courseId) {
		 List<Notice> notices = noticeRepository.findByCourseId(courseId);
		 return notices;
	}
	
	
}
