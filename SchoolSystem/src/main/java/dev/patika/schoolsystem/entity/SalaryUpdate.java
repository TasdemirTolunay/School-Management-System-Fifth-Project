package dev.patika.schoolsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.patika.schoolsystem.entity.enums.RaiseType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SalaryUpdate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long salaryUpdateId;

    private long instructorId;
    private String salaryType;

    @Enumerated(EnumType.STRING)
    private RaiseType raiseType;

    private double beforeUpdateSalary;
    private double afterUpdateSalary;
    private double percentChangeAmount;
    private LocalDate requestTime;

}
