package dev.patika.schoolsystem.service;

import dev.patika.schoolsystem.dto.CourseDTO;
import dev.patika.schoolsystem.dto.CourseWithStudentsDTO;
import dev.patika.schoolsystem.entity.Course;
import dev.patika.schoolsystem.mapper.CourseMapper;
import dev.patika.schoolsystem.mapper.CourseWithStudentsMapper;
import dev.patika.schoolsystem.repository.CourseRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
public class CourseServiceTest {

    @InjectMocks
    CourseService courseService;

    @Mock
    CourseRepository mockCourseRepository;

    @Mock
    CourseWithStudentsMapper mockCourseWithStudentsMapper;

    @Mock
    CourseMapper mockCourseMapper;

    @Test
    void findAll(){

        Iterable<Course> courseIterable = new ArrayList<>();
        List<CourseDTO> courseDTOS = new ArrayList<>();
        when(mockCourseRepository.findAll()).thenReturn(courseIterable);
        when(mockCourseMapper.mapCourseListToCourseDTOList(any())).thenReturn(courseDTOS);

        List<CourseDTO> actual = this.courseService.findAllCourses();

        assertEquals(courseDTOS,actual);

    }

    @Test
    void findByCourseId(){
        Course course = new Course();
        CourseDTO courseDTO = new CourseDTO();
        when(mockCourseRepository.findById(anyLong())).thenReturn(Optional.of(course));
        when(mockCourseMapper.mapCourseToCourseDTO(any())).thenReturn(courseDTO);

        CourseDTO actual = this.courseService.findByCourseId(anyLong());

        assertEquals(courseDTO,actual);

    }

    @Test
    void saveCourse(){

        Course course = new Course();
        CourseDTO courseDTO = new CourseDTO();
        when(mockCourseWithStudentsMapper.mapCourseWithStudentsDTOToCourse(any())).thenReturn(course);
        when(mockCourseRepository.selectExistsCourseCode(course.getCourseCode())).thenReturn(Boolean.FALSE);
        when(mockCourseRepository.save(any())).thenReturn(course);
        when(mockCourseMapper.mapCourseToCourseDTO(any())).thenReturn(courseDTO);

        CourseWithStudentsDTO dto = new CourseWithStudentsDTO();
        CourseDTO actual = this.courseService.saveCourse(dto);

        assertEquals(courseDTO,actual);

    }
}
