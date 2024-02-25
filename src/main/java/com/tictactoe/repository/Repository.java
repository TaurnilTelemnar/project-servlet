package com.tictactoe.repository;

import com.tictactoe.entity.Cell;

import java.util.Map;
import java.util.Optional;

public interface Repository {
    Optional<Cell> getCell(int id);
    Map<Integer, Cell> getField();
    void updateCell(Cell cell, int id);
    void init();
}
