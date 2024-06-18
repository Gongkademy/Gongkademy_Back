package com.gongkademy.domain.course.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gongkademy.domain.course.dto.request.CourseRequestDTO;
import com.gongkademy.domain.course.dto.response.CourseResponseDTO;
import com.gongkademy.domain.course.entity.Course;
import com.gongkademy.domain.course.entity.Lecture;
import com.gongkademy.domain.course.entity.RegistCourse;
import com.gongkademy.domain.course.entity.Scrap;
import com.gongkademy.domain.course.repository.CourseRepository;
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

	@Override
	public List<Course> getAllCourses() {
		// TODO Auto-generated method stub
		return null;
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
		
		// responseDTO 생성
		return null;
	}

	@Override
	public CourseResponseDTO scrapCourse(CourseRequestDTO courseRequestDTO) {
		Scrap scrap = this.convertToEntityScrap(courseRequestDTO);
		scrapRepository.save(scrap);
		
		// responseDTO 생성
		return null;
	}

	@Override
	public void deleteRegistCourse(Long registCourseId) {
		RegistCourse registCourse = registCourseRepository.findById(registCourseId)
				.orElseThrow(() -> new IllegalArgumentException("수강 강좌 찾을 수 없음"));
		
		registCourseRepository.delete(registCourse);
	}

	
	private RegistCourse converToEntityRegistCourse(CourseRequestDTO courseRequestDTO) {
		Course course = courseRepository.findById(courseRequestDTO.getCourseId())
				.orElseThrow(() -> new IllegalArgumentException("강좌 찾을 수 없음"));
		
		Member member = memberRepository.findById(courseRequestDTO.getMemberId())
				.orElseThrow(() -> new IllegalArgumentException("회원 찾을 수 없음"));
		
		// 수강 강좌 초기화
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
		
		// 강좌 저장 초기화
		Scrap scrap = new Scrap();
		scrap.setCourse(course);
		scrap.setMember(member);
		return scrap;
	}
	
	
}
