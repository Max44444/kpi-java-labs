package com.company.model.filters.command;

import com.company.model.enity.Tour;
import com.company.model.filters.TourFilter;

public class CheckVisitPoints implements FilterCommand {

    private final String[] points;

    public CheckVisitPoints(String[] points) {
        this.points = points;
    }

    @Override
    public boolean check(Tour tour) {
        return TourFilter.compareByVisitPoints(tour, points);
    }

}
