package dev.patika.schoolsystem.contoller;

import dev.patika.schoolsystem.controller.AddressController;
import dev.patika.schoolsystem.dto.AddressDTO;
import dev.patika.schoolsystem.dto.InstructorResponseDTO;
import dev.patika.schoolsystem.dto.StudentWithCoursesDTO;
import dev.patika.schoolsystem.service.AddressService;
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
public class AddressControllerTest {

    @Mock
    AddressService addressService;

    @InjectMocks
    AddressController addressController;


    @Test
    void getList(){

        List<AddressDTO> addressDTOList = new ArrayList<>();
        when(addressService.findAllAddress()).thenReturn(addressDTOList);

        ResponseEntity<List<AddressDTO>> actual = this.addressController.findAllList();

        assertAll(
                () -> assertEquals(addressDTOList,actual.getBody()),
                () -> assertEquals(HttpStatus.OK,actual.getStatusCode())
        );


    }

    @Test
    void getAddressById(){
        AddressDTO addressDTO = new AddressDTO();
        when(addressService.findByAddressId(anyLong())).thenReturn(addressDTO);

        ResponseEntity<AddressDTO> actual = this.addressController.findAddressById(anyLong());

        assertAll(
                () -> assertEquals(addressDTO,actual.getBody()),
                () -> assertEquals(HttpStatus.OK,actual.getStatusCode())
        );


    }

    @Test
    void getStudentsOfAddress(){

        List<StudentWithCoursesDTO> studentWithCoursesDTOList = new ArrayList<>();
        when(addressService.getStudentsOfAddress(anyLong())).thenReturn(studentWithCoursesDTOList);

        ResponseEntity<List<StudentWithCoursesDTO>> actual = this.addressController.findStudentsOfAddress(anyLong());

        assertAll(
                () -> assertEquals(studentWithCoursesDTOList,actual.getBody()),
                () -> assertEquals(HttpStatus.OK,actual.getStatusCode())
        );
    }

    @Test
    void getInstructorsOfAddress(){

        List<InstructorResponseDTO> instructorResponseDTOS = new ArrayList<>();
        when(addressService.getInstructorsOfAddress(anyLong())).thenReturn(instructorResponseDTOS);

        ResponseEntity<List<InstructorResponseDTO>> actual = this.addressController.findInstructorsOfAddress(anyLong());

        assertAll(
                () -> assertEquals(instructorResponseDTOS,actual.getBody()),
                () -> assertEquals(HttpStatus.OK,actual.getStatusCode())
        );
    }

    @Test
    void saveAddress(){

        AddressDTO addressDTO = new AddressDTO();
        when(addressService.saveAddress(any())).thenReturn(addressDTO);

        AddressDTO dto = new AddressDTO();
        ResponseEntity<AddressDTO> actual = this.addressController.saveAddress(dto);

        assertAll(
                () -> assertEquals(addressDTO,actual.getBody()),
                () -> assertEquals(HttpStatus.OK,actual.getStatusCode())
        );
    }

    @Test
    void updateAddress(){

        AddressDTO addressDTO = new AddressDTO();
        when(addressService.updateAddress(any(),anyLong())).thenReturn(addressDTO);

        AddressDTO dto = new AddressDTO();
        ResponseEntity<AddressDTO> actual = this.addressController.updateAddress(dto,1);

        assertAll(
                () -> assertEquals(addressDTO,actual.getBody()),
                () -> assertEquals(HttpStatus.OK,actual.getStatusCode())
        );
    }

    @Test
    void deleteAddress(){

        when(addressService.deleteAddressById(anyLong())).thenReturn(anyString());

        ResponseEntity<String> actual = this.addressController.deleteAddressWithId(1);

        assertEquals(HttpStatus.OK,actual.getStatusCode());
    }
}
