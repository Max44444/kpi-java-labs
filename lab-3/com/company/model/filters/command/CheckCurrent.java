package com.company.model.filters.command;

import com.company.model.enity.Tour;
import com.company.model.filters.TourFilter;

import java.util.Calendar;

public class CheckCurrent implements FilterCommand {

    private final String name;
    private final Calendar date;

    public CheckCurrent(String name, Calendar date) {
        this.name = name;
        this.date = date;
    }

    @Override
    public boolean check(Tour tour) {
        return TourFilter.isCurrent(tour, name, date);
    }

}
