package dev.patika.schoolsystem.contoller;

import dev.patika.schoolsystem.controller.CourseController;
import dev.patika.schoolsystem.dto.CourseDTO;
import dev.patika.schoolsystem.dto.CourseWithStudentsDTO;
import dev.patika.schoolsystem.dto.InstructorResponseDTO;
import dev.patika.schoolsystem.dto.StudentWithCoursesDTO;
import dev.patika.schoolsystem.service.CourseService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class CourseControllerTest {

    @Mock
    CourseService courseService;

    @InjectMocks
    CourseController courseController;

    @Test
    void getAllList(){
        List<CourseDTO> courseDTOList = new ArrayList<>();
        when(courseService.findAllCourses()).thenReturn(courseDTOList);

        ResponseEntity<List<CourseDTO>> actual = this.courseController.findAllList();

        assertAll(
                () -> assertEquals(courseDTOList,actual.getBody()),
                () -> assertEquals(HttpStatus.OK,actual.getStatusCode())
        );

    }

    @Test
    void getCourseById(){

        CourseDTO courseDTO = new CourseDTO();
        when(courseService.findByCourseId(anyLong())).thenReturn(courseDTO);

        ResponseEntity<CourseDTO> actual = this.courseController.findCourseById(anyLong());

        assertAll(
                () -> assertEquals(courseDTO,actual.getBody()),
                () -> assertEquals(HttpStatus.OK,actual.getStatusCode())
        );

    }

    @Test
    void getCoursesByCourseName(){
        List<CourseDTO> courseDTOList = new ArrayList<>();
        when(courseService.findCourseByName(anyString())).thenReturn(courseDTOList);

        ResponseEntity<List<CourseDTO>> actual = this.courseController.findCourseByCourseName(anyString());

        assertAll(
                () -> assertEquals(courseDTOList,actual.getBody()),
                () -> assertEquals(HttpStatus.OK,actual.getStatusCode())
        );

    }

    @Test
    void getStudentsOfCourse(){
        List<StudentWithCoursesDTO> studentWithCoursesDTOS = new ArrayList<>();
        when(courseService.findStudentsOfCourse(anyLong())).thenReturn(studentWithCoursesDTOS);

        ResponseEntity<List<StudentWithCoursesDTO>> actual = this.courseController.getStudentsOfCourse(anyLong());

        assertAll(
                () -> assertEquals(studentWithCoursesDTOS,actual.getBody()),
                () -> assertEquals(HttpStatus.OK,actual.getStatusCode())
        );

    }

    @Test
    void getInstructorOfCourse(){

        InstructorResponseDTO instructorResponseDTO = new InstructorResponseDTO();
        when(courseService.findInstructorOfCourse(anyLong())).thenReturn(instructorResponseDTO);

        ResponseEntity<InstructorResponseDTO> actual = this.courseController.getInstructorOfCourse(anyLong());

        assertAll(
                () -> assertEquals(instructorResponseDTO,actual.getBody()),
                () -> assertEquals(HttpStatus.OK,actual.getStatusCode())
        );

    }

    @Test
    void saveCourse(){

        CourseDTO courseDTO = new CourseDTO();
        when(courseService.saveCourse(any())).thenReturn(courseDTO);

        CourseWithStudentsDTO dto = new CourseWithStudentsDTO();
        ResponseEntity<CourseDTO> actual = this.courseController.saveCourse(dto);

        assertAll(
                () -> assertEquals(courseDTO,actual.getBody()),
                () -> assertEquals(HttpStatus.OK,actual.getStatusCode())
        );

    }

    @Test
    void updateCourse(){

        CourseDTO courseDTO = new CourseDTO();
        when(courseService.updateCourse(any(),anyLong())).thenReturn(courseDTO);

        CourseWithStudentsDTO dto = new CourseWithStudentsDTO();
        ResponseEntity<CourseDTO> actual = this.courseController.updateCourse(dto,1);

        assertAll(
                () -> assertEquals(courseDTO,actual.getBody()),
                () -> assertEquals(HttpStatus.OK,actual.getStatusCode())
        );

    }

    @Test
    void deleteCourse(){

        when(courseService.deleteCourseById(anyLong())).thenReturn(anyString());

        ResponseEntity<String> actual = this.courseController.deleteCourseWithId(1);

        assertEquals(HttpStatus.OK,actual.getStatusCode());

    }
}
