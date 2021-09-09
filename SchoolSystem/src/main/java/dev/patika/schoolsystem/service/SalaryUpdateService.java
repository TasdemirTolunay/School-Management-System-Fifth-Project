package dev.patika.schoolsystem.service;

import dev.patika.schoolsystem.entity.Instructor;
import dev.patika.schoolsystem.entity.PermanentInstructor;
import dev.patika.schoolsystem.entity.SalaryUpdate;
import dev.patika.schoolsystem.entity.VisitingResearcher;
import dev.patika.schoolsystem.entity.enums.RaiseType;
import dev.patika.schoolsystem.exceptions.IdNotFoundException;
import dev.patika.schoolsystem.exceptions.ReductionNotValidException;
import dev.patika.schoolsystem.repository.InstructorRepository;
import dev.patika.schoolsystem.repository.SalaryUpdateRepository;
import dev.patika.schoolsystem.util.ErrorMessageConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SalaryUpdateService {

    @Autowired
    private SalaryUpdateRepository salaryUpdateRepository;

    @Autowired
    private InstructorRepository instructorRepository;

    @Transactional
    public SalaryUpdate instructorSalaryUpdate(long instructorId, RaiseType raiseType, double changeAmount){

        SalaryUpdate salaryUpdate = new SalaryUpdate();
        Instructor instructor = instructorRepository.findById(instructorId)
                .orElseThrow(() -> new IdNotFoundException(String.format(ErrorMessageConstants.INSTRUCTOR_NOT_FOUND, instructorId)));
        salaryUpdate.setInstructorId(instructor.getId());
        if (instructor instanceof PermanentInstructor){

            salaryUpdate.setSalaryType("fixedSalary");
            double salary = ((PermanentInstructor) instructor).getFixedSalary();
            salaryUpdate.setBeforeUpdateSalary(salary);
            salaryUpdate.setPercentChangeAmount(changeAmount);
            if(raiseType.equals(RaiseType.Incraise)){

                double percentSalary = (salary * changeAmount) / 100.0;
                double incraiseResult = salary + percentSalary;
                ((PermanentInstructor) instructor).setFixedSalary(incraiseResult);
                salaryUpdate.setRaiseType(raiseType);
                salaryUpdate.setAfterUpdateSalary(incraiseResult);

            }else if (raiseType.equals(RaiseType.Reduction)){

                if(changeAmount > 100.0){

                    throw new ReductionNotValidException(ErrorMessageConstants.WRONG_REDUCTION_AMOUNT);

                }
                double percentSalary = (salary * changeAmount) / 100.0;
                double reductionResult = salary - percentSalary;
                ((PermanentInstructor) instructor).setFixedSalary(reductionResult);
                salaryUpdate.setRaiseType(raiseType);
                salaryUpdate.setAfterUpdateSalary(reductionResult);

            }

        }
        if (instructor instanceof VisitingResearcher){

            salaryUpdate.setSalaryType("hourlySalary");
            double salary = ((VisitingResearcher) instructor).getHourlySalary();
            salaryUpdate.setBeforeUpdateSalary(salary);
            salaryUpdate.setPercentChangeAmount(changeAmount);
            if(raiseType.equals(RaiseType.Incraise)){

                double percentSalary = (salary * changeAmount) / 100.0;
                double incraiseResult = salary + percentSalary;
                ((VisitingResearcher) instructor).setHourlySalary(incraiseResult);
                salaryUpdate.setRaiseType(raiseType);
                salaryUpdate.setAfterUpdateSalary(incraiseResult);

            }else if (raiseType.equals(RaiseType.Reduction)){

                if(changeAmount > 100.0){

                    throw new ReductionNotValidException(ErrorMessageConstants.WRONG_REDUCTION_AMOUNT);

                }
                double percentSalary = (salary * changeAmount) / 100.0;
                double reductionResult = salary - percentSalary;
                ((VisitingResearcher) instructor).setHourlySalary(reductionResult);
                salaryUpdate.setRaiseType(raiseType);
                salaryUpdate.setAfterUpdateSalary(reductionResult);

            }


        }

        instructorRepository.save(instructor);
        salaryUpdate.setRequestTime(LocalDate.now());
        salaryUpdateRepository.save(salaryUpdate);
        return salaryUpdate;

    }

    public List<SalaryUpdate> findSalaryUpdateByInstructorId(long instructorId){

        return salaryUpdateRepository.findSalaryUpdateByInstructorId(instructorId);

    }

    public List<SalaryUpdate> findSalaryUpdateByRequestTime(String requestTime){

        LocalDate date = LocalDate.parse(requestTime);
        return salaryUpdateRepository.findSalaryUpdateByRequestTime(date);

    }

}
