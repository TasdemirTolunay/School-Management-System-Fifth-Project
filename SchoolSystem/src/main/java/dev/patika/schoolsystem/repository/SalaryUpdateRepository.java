package dev.patika.schoolsystem.repository;

import dev.patika.schoolsystem.entity.SalaryUpdate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SalaryUpdateRepository extends CrudRepository<SalaryUpdate,Long> {

    List<SalaryUpdate> findSalaryUpdateByRequestTime(LocalDate requestTime);
    List<SalaryUpdate> findSalaryUpdateByInstructorId(long instructorId);

}
