package com.datascience.shop.controller;

public class ControllerResultDto {
    private final String view;
    private final boolean redirect;

    public ControllerResultDto(String view) {
        this.view = view;
        this.redirect = false;
    }

    public ControllerResultDto(String view, boolean redirect) {
        this.view = view;
        this.redirect = redirect;
    }

    public boolean isRedirect() {
        return redirect;
    }

    public String getView() {
        return view;
    }
}