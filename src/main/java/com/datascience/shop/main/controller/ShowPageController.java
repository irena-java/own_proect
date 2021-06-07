package com.datascience.shop.main.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowPageController implements Controller {

    private final String VIEW_NANE;

    public ShowPageController(String VIEW_NANE) {
        this.VIEW_NANE = VIEW_NANE;
    }

    @Override
    public ControllerResultDto execute(HttpServletRequest req, HttpServletResponse resp) {
        return new ControllerResultDto(VIEW_NANE);
    }
}