package se.lexicon;

import java.time.LocalDate;
import java.time.Period;

public class LocalDateToAgeConverter {
    public int getAge(LocalDate past){
        return Period.between(past, LocalDate.now()).getYears();
    }
}
