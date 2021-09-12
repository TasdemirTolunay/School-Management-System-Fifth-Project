package dev.patika.schoolsystem.service;

import dev.patika.schoolsystem.entity.Instructor;
import dev.patika.schoolsystem.entity.SalaryUpdate;
import dev.patika.schoolsystem.entity.enums.RaiseType;
import dev.patika.schoolsystem.repository.InstructorRepository;
import dev.patika.schoolsystem.repository.SalaryUpdateRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
public class SalaryUpdateTest {

    @Mock
    SalaryUpdateRepository mockSalaryUpdateRepository;

    @Mock
    InstructorRepository mockInstructorRepository;

    @InjectMocks
    SalaryUpdateService salaryUpdateService;

    @Test
    void instructorSalaryUpdate(){

        Instructor instructor = new Instructor();
        SalaryUpdate salaryUpdate = new SalaryUpdate();
        when(mockInstructorRepository.findById(anyLong())).thenReturn(Optional.of(instructor));
        when(mockInstructorRepository.save(any())).thenReturn(instructor);
        salaryUpdate.setRequestTime(LocalDate.now());
        when(mockSalaryUpdateRepository.save(any())).thenReturn(salaryUpdate);

        SalaryUpdate update = new SalaryUpdate();
        SalaryUpdate actual = this.salaryUpdateService.instructorSalaryUpdate(1,RaiseType.Incraise,anyDouble(),update);

        assertEquals(salaryUpdate,actual);

    }

    @Test
    void findSalaryUpdateBySalaryId(){

        List<SalaryUpdate> salaryUpdateList = new ArrayList<>();
        when(mockSalaryUpdateRepository.findSalaryUpdateByInstructorId(anyLong())).thenReturn(salaryUpdateList);

        List<SalaryUpdate> actual = this.salaryUpdateService.findSalaryUpdateByInstructorId(anyLong());

        assertEquals(salaryUpdateList,actual);

    }

    @Test
    void findSalaryUpdateByRequestTime(){

        List<SalaryUpdate> salaryUpdateList = new ArrayList<>();
        when(mockSalaryUpdateRepository.findSalaryUpdateByRequestTime(any())).thenReturn(salaryUpdateList);

        String date = "2021-09-13";
        List<SalaryUpdate> actual = this.salaryUpdateService.findSalaryUpdateByRequestTime(date);

        assertEquals(salaryUpdateList,actual);

    }

}
