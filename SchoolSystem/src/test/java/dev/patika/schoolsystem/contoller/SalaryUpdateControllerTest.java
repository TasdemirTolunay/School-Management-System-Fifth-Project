package dev.patika.schoolsystem.contoller;

import dev.patika.schoolsystem.controller.SalaryUpdateController;
import dev.patika.schoolsystem.entity.SalaryUpdate;
import dev.patika.schoolsystem.entity.enums.RaiseType;
import dev.patika.schoolsystem.service.SalaryUpdateService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
public class SalaryUpdateControllerTest {

    @Mock
    SalaryUpdateService salaryUpdateService;

    @InjectMocks
    SalaryUpdateController salaryUpdateController;

    @Test
    void instructorSalaryUpdate(){

        SalaryUpdate salaryUpdate = new SalaryUpdate();
        HttpServletRequest request = new MockHttpServletRequest();
        SalaryUpdate salary = new SalaryUpdate();
        salary.setSession(request.getSession().toString());
        salary.setRequestUrl("");
        salary.setRemoteAdr("127.0.0.1");
        when(salaryUpdateService.instructorSalaryUpdate(1, RaiseType.Incraise,25.0,salary)).thenReturn(salaryUpdate);


        ResponseEntity<SalaryUpdate> actual = this.salaryUpdateController.instructorSalaryUpdate(request,1,RaiseType.Incraise,25.0);

        assertEquals(HttpStatus.OK,actual.getStatusCode());

    }

    @Test
    void getSalaryUpdateByInstructorId(){

        List<SalaryUpdate>  salaryUpdateList = new ArrayList<>();
        when(salaryUpdateService.findSalaryUpdateByInstructorId(anyLong())).thenReturn(salaryUpdateList);

        ResponseEntity<List<SalaryUpdate>> actual = this.salaryUpdateController.findSalaryUpdateByInstructorId(anyLong());

        assertAll(
                () -> assertEquals(salaryUpdateList,actual.getBody()),
                () -> assertEquals(HttpStatus.OK,actual.getStatusCode())
        );
    }

    @Test
    void getSalaryUpdateByRequestTime(){

        List<SalaryUpdate> salaryUpdateList = new ArrayList<>();
        when(salaryUpdateService.findSalaryUpdateByRequestTime("2021-03-08")).thenReturn(salaryUpdateList);

        ResponseEntity<List<SalaryUpdate>> actual = this.salaryUpdateController.findSalaryUpdateByRequestTime("2021-03-08");

        assertAll(
                () -> assertEquals(salaryUpdateList,actual.getBody()),
                () -> assertEquals(HttpStatus.OK,actual.getStatusCode())
        );
    }

}
