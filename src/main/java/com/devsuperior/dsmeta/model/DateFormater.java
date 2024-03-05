package com.devsuperior.dsmeta.model;

import java.time.LocalDate;

public class DateFormater {
    private LocalDate minDate;
    private LocalDate maxDate;

    public DateFormater(LocalDate minDate, LocalDate maxDate) {
        this.minDate = minDate;
        this.maxDate = maxDate;
    }

    public LocalDate getMinDate() {
        return minDate;
    }

    public LocalDate getMaxDate() {
        return maxDate;
    }
}
