package com.company.model.filters.command;

import com.company.model.enity.Tour;
import com.company.model.filters.TourFilter;

public class CheckOperator implements FilterCommand {

    private final String operatorName;

    public CheckOperator(String operatorName) {
        this.operatorName = operatorName;
    }

    @Override
    public boolean check(Tour tour) {
        return TourFilter.compareByTourOperator(tour, operatorName);
    }
}
