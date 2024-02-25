package com.tictactoe.repository;

import com.tictactoe.Field;
import com.tictactoe.entity.Cell;

import java.util.Map;
import java.util.Optional;

public interface Repository {
    public Optional<Cell> getCell(int id);
    public Map<Integer, Cell> getField();
    public boolean updateCell(Cell cell, int id);
    public void init(int size);
}