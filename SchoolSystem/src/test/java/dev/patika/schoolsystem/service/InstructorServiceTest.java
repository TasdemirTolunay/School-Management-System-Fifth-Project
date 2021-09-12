package dev.patika.schoolsystem.service;

import dev.patika.schoolsystem.dto.*;
import dev.patika.schoolsystem.entity.Instructor;
import dev.patika.schoolsystem.entity.PermanentInstructor;
import dev.patika.schoolsystem.entity.VisitingResearcher;
import dev.patika.schoolsystem.exceptions.InstructorIsAlreadyExistException;
import dev.patika.schoolsystem.mapper.*;
import dev.patika.schoolsystem.repository.InstructorRepository;
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
public class InstructorServiceTest {

    @InjectMocks
    InstructorService instructorService;

    @Mock
    InstructorResponseMapper mockInstructorResponseMapper;

    @Mock
    InstructorRepository mockInstructorRepository;

    @Mock
    PermanentInstructorMapper mockPermanentInstructorMapper;

    @Mock
    VisitingResearcherMapper mockVisitingResearcherMapper;

    @Mock
    CourseMapper mockCourseMapper;

    @Mock
    AddressMapper mockAddressMapper;

    @Test
    void findAll(){

        Iterable<Instructor> instructorIterable = new ArrayList<>();
        List<InstructorResponseDTO> responseDTOList = new ArrayList<>();
        when(mockInstructorRepository.findAll()).thenReturn(instructorIterable);
        when(mockInstructorResponseMapper.mapInstructorListToInstructorResponseDTOList(any())).thenReturn(responseDTOList);

        List<InstructorResponseDTO> actual = this.instructorService.findAllInstructor();

        assertEquals(responseDTOList,actual);

    }

    @Test
    void findByInstructorId(){

        Instructor instructor = new Instructor();
        InstructorResponseDTO instructorResponseDTO = new InstructorResponseDTO();
        when(mockInstructorRepository.findById(anyLong())).thenReturn(Optional.of(instructor));
        when(mockInstructorResponseMapper.mapInstructorToInstructorResponseDTO(any())).thenReturn(instructorResponseDTO);

        InstructorResponseDTO actual = this.instructorService.findByInstructorId(anyLong());

        assertEquals(instructorResponseDTO,actual);

    }

    @Test
    void savePermanentInstructor(){

        PermanentInstructor permanentInstructor = new PermanentInstructor();
        InstructorResponseDTO responseDTO = new InstructorResponseDTO();
        when(mockPermanentInstructorMapper.mapPermanentInstructorDTOToPermanentInstructor(any())).thenReturn(permanentInstructor);
        when(mockInstructorRepository.selectExistsPhoneNumber(permanentInstructor.getInstructorPhoneNumber())).thenReturn(Boolean.FALSE);
        when(mockInstructorRepository.save(any())).thenReturn(permanentInstructor);
        when(mockInstructorResponseMapper.mapInstructorToInstructorResponseDTO(any())).thenReturn(responseDTO);

        PermanentInstructorDTO dto = new PermanentInstructorDTO();
        InstructorResponseDTO actual = this.instructorService.savePermanentInstructor(dto);

        assertEquals(responseDTO,actual);

    }

    @Test
    void savePermanentInstructorExistsException(){

        PermanentInstructor permanentInstructor = new PermanentInstructor();
        when(mockPermanentInstructorMapper.mapPermanentInstructorDTOToPermanentInstructor(any())).thenReturn(permanentInstructor);
        when(mockInstructorRepository.selectExistsPhoneNumber(permanentInstructor.getInstructorPhoneNumber())).thenReturn(Boolean.TRUE);

        PermanentInstructorDTO dto = new PermanentInstructorDTO();
        Executable executable = () -> this.instructorService.savePermanentInstructor(dto);

        assertThrows(InstructorIsAlreadyExistException.class,executable);

    }

