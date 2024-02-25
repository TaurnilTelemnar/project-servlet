package com.tictactoe.repository;

import com.tictactoe.entity.Cell;
import com.tictactoe.entity.Sign;

import java.util.*;

public class CellRepository implements Repository{

    private Map<Integer, Cell> field;

    public CellRepository(){
        init();
    }
    @Override
    public Optional<Cell> getCell(int id) {
        return Optional.ofNullable(field.get(id));
    }

    @Override
    public Map<Integer, Cell> getField() {
        return field;
    }

    @Override
    public void updateCell(Cell cell, int id) {
        field.put(id, cell);
    }

    @Override
    public void init() {
        field = new HashMap<>();
        for (int i = 0; i < 9; i++) {
            Cell emptyCell = Cell.builder()
                    .id(i)
                    .sign(Sign.EMPTY)
                    .build();
            field.put(i, emptyCell);
        }
    }
}
