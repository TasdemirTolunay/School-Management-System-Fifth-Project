package dev.patika.schoolsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

/**
 * PermanentInstructor is the type of instructor that receives a fixed salary.
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PermanentInstructor extends Instructor {

    private double fixedSalary;

}