package com.company.view;

import com.company.model.enity.Tour;
import com.company.view.dictionaries.Dictionary;

public interface View {
    void showTours(Tour[] tours);

    void showMessage(Dictionary... message);
    void showError(String message);
}
