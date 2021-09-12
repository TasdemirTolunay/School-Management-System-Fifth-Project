package dev.patika.schoolsystem.util;


import java.time.LocalDate;

public class StudentAgeValid {

    public boolean studentAgeValidator(int birthDateYear){

        int age = (LocalDate.now().getYear()) - birthDateYear;
        if(age < 18 || age > 40){
            return true;
        }else{
            return false;
        }
    }
}
