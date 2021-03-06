package dev.patika.schoolsystem.controller;

import dev.patika.schoolsystem.entity.SalaryUpdate;
import dev.patika.schoolsystem.entity.enums.RaiseType;
import dev.patika.schoolsystem.service.SalaryUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/salary")
public class SalaryUpdateController {

    @Autowired
    private SalaryUpdateService salaryUpdateService;

    @PostMapping("/update/{instructorId}/{raiseType}/{percentChangeAmount}")
    public ResponseEntity<SalaryUpdate> instructorSalaryUpdate(HttpServletRequest request, @PathVariable long instructorId, @PathVariable RaiseType raiseType, @PathVariable double percentChangeAmount){

        SalaryUpdate salaryUpdate = new SalaryUpdate();
        salaryUpdate.setRequestUrl(request.getRequestURI());
        salaryUpdate.setRemoteAdr(request.getRemoteAddr());
        salaryUpdate.setSession(request.getSession().toString());
        return new ResponseEntity<>(salaryUpdateService.instructorSalaryUpdate(instructorId,raiseType,percentChangeAmount,salaryUpdate), HttpStatus.OK);

    }

    @GetMapping("/findByInstructor/{instructorId}")
    public ResponseEntity<List<SalaryUpdate>> findSalaryUpdateByInstructorId(@PathVariable long instructorId){

        return new ResponseEntity<>(salaryUpdateService.findSalaryUpdateByInstructorId(instructorId),HttpStatus.OK);

    }

    @GetMapping("/findByRequestTime/{requestTime}")
    public ResponseEntity<List<SalaryUpdate>> findSalaryUpdateByRequestTime(@PathVariable String requestTime){

        return new ResponseEntity<>(salaryUpdateService.findSalaryUpdateByRequestTime(requestTime),HttpStatus.OK);

    }

}
