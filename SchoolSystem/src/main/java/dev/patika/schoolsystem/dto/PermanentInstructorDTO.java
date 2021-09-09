package dev.patika.schoolsystem.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * An PermanentInstructor DTO class that will return as the request and response object of the PermanentInstructor class.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PermanentInstructorDTO extends InstructorDTO{

    @NotNull(message = "Fixed Salary is mandatory")
    @ApiModelProperty(example = "9000")
    private double fixedSalary;

}
