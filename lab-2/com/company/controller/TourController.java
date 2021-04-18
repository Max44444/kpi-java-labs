package com.company.controller;

import com.company.controller.listener.ConsoleListener;
import com.company.controller.listener.Listener;
import com.company.errors.EmptyStringException;
import com.company.errors.IncorrectNumberException;
import com.company.errors.IncorrectTourException;
import com.company.errors.NoVisitPointsException;
import com.company.model.TourModel;
import com.company.model.enity.Tour;
import com.company.validator.Validator;
import com.company.view.ConsoleView;
import com.company.view.View;
import com.company.view.dictionaries.Dictionary;

import java.util.Calendar;

public class TourController {
    private final TourModel model = new TourModel();
    private final View view = new ConsoleView();
    private final Listener listener = new ConsoleListener(view);
    private boolean isWorking = false;

     public TourController() {
        start();
    }

    public void start() {
        view.showMessage(Dictionary.GREETING);
        showHelp();
        isWorking = true;
        work();
    }

    public void stop() {
        isWorking = false;
        view.showMessage(Dictionary.FAREWELL);
    }

    private void work() {
        while (isWorking) {
            execute(listener.getInt(Dictionary.ENTER, Dictionary.COMMAND));
        }
    }

    private void execute(int commandId) {
        switch (commandId) {
            case 1:
                showHelp();
                break;
            case 2:
                stop();
                break;
            case 3:
                showTourList();
                break;
            case 4:
                showToursByOperator();
                break;
            case 5:
                showToursByVisitPoints();
                break;
            case 6:
                showCurrentTours();
                break;
            case 7:
                addTour();
                break;
            case 8:
                removeTour();
                break;
            default:
                view.showMessage(Dictionary.ERROR_COMMAND);
        }
    }

    private void showHelp() {
        view.showMessage(Dictionary.HELP);
    }

    private void showTourList() {
        view.showTours(model.getAll());
    }

    private void showToursByOperator() {
         try {
             String candidate = listener.getLine(Dictionary.ENTER, Dictionary.TOUR_OPERATOR);
             Validator.notEmpty(candidate);
             view.showTours(model.getToursByOperator(candidate));
         } catch (EmptyStringException e) {
             view.showMessage(e.getMessage());
         }
    }

    private void showToursByVisitPoints() {
         try {
             String[] candidate = listener.getLine(Dictionary.ENTER, Dictionary.VISIT_POINTS).split(" ");
             Validator.notEmpty(candidate);
             view.showTours(model.getToursByVisitPoint(candidate));
         } catch (NoVisitPointsException e) {
             view.showMessage(e.getMessage());
         }
    }

    private void showCurrentTours() {
         try {
             String candidateName = listener.getLine(Dictionary.ENTER, Dictionary.NAME);
             Calendar candidateDate = listener.getDate(Dictionary.ENTER, Dictionary.DATE);
             Validator.notEmpty(candidateName);
             Validator.checkLowerBound(candidateDate, Calendar.getInstance());
             view.showTours(model.getCurrentTours(candidateName, candidateDate));
         } catch (Exception e) {
             view.showMessage(e.getMessage());
         }
    }

    private void addTour() {
         try {
             Tour candidate = listener.getTour();
             Validator.checkTour(candidate);
             view.showTours(new Tour[]{candidate});
             if (listener.confirm(Dictionary.SAVE_QUESTION)) {
                 model.add(candidate);
                 view.showMessage(Dictionary.COMPLETE);
             }
         } catch (IncorrectTourException e) {
             view.showMessage(e.getMessage());
         }
    }

    private void removeTour() {
         try {
             int id = listener.getInt(Dictionary.ENTER, Dictionary.ID);
             Validator.checkLowerBound(id, 0);
             model.deleteById(id);
             view.showMessage(Dictionary.COMPLETE);
         } catch (IncorrectNumberException e) {
             view.showMessage(e.getMessage());
         }
    }
}
