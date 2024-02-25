package com.tictactoe.controller;

import com.tictactoe.repository.FieldRepository;
import com.tictactoe.service.FieldService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/start")
public class InitController extends HttpServlet {
    private String pathIndex = "/index.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession currentSession = req.getSession(true);
        FieldRepository fieldRepository = new FieldRepository(9);
        FieldService fieldService = new FieldService(fieldRepository);
        currentSession.setAttribute("service", fieldService);
        ServletContext servletContext = super.getServletContext();
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(pathIndex);
        requestDispatcher.forward(req, resp);
    }
}
