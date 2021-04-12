package com.company.controller;

import com.company.controller.listener.ConsoleListener;
import com.company.controller.listener.Listener;
import com.company.exceptions.*;
import com.company.model.FileTourWriter;
import com.company.model.Model;
import com.company.model.enity.Tour;
import com.company.validator.Validator;
import com.company.view.ConsoleView;
import com.company.view.View;
import com.company.view.dictionaries.Dictionary;

import java.util.Calendar;

public class TourController {
    private Model model;
    private View view;
    private Listener listener;
    private boolean isWorking = false;

    public void start() {
        init();
        view.showMessage(Dictionary.GREETING);
        showHelp();
        work();
    }

    public void stop() {
        isWorking = false;
        view.showMessage(Dictionary.FAREWELL);
        try {
            model.getControl().saveDataToDB();
        } catch (DataBaseException e) {
            view.showError(e.getMessage());
        }
    }

    private void work() {
        int command;
        while (isWorking) {
            command = listener.getInt(Dictionary.ENTER, Dictionary.COMMAND);
            execute(command);
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
                view.showError(Dictionary.ERROR_COMMAND.toString());
        }
    }

    private void showHelp() {
        view.showMessage(Dictionary.HELP);
    }

    private void showTourList() {
        view.showTours(model.getFilter().getAll());
        saveLastResult();
    }

    private void showToursByOperator() {
        try {
            String candidate = listener.getLine(Dictionary.ENTER, Dictionary.TOUR_OPERATOR);
            Validator.notEmpty(candidate);
            view.showTours(model.getFilter().getToursByOperator(candidate));
            saveLastResult();
        } catch (EmptyStringException e) {
            view.showError(e.getMessage());
        }
    }

    private void showToursByVisitPoints() {
        try {
            String[] candidate = listener.getLine(Dictionary.ENTER, Dictionary.VISIT_POINTS).split(" ");
            Validator.notEmpty(candidate);
            view.showTours(model.getFilter().getToursByVisitPoint(candidate));
            saveLastResult();
        } catch (NoVisitPointsException e) {
            view.showError(e.getMessage());
        }
    }

    private void showCurrentTours() {
        try {
            String candidateName = listener.getLine(Dictionary.ENTER, Dictionary.NAME);
            Validator.notEmpty(candidateName);
            Calendar candidateDate = listener.getDate(Dictionary.ENTER, Dictionary.DATE);
            Validator.checkLowerBound(candidateDate, Calendar.getInstance());
            view.showTours(model.getFilter().getCurrentTours(candidateName, candidateDate));
            saveLastResult();
        } catch (EmptyStringException | IncorrectDateException e) {
            view.showError(e.getMessage());
        }
    }

    private void addTour() {
        try {
            Tour candidate = listener.getTour();
            view.showTours(new Tour[]{candidate});
            if (listener.confirm(Dictionary.SAVE_QUESTION)) {
                model.getControl().addTour(candidate);
                view.showMessage(Dictionary.COMPLETE);
            }
        } catch (IncorrectTourException | DataBaseException e) {
            view.showError(e.getMessage());
        }
    }

    private void removeTour() {
        try {
            int id = listener.getInt(Dictionary.ENTER, Dictionary.ID);
            Validator.checkLowerBound(id, 0);
            model.getControl().deleteTourByID(id);
            view.showMessage(Dictionary.COMPLETE);
        } catch (IncorrectNumberException | DataBaseException e) {
            view.showError(e.getMessage());
        }
    }

    private void saveLastResult() {
        if (model.getFilter().getBuffer().length != 0 && listener.confirm(Dictionary.SAVE_QUESTION)) {
            try {
                FileTourWriter.saveDataIntoFile(
                    listener.getLine(Dictionary.ENTER, Dictionary.FILENAME),
                    model.getFilter().getBuffer()
                );
                view.showMessage(Dictionary.SUCCESS);
            } catch (DataSavingException e) {
                view.showMessage(Dictionary.SAVE_ERROR);
            }

        }
    }

    private void init() {
        view = new ConsoleView();
        try {
            model = new Model();
        } catch (DataBaseException e) {
            view.showError(e.getMessage());
            System.exit(0);
        }
        listener = new ConsoleListener(view);
        isWorking = true;
    }
}
