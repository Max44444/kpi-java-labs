package com.company.controller.listener;

import com.company.exceptions.IncorrectTourException;
import com.company.model.enity.Tour;
import com.company.view.dictionaries.Dictionary;

import java.util.Calendar;

public interface Listener {

    int getInt(Dictionary... pre);
    String getLine(Dictionary... pre);
    Calendar getDate(Dictionary... pre);
    boolean confirm(Dictionary... pre);
    Tour getTour() throws IncorrectTourException;

}
