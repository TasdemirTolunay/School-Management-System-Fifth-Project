package dev.patika.schoolsystem.contoller;

import dev.patika.schoolsystem.controller.ExceptionAppErrorController;
import dev.patika.schoolsystem.entity.ExceptionsAppErrorResponse;
import dev.patika.schoolsystem.service.ExceptionsAppErrorResponseService;
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
public class ExceptionAppErrorControllerTest {

    @Mock
    ExceptionsAppErrorResponseService errorResponseService;

    @InjectMocks
    ExceptionAppErrorController errorController;

    @Test
    void getAllList(){

        List<ExceptionsAppErrorResponse> errorResponseList = new ArrayList<>();
        when(errorResponseService.findAllExceptions()).thenReturn(errorResponseList);

        ResponseEntity<List<ExceptionsAppErrorResponse>> actual = this.errorController.findAllException();

        assertAll(
                () -> assertEquals(errorResponseList,actual.getBody()),
                () -> assertEquals(HttpStatus.OK,actual.getStatusCode())
        );
    }

    @Test
    void getExceptionByThrowDate(){

        List<ExceptionsAppErrorResponse> errorResponseList = new ArrayList<>();
        when(errorResponseService.findExceptionsByThrowDate("2021-03-08")).thenReturn(errorResponseList);

        ResponseEntity<List<ExceptionsAppErrorResponse>> actual = this.errorController.getExceptionsByThrowDate("2021-03-08");

        assertAll(
                () -> assertEquals(errorResponseList,actual.getBody()),
                () -> assertEquals(HttpStatus.OK,actual.getStatusCode())
        );
    }

    @Test
    void getExceptionByType(){

        List<ExceptionsAppErrorResponse> errorResponseList = new ArrayList<>();
        when(errorResponseService.findExceptionsByTypeName(anyString())).thenReturn(errorResponseList);

        ResponseEntity<List<ExceptionsAppErrorResponse>> actual = this.errorController.getExceptionsByType(anyString());

        assertAll(
                () -> assertEquals(errorResponseList,actual.getBody()),
                () -> assertEquals(HttpStatus.OK,actual.getStatusCode())
        );
    }

    @Test
    void getExceptionByStatusCode(){

        List<ExceptionsAppErrorResponse> errorResponseList = new ArrayList<>();
        when(errorResponseService.findExceptionsByStatusCode(anyInt())).thenReturn(errorResponseList);

        ResponseEntity<List<ExceptionsAppErrorResponse>> actual = this.errorController.getExceptionByStatusCode(anyInt());

        assertAll(
                () -> assertEquals(errorResponseList,actual.getBody()),
                () -> assertEquals(HttpStatus.OK,actual.getStatusCode())
        );
    }
}