    @Test
    void saveVisitingResearcher(){

        VisitingResearcher visitingResearcher = new VisitingResearcher();
        InstructorResponseDTO instructorResponseDTO = new InstructorResponseDTO();
        when(mockVisitingResearcherMapper.mapVisitingResearcherDTOToVisitingResearcher(any())).thenReturn(visitingResearcher);
        when(mockInstructorRepository.selectExistsPhoneNumber(visitingResearcher.getInstructorPhoneNumber())).thenReturn(Boolean.FALSE);
        when(mockInstructorRepository.save(any())).thenReturn(visitingResearcher);
        when(mockInstructorResponseMapper.mapInstructorToInstructorResponseDTO(any())).thenReturn(instructorResponseDTO);

        VisitingResearcherDTO dto = new VisitingResearcherDTO();
        InstructorResponseDTO actual = this.instructorService.saveVisitingResearcherInstructor(dto);

        assertEquals(instructorResponseDTO,actual);

    }

    @Test
    void saveVisitingResearcherExistsException(){

        VisitingResearcher visitingResearcher = new VisitingResearcher();
        when(mockVisitingResearcherMapper.mapVisitingResearcherDTOToVisitingResearcher(any())).thenReturn(visitingResearcher);
        when(mockInstructorRepository.selectExistsPhoneNumber(visitingResearcher.getInstructorPhoneNumber())).thenReturn(Boolean.TRUE);

        VisitingResearcherDTO dto = new VisitingResearcherDTO();
        Executable executable = () -> this.instructorService.saveVisitingResearcherInstructor(dto);

        assertThrows(InstructorIsAlreadyExistException.class,executable);

    }

    @Test
    void updateInstructor(){

        Instructor instructor = new Instructor();
        InstructorResponseDTO instructorResponseDTO = new InstructorResponseDTO();
        when(mockInstructorRepository.findById(anyLong())).thenReturn(Optional.of(instructor));
        when(mockInstructorRepository.selectExistsPhoneNumber(instructor.getInstructorPhoneNumber())).thenReturn(Boolean.FALSE);
        when(mockInstructorRepository.save(any())).thenReturn(instructor);
        when(mockInstructorResponseMapper.mapInstructorToInstructorResponseDTO(any())).thenReturn(instructorResponseDTO);

        InstructorDTO instructorDTO = new InstructorDTO();
        InstructorResponseDTO actual = this.instructorService.updateInstructor(instructorDTO,anyLong());

        assertEquals(instructorResponseDTO,actual);

    }

    @Test
    void updateInstructorExistsException(){

        Instructor instructor = new Instructor();
        when(mockInstructorRepository.findById(anyLong())).thenReturn(Optional.of(instructor));
        when(mockInstructorRepository.selectExistsPhoneNumber(instructor.getInstructorPhoneNumber())).thenReturn(Boolean.TRUE);

        InstructorDTO instructorDTO = new InstructorDTO();
        Executable executable = () -> this.instructorService.updateInstructor(instructorDTO,anyLong());

        assertThrows(InstructorIsAlreadyExistException.class,executable);

    }

    @Test
    void findInstructorByName(){

        List<Instructor> instructorList = new ArrayList<>();
        List<InstructorResponseDTO> instructorResponseDTOList = new ArrayList<>();
        when(mockInstructorRepository.findByInstructorName(anyString())).thenReturn(instructorList);
        when(mockInstructorResponseMapper.mapInstructorListToInstructorResponseDTOList(any())).thenReturn(instructorResponseDTOList);

        List<InstructorResponseDTO> actual = this.instructorService.findInstructorByName(anyString());

        assertEquals(instructorResponseDTOList,actual);

    }

    @Test
    void getCoursesOfInstructor(){

        Instructor instructor = new Instructor();
        List<CourseDTO> courseDTOList = new ArrayList<>();
        when(mockInstructorRepository.findById(anyLong())).thenReturn(Optional.of(instructor));
        when(mockCourseMapper.mapCourseListToCourseDTOList(any())).thenReturn(courseDTOList);

        List<CourseDTO> actual = this.instructorService.getCoursesOfInstructor(anyLong());

        assertEquals(courseDTOList,actual);

    }

    @Test
    void getAddressOfInstructor(){

        Instructor instructor = new Instructor();
        AddressDTO addressDTO = new AddressDTO();
        when(mockInstructorRepository.findById(anyLong())).thenReturn(Optional.of(instructor));
        when(mockAddressMapper.mapAddressToAddressDTO(any())).thenReturn(addressDTO);

        AddressDTO actual = this.instructorService.getAddressOfInstructor(anyLong());

        assertEquals(addressDTO,actual);

    }

}
