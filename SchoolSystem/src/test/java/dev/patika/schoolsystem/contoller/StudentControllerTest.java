package dev.patika.schoolsystem.contoller;

import dev.patika.schoolsystem.controller.StudentController;
import dev.patika.schoolsystem.dto.AddressDTO;
import dev.patika.schoolsystem.dto.CourseDTO;
import dev.patika.schoolsystem.dto.StudentDTO;
import dev.patika.schoolsystem.dto.StudentWithCoursesDTO;
import dev.patika.schoolsystem.service.StudentService;
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
public class StudentControllerTest {

    @Mock
    StudentService studentService;

    @InjectMocks
    StudentController studentController;

    @Test
    void getAllList(){

        List<StudentWithCoursesDTO> studentWithCoursesDTOS = new ArrayList<>();
        when(studentService.findAllStudents()).thenReturn(studentWithCoursesDTOS);

        ResponseEntity<List<StudentWithCoursesDTO>> actual = this.studentController.findAllList();

        assertAll(
                () -> assertEquals(studentWithCoursesDTOS,actual.getBody()),
                () -> assertEquals(HttpStatus.OK,actual.getStatusCode())
        );
    }

    @Test
    void getStudentById(){

        StudentWithCoursesDTO studentWithCoursesDTO = new StudentWithCoursesDTO();
        when(studentService.findByStudentId(anyLong())).thenReturn(studentWithCoursesDTO);

        ResponseEntity<StudentWithCoursesDTO> actual = this.studentController.findStudentById(anyLong());

        assertAll(
                () -> assertEquals(studentWithCoursesDTO,actual.getBody()),
                () -> assertEquals(HttpStatus.OK,actual.getStatusCode())
        );
    }

    @Test
    void getStudentByStudentName(){

        List<StudentWithCoursesDTO> studentWithCoursesDTOList = new ArrayList<>();
        when(studentService.findStudentByName(anyString())).thenReturn(studentWithCoursesDTOList);

        ResponseEntity<List<StudentWithCoursesDTO>> actual = this.studentController.findStudentByStudentName(anyString());

        assertAll(
                () -> assertEquals(studentWithCoursesDTOList,actual.getBody()),
                () -> assertEquals(HttpStatus.OK,actual.getStatusCode())
        );
    }

    @Test
    void getCoursesOfStudent(){

        List<CourseDTO> courseDTOList = new ArrayList<>();
        when(studentService.findCoursesOfStudent(anyLong())).thenReturn(courseDTOList);

        ResponseEntity<List<CourseDTO>> actual = this.studentController.getCoursesOfStudents(anyLong());

        assertAll(
                () -> assertEquals(courseDTOList,actual.getBody()),
                () -> assertEquals(HttpStatus.OK,actual.getStatusCode())
        );
    }

    @Test
    void getAddressOfStudent(){

        AddressDTO addressDTO = new AddressDTO();
        when(studentService.findAddressOfStudent(anyLong())).thenReturn(addressDTO);

        ResponseEntity<AddressDTO> actual = this.studentController.getAddressOfStudent(anyLong());

        assertAll(
                () -> assertEquals(addressDTO,actual.getBody()),
                () -> assertEquals(HttpStatus.OK,actual.getStatusCode())
        );
    }

    @Test
    void saveStudent(){

        StudentWithCoursesDTO studentWithCoursesDTO = new StudentWithCoursesDTO();
        when(studentService.saveStudent(any())).thenReturn(studentWithCoursesDTO);

        StudentDTO studentDTO = new StudentDTO();
        ResponseEntity<StudentWithCoursesDTO> actual = this.studentController.saveStudent(studentDTO);

        assertAll(
                () -> assertEquals(studentWithCoursesDTO,actual.getBody()),
                () -> assertEquals(HttpStatus.OK,actual.getStatusCode())
        );
    }

    @Test
    void updateStuden(){

        StudentWithCoursesDTO studentWithCoursesDTO = new StudentWithCoursesDTO();
        when(studentService.updateStudent(any(),anyLong())).thenReturn(studentWithCoursesDTO);

        StudentDTO dto = new StudentDTO();
        ResponseEntity<StudentWithCoursesDTO> actual = this.studentController.updateStudent(dto,1);

        assertAll(
                () -> assertEquals(studentWithCoursesDTO,actual.getBody()),
                () -> assertEquals(HttpStatus.OK,actual.getStatusCode())
        );
    }

    @Test
    void deleteStudent(){

        when(studentService.deleteStudentById(1)).thenReturn(anyString());

        ResponseEntity<String> actual = this.studentController.deleteStudentWithId(1);

        assertEquals(HttpStatus.OK,actual.getStatusCode());
    }
}
