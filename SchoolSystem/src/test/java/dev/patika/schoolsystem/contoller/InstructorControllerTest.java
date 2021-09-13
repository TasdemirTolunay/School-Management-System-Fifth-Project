package dev.patika.schoolsystem.contoller;

import dev.patika.schoolsystem.controller.InstructorController;
import dev.patika.schoolsystem.dto.*;
import dev.patika.schoolsystem.service.InstructorService;
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
public class InstructorControllerTest {

    @Mock
    InstructorService instructorService;

    @InjectMocks
    InstructorController instructorController;

    @Test
    void getAllList(){

        List<InstructorResponseDTO> responseDTOList = new ArrayList<>();
        when(instructorService.findAllInstructor()).thenReturn(responseDTOList);

        ResponseEntity<List<InstructorResponseDTO>> actual = this.instructorController.findAllList();

        assertAll(
                () -> assertEquals(responseDTOList,actual.getBody()),
                () -> assertEquals(HttpStatus.OK,actual.getStatusCode())
        );
    }

    @Test
    void getnstructorById(){

        InstructorResponseDTO instructorResponseDTO = new InstructorResponseDTO();
        when(instructorService.findByInstructorId(anyLong())).thenReturn(instructorResponseDTO);

        ResponseEntity<InstructorResponseDTO> actual = this.instructorController.findInstructorById(anyLong());

        assertAll(
                () -> assertEquals(instructorResponseDTO,actual.getBody()),
                () -> assertEquals(HttpStatus.OK,actual.getStatusCode())
        );

    }

    @Test
    void getInstructorByInstructorName(){

        List<InstructorResponseDTO> responseDTOList = new ArrayList<>();
        when(instructorService.findInstructorByName(anyString())).thenReturn(responseDTOList);

        ResponseEntity<List<InstructorResponseDTO>> actual = this.instructorController.findInstructorByInstructorName(anyString());

        assertAll(
                () -> assertEquals(responseDTOList,actual.getBody()),
                () -> assertEquals(HttpStatus.OK,actual.getStatusCode())
        );
    }

    @Test
    void getCoursesOfInstructor(){

        List<CourseDTO> courseDTOList = new ArrayList<>();
        when(instructorService.getCoursesOfInstructor(anyLong())).thenReturn(courseDTOList);

        ResponseEntity<List<CourseDTO>> actual = this.instructorController.findCoursesOfInstructor(anyLong());

        assertAll(
                () -> assertEquals(courseDTOList,actual.getBody()),
                () -> assertEquals(HttpStatus.OK,actual.getStatusCode())
        );
    }

    @Test
    void getAddressOfInstructor(){

        AddressDTO addressDTO = new AddressDTO();
        when(instructorService.getAddressOfInstructor(anyLong())).thenReturn(addressDTO);

        ResponseEntity<AddressDTO> actual = this.instructorController.findAddressOfInstructor(anyLong());

        assertAll(
                () -> assertEquals(addressDTO,actual.getBody()),
                () -> assertEquals(HttpStatus.OK,actual.getStatusCode())
        );
    }

    @Test
    void savePermanentInstructor(){

        InstructorResponseDTO instructorResponseDTO = new InstructorResponseDTO();
        when(instructorService.savePermanentInstructor(any())).thenReturn(instructorResponseDTO);

        PermanentInstructorDTO dto = new PermanentInstructorDTO();
        ResponseEntity<InstructorResponseDTO> actual = this.instructorController.savePermanentInstructor(dto);

        assertAll(
                () -> assertEquals(instructorResponseDTO,actual.getBody()),
                () -> assertEquals(HttpStatus.OK,actual.getStatusCode())
        );
    }

    @Test
    void saveVisitingResearcher(){

        InstructorResponseDTO instructorResponseDTO = new InstructorResponseDTO();
        when(instructorService.saveVisitingResearcherInstructor(any())).thenReturn(instructorResponseDTO);

        VisitingResearcherDTO dto = new VisitingResearcherDTO();
        ResponseEntity<InstructorResponseDTO> actual = this.instructorController.saveVisitingInstructor(dto);

        assertAll(
                () -> assertEquals(instructorResponseDTO,actual.getBody()),
                () -> assertEquals(HttpStatus.OK,actual.getStatusCode())
        );
    }

    @Test
    void updateInstructor(){

        InstructorResponseDTO instructorResponseDTO = new InstructorResponseDTO();
        when(instructorService.updateInstructor(any(),anyLong())).thenReturn(instructorResponseDTO);

        InstructorDTO dto = new InstructorDTO();
        ResponseEntity<InstructorResponseDTO> actual = this.instructorController.updateInstructor(dto,1);

        assertAll(
                () -> assertEquals(instructorResponseDTO,actual.getBody()),
                () -> assertEquals(HttpStatus.OK,actual.getStatusCode())
        );
    }

    @Test
    void deleteInstructor(){

        when(instructorService.deleteInstructorById(anyLong())).thenReturn(anyString());

        ResponseEntity<String> actual = this.instructorController.deleteInstructorWithId(1);

        assertEquals(HttpStatus.OK,actual.getStatusCode());
    }
}
