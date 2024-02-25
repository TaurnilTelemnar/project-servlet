package com.tictactoe.controller;

import com.tictactoe.repository.CellRepository;
import com.tictactoe.service.FieldService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/start")
public class InitController extends BaseController {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession currentSession = req.getSession(true);
        CellRepository cellRepository = new CellRepository();
        FieldService fieldService = new FieldService(cellRepository);
        currentSession.setAttribute("service", fieldService);
        ServletContext servletContext = getServletContext();
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(super.PATH_INDEX);
        requestDispatcher.forward(req, resp);
    }
}
