package com.company.controller;

import com.company.controller.listener.ConsoleListener;
import com.company.model.TourModel;
import com.company.model.enity.Tour;
import com.company.view.ConsoleView;
import com.company.view.Dictionary;

public class TourController {
    private final TourModel model = new TourModel();
    private final ConsoleView view = new ConsoleView();
    private final ConsoleListener listener = new ConsoleListener(view);
    private boolean isWorking = false;

     public TourController() {
        start();
    }

    public void start() {
        view.showMessage(Dictionary.GREETING);
        view.showMessage(Dictionary.HELP);
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
                view.showMessage(Dictionary.HELP);
                break;
            case 2:
                stop();
                break;
            case 3:
                view.showTours(model.getAll());
                break;
            case 4:
                view.showTours(model.getToursByOperator(listener.getLine(Dictionary.ENTER, Dictionary.TOUR_OPERATOR)));
                break;
            case 5:
                view.showTours(model.getToursByVisitPoint(listener.getLine(Dictionary.ENTER, Dictionary.VISIT_POINTS).split(" ")));
                break;
            case 6:
                view.showTours(model.getCurrentTours(
                    listener.getLine(Dictionary.ENTER, Dictionary.NAME),
                    listener.getDate(Dictionary.ENTER, Dictionary.DATE)
                ));
                break;
            case 7:
                Tour tour = listener.getTour();
                view.showTours(new Tour[]{tour});
                if (listener.confirm(Dictionary.SAVE_QUESTION)) {
                    model.add(tour);
                    view.showMessage(Dictionary.COMPLETE);
                }
                break;
            case 8:
                int id = listener.getInt(Dictionary.ENTER, Dictionary.ID);
                model.deleteById(id);
                view.showMessage(Dictionary.COMPLETE);
                break;
            default:
                view.showMessage(Dictionary.ERROR_COMMAND);
        }
    }
}
