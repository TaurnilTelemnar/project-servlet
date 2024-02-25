package com.tictactoe.repository;

import com.tictactoe.entity.Cell;
import com.tictactoe.entity.Sign;

import java.util.*;

public class FieldRepository implements Repository{

    private Map<Integer, Cell> field;

    public FieldRepository(int size){
        init(size);
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
    public boolean updateCell(Cell cell, int id) {
        field.put(id, cell);
        return true;
    }

    @Override
    public void init(int size) {
        field = new HashMap<>();
        for (int i = 0; i < size; i++) {
            Cell emptyCell = Cell.builder()
                    .id(i)
                    .sign(Sign.EMPTY)
                    .build();
            field.put(i, emptyCell);
        }
    }
}
