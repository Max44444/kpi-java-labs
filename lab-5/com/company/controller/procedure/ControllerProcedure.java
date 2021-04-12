package com.company.controller.procedure;

import com.company.exceptions.MVCWorkingException;

@FunctionalInterface
public interface ControllerProcedure {
    void execute() throws MVCWorkingException;
}
