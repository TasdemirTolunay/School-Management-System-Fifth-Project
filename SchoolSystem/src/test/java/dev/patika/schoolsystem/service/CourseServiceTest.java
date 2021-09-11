package dev.patika.schoolsystem.service;

import dev.patika.schoolsystem.dto.CourseDTO;
import dev.patika.schoolsystem.dto.CourseWithStudentsDTO;
import dev.patika.schoolsystem.dto.InstructorResponseDTO;
import dev.patika.schoolsystem.dto.StudentWithCoursesDTO;
import dev.patika.schoolsystem.entity.Course;
import dev.patika.schoolsystem.exceptions.CourseIsAlreadyExistException;
import dev.patika.schoolsystem.exceptions.StudentNumberForOneCourseExceededException;
import dev.patika.schoolsystem.mapper.CourseMapper;
import dev.patika.schoolsystem.mapper.CourseWithStudentsMapper;
import dev.patika.schoolsystem.mapper.InstructorResponseMapper;
import dev.patika.schoolsystem.mapper.StudentWithCoursesMapper;
import dev.patika.schoolsystem.repository.CourseRepository;
import dev.patika.schoolsystem.util.CourseStudentSizeValid;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
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
    StudentWithCoursesMapper mockStudentWithCoursesMapper;

    @Mock
    InstructorResponseMapper mockInstructorResponseMapper;

    @Mock
    CourseMapper mockCourseMapper;

    @Mock
    CourseStudentSizeValid mockStudentSizeValid;

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

    @Test
    void saveCourseExistsException(){

        Course course = new Course();
        when(mockCourseWithStudentsMapper.mapCourseWithStudentsDTOToCourse(any())).thenReturn(course);
        when(mockCourseRepository.selectExistsCourseCode(course.getCourseCode())).thenReturn(Boolean.TRUE);


        CourseWithStudentsDTO dto = new CourseWithStudentsDTO();
        Executable executable = () -> this.courseService.saveCourse(dto);

        assertThrows(CourseIsAlreadyExistException.class,executable);

    }

    @Test
    void saveCourseStudentSizeException(){

        Course course = new Course();
        when(mockCourseWithStudentsMapper.mapCourseWithStudentsDTOToCourse(any())).thenReturn(course);
        when(mockCourseRepository.selectExistsCourseCode(course.getCourseCode())).thenReturn(Boolean.FALSE);
        when(mockStudentSizeValid.studentSizeValidator(anyInt())).thenReturn(Boolean.TRUE);

        CourseWithStudentsDTO dto = new CourseWithStudentsDTO();
        Executable executable = () -> this.courseService.saveCourse(dto);

        assertThrows(StudentNumberForOneCourseExceededException.class,executable);

    }

    @Test
    void updateCourse(){

        Course course = new Course();
        CourseDTO courseDTO = new CourseDTO();
        when(mockCourseRepository.findById(anyLong())).thenReturn(Optional.of(course));
        when(mockCourseRepository.selectExistsCourseCode(course.getCourseCode())).thenReturn(Boolean.FALSE);
        when(mockCourseRepository.save(any())).thenReturn(course);
        when(mockCourseMapper.mapCourseToCourseDTO(any())).thenReturn(courseDTO);

        CourseWithStudentsDTO dto = new CourseWithStudentsDTO();
        CourseDTO actual = this.courseService.updateCourse(dto,anyLong());

        assertEquals(courseDTO,actual);

    }

    @Test
    void updateCourseException(){

        Course course = new Course();
        when(mockCourseRepository.findById(anyLong())).thenReturn(Optional.of(course));
        when(mockCourseRepository.selectExistsCourseCode(course.getCourseCode())).thenReturn(Boolean.TRUE);

        CourseWithStudentsDTO dto = new CourseWithStudentsDTO();
        Executable executable = () -> this.courseService.updateCourse(dto,anyLong());

        assertThrows(CourseIsAlreadyExistException.class,executable);

    }

    @Test
    void findCourseByName(){

        List<Course> courseList = new ArrayList<>();
        List<CourseDTO> courseDTOList = new ArrayList<>();
        when(mockCourseRepository.findByCourseName(anyString())).thenReturn(courseList);
        when(mockCourseMapper.mapCourseListToCourseDTOList(any())).thenReturn(courseDTOList);

        List<CourseDTO> actual = this.courseService.findCourseByName(anyString());

        assertEquals(courseDTOList,actual);

    }

    @Test
    void findStudentsOfCourse(){

        List<StudentWithCoursesDTO> studentWithCoursesDTOS = new ArrayList<>();
        Course course = new Course();
        when(mockCourseRepository.findById(anyLong())).thenReturn(Optional.of(course));
        when(mockStudentWithCoursesMapper.mapStudentListToStudentWithCoursesDTOList(any())).thenReturn(studentWithCoursesDTOS);

        List<StudentWithCoursesDTO> actual = this.courseService.findStudentsOfCourse(anyLong());

        assertEquals(studentWithCoursesDTOS,actual);

    }

    @Test
    void findInstructorOfCourse(){

        InstructorResponseDTO instructorResponseDTO = new InstructorResponseDTO();
        Course course = new Course();
        when(mockCourseRepository.findById(anyLong())).thenReturn(Optional.of(course));
        when(mockInstructorResponseMapper.mapInstructorToInstructorResponseDTO(any())).thenReturn(instructorResponseDTO);

        InstructorResponseDTO actual = this.courseService.findInstructorOfCourse(anyLong());

        assertEquals(instructorResponseDTO,actual);

    }

    @Test
    void findCourseById(){

        Course course = new Course();
        when(mockCourseRepository.findById(anyLong())).thenReturn(Optional.of(course));

        Course actual = this.courseService.findCourseById(anyLong());

        assertEquals(course,actual);

    }

}
