package com.gongkademy.domain.course.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.gongkademy.domain.course.dto.request.CourseRequestDTO;
import com.gongkademy.domain.course.dto.response.CourseResponseDTO;
import com.gongkademy.domain.course.entity.Course;
import com.gongkademy.domain.course.entity.RegistCourse;
import com.gongkademy.domain.course.repository.CourseRepository;
import com.gongkademy.domain.course.repository.LectureRepository;
import com.gongkademy.domain.member.entity.Member;
import com.gongkademy.domain.member.repository.MemberRepository;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class CourseServiceTest {


    @Mock
    private MemberRepository memberRepository;

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private LectureRepository lectureRepository;

    @InjectMocks
    private CourseServiceImpl courseService;

    private CourseRequestDTO requestDTO;
    private Member member;
    private Course course;

    @BeforeEach
    void setUp() {
        requestDTO = new CourseRequestDTO();
        requestDTO.setCourseId(1L);
        requestDTO.setMemberId(1L);

        member = new Member();
        member.setId(1L);

        course = new Course();
        course.setId(1L);
        course.setTotalCourseTime(100L);
    }

    @Test
    void  registCourse_Test() {
        // given
    	when(memberRepository.findById(anyLong())).thenReturn(Optional.of(member));
        when(courseRepository.findById(anyLong())).thenReturn(Optional.of(course));

        // when
        CourseResponseDTO responseDTO = courseService.registCourse(requestDTO, 1L);

        // then
        verify(memberRepository, times(1)).findById(1L);
        verify(courseRepository, times(2)).findById(1L);
        verify(lectureRepository, times(1)).findByCourseId(1L);
        
        assertNotNull(responseDTO);
        assertEquals(course.getId(), responseDTO.getCourseId());
    }
}

