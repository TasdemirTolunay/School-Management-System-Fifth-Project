package dev.patika.schoolsystem.service;

import dev.patika.schoolsystem.entity.ExceptionsAppErrorResponse;
import dev.patika.schoolsystem.repository.ExceptionsAppErrorResponseRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ExceptionsAppErrorServiceTest {

    @InjectMocks
    ExceptionsAppErrorResponseService errorResponseService;

    @Mock
    ExceptionsAppErrorResponseRepository mockErrorResponseRepository;

    @Test
    void findAllExceptions(){

        Iterable<ExceptionsAppErrorResponse> errorResponseIterable = new ArrayList<>();
        List<ExceptionsAppErrorResponse> errorResponseList = new ArrayList<>();
        when(mockErrorResponseRepository.findAll()).thenReturn(errorResponseIterable);
        errorResponseIterable.iterator().forEachRemaining(errorResponseList :: add);

        List<ExceptionsAppErrorResponse> actual = this.errorResponseService.findAllExceptions();

        assertEquals(errorResponseList,actual);

    }

    @Test
    void findExceptionsByThrowDate(){

        List<ExceptionsAppErrorResponse> errorResponseList = new ArrayList<>();
        when(mockErrorResponseRepository.getExceptionsAppErrorResponsesByExceptionThrowDate(any())).thenReturn(errorResponseList);

        String date = "2021-09-12";
        List<ExceptionsAppErrorResponse> actual = this.errorResponseService.findExceptionsByThrowDate(date);

        assertEquals(errorResponseList,actual);

    }

    @Test
    void findExceptionsByTypeName(){

        List<ExceptionsAppErrorResponse> errorResponseList = new ArrayList<>();
        when(mockErrorResponseRepository.getExceptionsAppErrorResponsesByExceptionType(anyString())).thenReturn(errorResponseList);

        List<ExceptionsAppErrorResponse> actual = this.errorResponseService.findExceptionsByTypeName(anyString());

        assertEquals(errorResponseList,actual);

    }

    @Test
    void findExceptionsByStatusCode(){

        List<ExceptionsAppErrorResponse> errorResponseList = new ArrayList<>();
        when(mockErrorResponseRepository.findExceptionsAppErrorResponsesByStatus(anyInt())).thenReturn(errorResponseList);

        List<ExceptionsAppErrorResponse> actual = this.errorResponseService.findExceptionsByStatusCode(anyInt());

        assertEquals(errorResponseList,actual);

    }

}
