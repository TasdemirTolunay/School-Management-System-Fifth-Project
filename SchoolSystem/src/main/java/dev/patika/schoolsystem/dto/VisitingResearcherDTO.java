package dev.patika.schoolsystem.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * An VisitingResearcher DTO class that will return as the request and response object of the VisitingResearcher class.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VisitingResearcherDTO extends InstructorDTO{

    @NotNull(message = "Hourly Salary is mandatory")
    @ApiModelProperty(example = "150")
    private double hourlySalary;

}
