package dev.patika.schoolsystem.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a instructor enrolled in the school.
 * An instructor can teach many courses.
 * An instructor can be enrolled to one address.
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Instructor extends BaseEntity{

    /**
     * This instructor's variables.
     */
    private String instructorName;
    private String instructorPhoneNumber;

    /**
     * Defines the relation of the instructor with the address and the course.
     */
    @JsonBackReference
    @ManyToOne
    Address instructorAddress;

    @JsonManagedReference
    @OneToMany(mappedBy = "instructor")
    private List<Course> courseList = new ArrayList<>();

}