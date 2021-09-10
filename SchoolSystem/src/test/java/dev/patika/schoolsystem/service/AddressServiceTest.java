package dev.patika.schoolsystem.service;

import dev.patika.schoolsystem.dto.AddressDTO;
import dev.patika.schoolsystem.dto.InstructorResponseDTO;
import dev.patika.schoolsystem.dto.StudentWithCoursesDTO;
import dev.patika.schoolsystem.entity.Address;
import dev.patika.schoolsystem.mapper.AddressMapper;
import dev.patika.schoolsystem.mapper.InstructorResponseMapper;
import dev.patika.schoolsystem.mapper.StudentWithCoursesMapper;
import dev.patika.schoolsystem.repository.AddressRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class AddressServiceTest {

    @InjectMocks
    AddressService addressService;

    @Mock
    AddressMapper mockAddressMapper;

    @Mock
    AddressRepository mockAddressRepository;

    @Mock
    StudentWithCoursesMapper mockStudentWithCoursesMapper;

    @Mock
    InstructorResponseMapper mockInstructorResponseMapper;

    @Test
    void findAll(){

        Iterable<Address> addressIterable = new ArrayList<>();
        List<AddressDTO> addressDTOList = new ArrayList<>();
        when(mockAddressRepository.findAll()).thenReturn(addressIterable);
        when(mockAddressMapper.mapAddressListToAddressDTOList(any())).thenReturn(addressDTOList);

        List<AddressDTO> actual = this.addressService.findAllAddress();

        assertEquals(addressDTOList,actual);

    }

    @Test
    void findByAddressId(){

        Address address = new Address();
        AddressDTO addressDTO = new AddressDTO();
        when(mockAddressRepository.findById(anyLong())).thenReturn(Optional.of(address));
        when(mockAddressMapper.mapAddressToAddressDTO(any())).thenReturn(addressDTO);

        AddressDTO actual = this.addressService.findByAddressId(anyLong());

        assertEquals(addressDTO,actual);

    }

    @Test
    void saveAddress(){

        Address address = new Address();
        AddressDTO addressDTO = new AddressDTO();
        when(mockAddressMapper.mapAddressDTOToAddress(any())).thenReturn(address);
        when(mockAddressRepository.save(any())).thenReturn(address);
        when(mockAddressMapper.mapAddressToAddressDTO(any())).thenReturn(addressDTO);

        AddressDTO dto = new AddressDTO();

        AddressDTO actual = this.addressService.saveAddress(dto);

        assertEquals(addressDTO,actual);

    }

    @Test
    void updateAddress(){

        Address address = new Address();
        AddressDTO addressDTO = new AddressDTO();
        when(mockAddressRepository.findById(anyLong())).thenReturn(Optional.of(address));
        when(mockAddressRepository.save(any())).thenReturn(address);
        when(mockAddressMapper.mapAddressToAddressDTO(any())).thenReturn(addressDTO);

        AddressDTO dto = new AddressDTO();
        AddressDTO actual = this.addressService.updateAddress(dto,anyLong());

        assertEquals(addressDTO,actual);

    }

    @Test
    void findStudentsOfAddress(){

        Address address = new Address();
        List<StudentWithCoursesDTO> studentWithCoursesDTOS = new ArrayList<>();
        when(mockAddressRepository.findById(anyLong())).thenReturn(Optional.of(address));
        when(mockStudentWithCoursesMapper.mapStudentListToStudentWithCoursesDTOList(any())).thenReturn(studentWithCoursesDTOS);

        List<StudentWithCoursesDTO> actual = this.addressService.getStudentsOfAddress(anyLong());

        assertEquals(studentWithCoursesDTOS,actual);

    }

    @Test
    void findInstructorsOfAddress(){

        Address address = new Address();
        List<InstructorResponseDTO> instructorResponseDTOS = new ArrayList<>();
        when(mockAddressRepository.findById(anyLong())).thenReturn(Optional.of(address));
        when(mockInstructorResponseMapper.mapInstructorListToInstructorResponseDTOList(any())).thenReturn(instructorResponseDTOS);

        List<InstructorResponseDTO> actual = this.addressService.getInstructorsOfAddress(anyLong());

        assertEquals(instructorResponseDTOS,actual);

    }

    @Test
    void findAddressById(){

        Address address = new Address();
        when(mockAddressRepository.findById(anyLong())).thenReturn(Optional.of(address));

        Address actual = this.addressService.findAddressById(anyLong());

        assertEquals(address,actual);

    }

}
