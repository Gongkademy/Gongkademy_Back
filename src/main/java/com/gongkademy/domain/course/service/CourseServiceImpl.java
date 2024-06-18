package com.gongkademy.domain.course.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gongkademy.domain.course.dto.request.CourseRequestDTO;
import com.gongkademy.domain.course.dto.response.CourseContentsResponseDTO;
import com.gongkademy.domain.course.dto.response.CourseResponseDTO;
import com.gongkademy.domain.course.entity.Course;
import com.gongkademy.domain.course.entity.Lecture;
import com.gongkademy.domain.course.entity.Notice;
import com.gongkademy.domain.course.entity.RegistCourse;
import com.gongkademy.domain.course.entity.RegistLecture;
import com.gongkademy.domain.course.entity.Scrap;
import com.gongkademy.domain.course.repository.CourseRepository;
import com.gongkademy.domain.course.repository.LectureRepository;
import com.gongkademy.domain.course.repository.NoticeRepository;
import com.gongkademy.domain.course.repository.RegistCourseRepository;
import com.gongkademy.domain.course.repository.RegistLectureRepository;
import com.gongkademy.domain.course.repository.ScrapRepository;
import com.gongkademy.domain.member.entity.Member;
import com.gongkademy.domain.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
	
	private final RegistCourseRepository registCourseRepository;
	private final RegistLectureRepository registLectureRepository;
	private final ScrapRepository scrapRepository;
	private final MemberRepository memberRepository;
	private final CourseRepository courseRepository;
	private final NoticeRepository noticeRepository;
	private final LectureRepository lectureRepository;

	@Override
	public List<CourseResponseDTO> getAllCourses(Long memberId) {
		List<CourseResponseDTO> courseResponseDTOs = new ArrayList<>();
		
		List<Course> courses = courseRepository.findAll();
		for(Course course : courses) {
            CourseResponseDTO dto = this.convertToDTO(course);

            Boolean isRegistered = registCourseRepository.existsByMemberIdAndCourseId(memberId, course.getId());
			dto.setIsRegistered(isRegistered);
            courseResponseDTOs.add(dto);
		}
		return courseResponseDTOs;
	}

	@Override
	public List<CourseContentsResponseDTO> getCourseContents(CourseRequestDTO courseRequestDTO) {
		Long memberId = courseRequestDTO.getMemberId();
		Long courseId = courseRequestDTO.getCourseId();
		
		List<Lecture> lectures = lectureRepository.findByCourseId(courseId);
		List<CourseContentsResponseDTO> courseContentsDTOs = new ArrayList<>();
		
		// TODO lecture complete인지 확인
		for(Lecture lecture : lectures) {
			CourseContentsResponseDTO dto = this.convertToDToContents(lecture);
		
			dto.setMemberId(memberId);
			
			// 수강하고 있는 강좌면 완강여부 확인
            Boolean isRegistered = registCourseRepository.existsByMemberIdAndCourseId(memberId, courseId);
			if(isRegistered) {
				Boolean isCompleted = registLectureRepository.existsByMemberIdAndLectureIdAndComplete(memberId, lecture.getId(), true);
				dto.setIsCompleted(isCompleted);
			}
			// 아니면 다 false
			else dto.setIsCompleted(false);
			
			courseContentsDTOs.add(dto);
		}
		
		return courseContentsDTOs;
	}


	@Override
	public CourseResponseDTO registCourse(CourseRequestDTO courseRequestDTO) {
		RegistCourse registCourse = this.converToEntityRegistCourse(courseRequestDTO);
		
		registCourseRepository.save(registCourse);
		
		// 수강생 수 update
		Optional<Course> course = courseRepository.findById(courseRequestDTO.getCourseId());
		course.get().updateLectureCount();
		
		addRegistLectures(registCourse);
		
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
	
	@Override
	public List<Notice> getCourseNotices(Long courseId) {
		// TODO : 페이지네이션
		List<Notice> notices = noticeRepository.findByCourseId(courseId);
		return notices;
	}
	
	// 수강 강좌에 대한 수강 강의 생성
	private void addRegistLectures(RegistCourse registCourse) {
        List<Lecture> lectures = lectureRepository.findByCourseId(registCourse.getCourse().getId());
        
        for (Lecture lecture : lectures) {
            RegistLecture registLecture = new RegistLecture();
            registLecture.setLecture(lecture);
            registLecture.setRegistCourse(registCourse);
            registLecture.setMember(registCourse.getMember());
            
            registCourse.addRegistLecture(registLecture);
        }
        registLectureRepository.saveAll(registCourse.getRegistLectures());
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
	
	private CourseContentsResponseDTO convertToDToContents(Lecture lecture) {
		CourseContentsResponseDTO courseContentsResponseDTO = new CourseContentsResponseDTO();
		courseContentsResponseDTO.setLectureId(lecture.getId());
		courseContentsResponseDTO.setLectureOrder(lecture.getLectureOrder());
		courseContentsResponseDTO.setTime(lecture.getTime());
		courseContentsResponseDTO.setTitle(lecture.getTitle());
		return courseContentsResponseDTO;
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
	
	
}
