package com.tictactoe.service;

import com.tictactoe.entity.Cell;
import com.tictactoe.entity.Sign;
import com.tictactoe.repository.FieldRepository;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
public class FieldService {
    private final FieldRepository fieldRepository;
    public void updateCell(Cell cell, int id){
        fieldRepository.updateCell(cell, id);
    }
    public Cell getCell(int id){
        return fieldRepository.getCell(id).orElseThrow();
    }
    public Map<Integer, Cell> getField(){
        return fieldRepository.getField();
    }
    // проверка пустой ячейки
    public boolean isCellEmpty(int id){
        Cell cell = fieldRepository.getCell(id).orElseThrow();
        return cell.getSign().equals(Sign.EMPTY);
    }

    //опеределяет наличие свободных ячеек
    public boolean isGameOver(){
        return getEmptyCellID() == null;
    }
    //возвращает первую пустую ячейку
    //ToDO заменить на алгоритм минимакса
    public Integer getEmptyCellID(){
        for(Map.Entry<Integer, Cell> entry: getField().entrySet()){
            if(entry.getValue().getSign().equals(Sign.EMPTY)){
                return entry.getKey();
            }
        }
        return null;
    }
    //опеределяет победителя
    public Sign whoIsWin(){
        for (Line line : getAllLines()) {
            if(line.isLineHasWinner()){
                return line.whoIsWinner();
            }
        }
        return null;
    }
    //ToDO вычислить зависимость количества допустимых линий от количества ячеек
    private List<Line> getAllLines(){
        List<Line> lines = new ArrayList<>();
        Map<Integer, Cell> field = fieldRepository.getField();
        lines.add(new Line(field.get(0), field.get(1), field.get(2)));
        lines.add(new Line(field.get(3), field.get(4), field.get(5)));
        lines.add(new Line(field.get(6), field.get(7), field.get(8)));
        lines.add(new Line(field.get(0), field.get(3), field.get(6)));
        lines.add(new Line(field.get(1), field.get(4), field.get(7)));
        lines.add(new Line(field.get(2), field.get(5), field.get(8)));
        lines.add(new Line(field.get(0), field.get(4), field.get(8)));
        lines.add(new Line(field.get(2), field.get(4), field.get(6)));
        return lines;
    }
    private static class Line {
        private final Cell first;
        private final Cell second;
        private final Cell third;
        public Line(Cell first, Cell second, Cell third){
            this.first = first;
            this.second = second;
            this.third = third;
        }
        public boolean isLineHasWinner(){
            Sign firstSign = first.getSign();
            Sign secondSign = second.getSign();
            Sign thirdSign = third.getSign();

            if(firstSign.equals(secondSign) && firstSign.equals(thirdSign)){
                if(firstSign.equals(Sign.CROSS)) return true;
                if(firstSign.equals(Sign.NOUGHT)) return true;
                if(firstSign.equals(Sign.EMPTY)) return false;
            }
            return false;
        }

        public Sign whoIsWinner(){
            Sign firstSign = first.getSign();
            Sign secondSign = second.getSign();
            Sign thirdSign = third.getSign();
            if(firstSign.equals(secondSign) && firstSign.equals(thirdSign)){
                return firstSign;
            }
            return null;
        }
    }
}
