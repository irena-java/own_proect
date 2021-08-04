package com.datascience.shop.servlet;

import com.datascience.shop.controller.Controller;
import com.datascience.shop.controller.ControllerFactory;
import com.datascience.shop.controller.ControllerResultDto;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/datascience-shop/*")
public class DispatcherServlet extends HttpServlet {

    private ControllerFactory controllerFactory;

    @Override
    public void init() throws ServletException {
        controllerFactory = new ControllerFactory();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Controller controller = controllerFactory.getController(req);
        try {
            ControllerResultDto result = controller.execute(req, resp);
            doForwardOrRedirect(result, req, resp);
        } catch (Exception e) {
            throw new ServletException("Cannot execute action", e);
        }
    }

    private void doForwardOrRedirect(ControllerResultDto result, HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        if (result.isRedirect()) {
            resp.sendRedirect(result.getView());
        } else {
        String path = String.format("/WEB-INF/jsp/" + result.getView() + ".jsp");
        req.getRequestDispatcher(path).forward(req, resp);
    }}
}