package com.company.model;

import com.company.model.db.DataBase;
import com.company.model.enity.Tour;
import com.company.model.filters.command.CheckCurrent;
import com.company.model.filters.command.CheckOperator;
import com.company.model.filters.command.CheckVisitPoints;
import com.company.model.filters.command.FilterCommand;

import java.util.Arrays;
import java.util.Calendar;

public class TourModelFilter {
    private final DataBase db;
    private Tour[] buffer;

    public TourModelFilter(DataBase db) {
        this.db = db;
    }

    public Tour[] getAll() {
        return buffer = db.getData();
    }

    public Tour[] getToursByOperator(String name) {
        return getToursByFilterCommand(new CheckOperator(name));
    }

    public Tour[] getToursByVisitPoint(String[] points) {
        return getToursByFilterCommand(new CheckVisitPoints(points));
    }

    public Tour[] getCurrentTours(String name, Calendar date) {
        return getToursByFilterCommand(new CheckCurrent(name, date));
    }

    public Tour[] getBuffer() {
        return buffer;
    }

    private Tour[] getToursByFilterCommand(FilterCommand filter) {
        buffer = new Tour[db.getData().length];
        int totalSize = 0;

        for (Tour e : db.getData()) {
            if (filter.check(e)) {
                buffer[totalSize++] = e;
            }
        }

        return buffer = Arrays.copyOf(buffer, totalSize);
    }
}
