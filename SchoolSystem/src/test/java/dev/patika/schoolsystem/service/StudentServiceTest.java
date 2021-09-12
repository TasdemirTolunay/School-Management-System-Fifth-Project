package dev.patika.schoolsystem.service;

import dev.patika.schoolsystem.dto.AddressDTO;
import dev.patika.schoolsystem.dto.CourseDTO;
import dev.patika.schoolsystem.dto.StudentDTO;
import dev.patika.schoolsystem.dto.StudentWithCoursesDTO;
import dev.patika.schoolsystem.entity.Student;
import dev.patika.schoolsystem.exceptions.StudentAgeNotValidException;
import dev.patika.schoolsystem.mapper.AddressMapper;
import dev.patika.schoolsystem.mapper.CourseMapper;
import dev.patika.schoolsystem.mapper.StudentMapper;
import dev.patika.schoolsystem.mapper.StudentWithCoursesMapper;
import dev.patika.schoolsystem.repository.StudentRepository;
import dev.patika.schoolsystem.util.StudentAgeValid;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    @InjectMocks
    StudentService studentService;

    @Mock
    StudentAgeValid mockStudentAgeValid;

    @Mock
    StudentWithCoursesMapper mockStudentWithCoursesMapper;

    @Mock
    StudentMapper mockStudentMapper;

    @Mock
    StudentRepository mockStudentRepository;

    @Mock
    CourseMapper courseMapper;

    @Mock
    AddressMapper addressMapper;

    @Test
    void findAll(){

        Iterable<Student> studentIterable = new ArrayList<>();
        List<StudentWithCoursesDTO> studentWithCoursesDTOS = new ArrayList<>();
        when(mockStudentRepository.findAll()).thenReturn(studentIterable);
        when(mockStudentWithCoursesMapper.mapStudentListToStudentWithCoursesDTOList(any())).thenReturn(studentWithCoursesDTOS);

        List<StudentWithCoursesDTO> actual = this.studentService.findAllStudents();

        assertEquals(studentWithCoursesDTOS,actual);

    }

    @Test
    void findByStudentId(){

        Student student = new Student();
        StudentWithCoursesDTO studentWithCoursesDTO = new StudentWithCoursesDTO();
        when(mockStudentRepository.findById(anyLong())).thenReturn(Optional.of(student));
        when(mockStudentWithCoursesMapper.mapStudentToStudentWithCoursesDTO(any())).thenReturn(studentWithCoursesDTO);

        StudentWithCoursesDTO actual = this.studentService.findByStudentId(anyLong());

        assertEquals(studentWithCoursesDTO,actual);

    }

    @Test
    void saveStudent(){

        Student student = new Student();
        StudentWithCoursesDTO studentWithCoursesDTO = new StudentWithCoursesDTO();
        when(mockStudentMapper.mapStudentDTOToStudent(any())).thenReturn(student);
        when(mockStudentAgeValid.studentAgeValidator(anyInt())).thenReturn(Boolean.FALSE);
        when(mockStudentRepository.save(any())).thenReturn(student);
        when(mockStudentWithCoursesMapper.mapStudentToStudentWithCoursesDTO(any())).thenReturn(studentWithCoursesDTO);

        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setStudentBirthDate(LocalDate.of(1996,02,12));
        StudentWithCoursesDTO actual = this.studentService.saveStudent(studentDTO);

        assertEquals(studentWithCoursesDTO,actual);

    }

    @Test
    void saveStudentAgeException(){

        Student student = new Student();
        when(mockStudentMapper.mapStudentDTOToStudent(any())).thenReturn(student);
        when(mockStudentAgeValid.studentAgeValidator(anyInt())).thenReturn(Boolean.TRUE);

        StudentDTO dto = new StudentDTO();
        dto.setStudentBirthDate(LocalDate.of(1996,02,12));
        Executable executable = () -> this.studentService.saveStudent(dto);

        assertThrows(StudentAgeNotValidException.class,executable);

    }

    @Test
    void updateStudent(){

        Student student = new Student();
        StudentWithCoursesDTO studentWithCoursesDTO = new StudentWithCoursesDTO();
        when(mockStudentRepository.findById(anyLong())).thenReturn(Optional.of(student));
        when(mockStudentAgeValid.studentAgeValidator(anyInt())).thenReturn(Boolean.FALSE);
        when(mockStudentRepository.save(any())).thenReturn(student);
        when(mockStudentWithCoursesMapper.mapStudentToStudentWithCoursesDTO(any())).thenReturn(studentWithCoursesDTO);

        StudentDTO dto = new StudentDTO();
        dto.setStudentBirthDate(LocalDate.of(1996,02,12));
        StudentWithCoursesDTO actual = this.studentService.updateStudent(dto,anyLong());

        assertEquals(studentWithCoursesDTO,actual);

    }

    @Test
    void updateStudentAgeException(){

        Student student = new Student();
        when(mockStudentRepository.findById(anyLong())).thenReturn(Optional.of(student));
        when(mockStudentAgeValid.studentAgeValidator(anyInt())).thenReturn(Boolean.TRUE);

        StudentDTO dto = new StudentDTO();
        dto.setStudentBirthDate(LocalDate.of(1996,02,12));
        Executable executable = () -> this.studentService.updateStudent(dto,anyLong());

        assertThrows(StudentAgeNotValidException.class,executable);

    }

    @Test
    void findStudentByName(){

        List<Student> studentList = new ArrayList<>();
        List<StudentWithCoursesDTO> studentWithCoursesDTOList = new ArrayList<>();
        when(mockStudentRepository.findStudentByStudentName(anyString())).thenReturn(studentList);
        when(mockStudentWithCoursesMapper.mapStudentListToStudentWithCoursesDTOList(any())).thenReturn(studentWithCoursesDTOList);

        List<StudentWithCoursesDTO> actual = this.studentService.findStudentByName(anyString());

        assertEquals(studentWithCoursesDTOList,actual);

    }

    @Test
    void findCoursesOfStudent(){

        Student student = new Student();
        List<CourseDTO> courseDTOList = new ArrayList<>();
        when(mockStudentRepository.findById(anyLong())).thenReturn(Optional.of(student));
        when(courseMapper.mapCourseListToCourseDTOList(any())).thenReturn(courseDTOList);

        List<CourseDTO> actual = this.studentService.findCoursesOfStudent(anyLong());

        assertEquals(courseDTOList,actual);

    }

    @Test
    void findAddressOfStudent(){

        Student student = new Student();
        AddressDTO addressDTO = new AddressDTO();
        when(mockStudentRepository.findById(anyLong())).thenReturn(Optional.of(student));
        when(addressMapper.mapAddressToAddressDTO(any())).thenReturn(addressDTO);

        AddressDTO actual = this.studentService.findAddressOfStudent(anyLong());

        assertEquals(addressDTO,actual);

    }


}
