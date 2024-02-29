package com.tictactoe.controller;

import com.tictactoe.entity.Cell;
import com.tictactoe.entity.Sign;
import com.tictactoe.service.FieldService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/logic")
public class LogicController extends BaseController {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession currentSession = req.getSession();
        FieldService service = extractService(currentSession);
        if(isGameStopped(currentSession)){
            resp.sendRedirect(super.PATH_INDEX);
            return;
        }
        int selectedCellID = this.getSelectedCellID(req);

        if(!service.isCellEmpty(selectedCellID)){
            resp.sendRedirect(super.PATH_INDEX);
            return;
        }
        Cell selectedCell = service.getCell(selectedCellID);
        selectedCell.setSign(Sign.CROSS);
        service.updateCell(selectedCell, selectedCellID);

        if(checkWin(service, currentSession, resp)){
            return;
        }
        if(checkDraw(service, currentSession, resp)){
            return;
        }
        int emptyCellID = service.getNoughtNextMove();
        Cell cellForBotMove = new Cell(emptyCellID, Sign.NOUGHT);
        service.updateCell(cellForBotMove, emptyCellID);
        if(checkWin(service, currentSession, resp)){
            return;
        }
        if(checkDraw(service, currentSession, resp)){
            return;
        }
        resp.sendRedirect(super.PATH_INDEX);
    }

    private boolean isGameStopped(HttpSession currentSession) {
        Object winObj = currentSession.getAttribute("winner");
        Object drawObj = currentSession.getAttribute("draw");
        return drawObj != null || winObj != null;
    }

    private boolean checkDraw(FieldService service, HttpSession currentSession, HttpServletResponse resp) throws IOException {
        if(service.isGameDraw()){
            currentSession.setAttribute("draw", true);
            currentSession.setAttribute("service", service);
            resp.sendRedirect(super.PATH_INDEX);
            return true;
        }
        return false;
    }


    private FieldService extractService(HttpSession currentSession) {
        Object service = currentSession.getAttribute("service");
        if(service.getClass() != FieldService.class){
            currentSession.invalidate();
            throw new RuntimeException("Session is broken, try one more time");
        }
        return (FieldService) service;
    }

    private int getSelectedCellID(HttpServletRequest request){
        String click = request.getParameter("click");
        return Integer.parseInt(click);
    }

    private boolean checkWin(FieldService service, HttpSession currentSession, HttpServletResponse resp) throws IOException {
        if(service.isGameHasWinner()){
            Sign winner = service.whoIsWin();
            currentSession.setAttribute("winner", winner);
            currentSession.setAttribute("service", service);
            resp.sendRedirect(super.PATH_INDEX);
            return true;
        }
        return false;
    }
}
