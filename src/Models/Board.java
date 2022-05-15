package Models;

import javax.swing.*;
import java.util.Arrays;

// Является фасадом для всех классов Models
public class Board {
    private Cell[][] cells = new Cell[8][8]; // [number][letter]
    private BoardSnapshot snapshot = null;
    private Color currentTurn = Color.WHITE;
    private SpecialMove specialMove = null;
    private State state;

    // Вызывается контроллером при создании доски
    public Board()
    {
        Fabric fabric = new FabricWhite();
        fabric.setSpecialMove(specialMove);
        for(int i =0; i < 8; i++){
            cells[1][i] = new Cell(fabric.createPawn(), new Cords(1,i));
        }
        cells[0][0] = new Cell(fabric.createRook(), new Cords(0,0));
        cells[0][7] = new Cell(fabric.createRook(), new Cords(0,7));
        cells[0][1] = new Cell(fabric.createKnight(), new Cords(0,1));
        cells[0][6] = new Cell(fabric.createKnight(), new Cords(0,6));
        cells[0][2] = new Cell(fabric.createBishop(), new Cords(0,2));
        cells[0][5] = new Cell(fabric.createBishop(), new Cords(0,5));
        cells[0][3] = new Cell(fabric.createQueen(), new Cords(0,3));
        cells[0][4] = new Cell(fabric.createKing(), new Cords(0,4));

        for(int i =2; i < 7; i++){
            for(int j =0; j < 8; j++){
                cells[i][j] = new Cell(null, new Cords(i,j));
            }

        }

        fabric = new FabricBlack();
        fabric.setSpecialMove(specialMove);
        cells[7][0] = new Cell(fabric.createRook(), new Cords(7,0));
        cells[7][7] = new Cell(fabric.createRook(), new Cords(7,7));
        cells[7][1] = new Cell(fabric.createKnight(), new Cords(7,1));
        cells[7][6] = new Cell(fabric.createKnight(), new Cords(7,6));
        cells[7][2] = new Cell(fabric.createBishop(), new Cords(7,2));
        cells[7][5] = new Cell(fabric.createBishop(), new Cords(7,5));
        cells[7][3] = new Cell(fabric.createQueen(), new Cords(7,3));
        cells[7][4] = new Cell(fabric.createKing(), new Cords(7,4));
        for(int i =0; i < 8; i++){
            cells[6][i] = new Cell(fabric.createPawn(), new Cords(6,i));
        }

        snapshot = new BoardSnapshot(cells, null, currentTurn);

        state = new DefaultState();
    }

    // Вызывается контроллером при перемещении фигуры
    public void moveFigure(Cell curCell, Cell toMoveCell)
    {
        state = new DefaultState();
        if (currentTurn == Color.WHITE){
            snapshot = new BoardSnapshot(cells, snapshot.deepCopy(), Color.BLACK);
        } else {
            snapshot = new BoardSnapshot(cells, snapshot.deepCopy(), Color.WHITE);
        }

        // Сброс при нажатии на ту же фигуру
        if (curCell.getCords().letter() == toMoveCell.getCords().letter() &&
                curCell.getCords().number() == toMoveCell.getCords().number())
        {
            clearIsMovable();
        }


        if (toMoveCell.getIsMovable()){

            // Проверка проигрыша
            if (toMoveCell.getFigure() != null && toMoveCell.getFigure() instanceof King){
                if (toMoveCell.getFigure().getColor() == Color.WHITE){
                    System.out.println("WHITE LOST");
                    state = new LoseState(Color.WHITE);
                }
                else {
                    System.out.println("BLACK LOST");
                    state = new LoseState(Color.BLACK);
                }
            }

            // Проверка смены пешки
            if (curCell.getFigure() instanceof Pawn && curCell.getFigure().getColor() == Color.WHITE && toMoveCell.getCords().number() == 7){
                System.out.println("WHITE PAWN CHANGE");
                state = new PawnChangeState(toMoveCell);
            }
            if (curCell.getFigure() instanceof Pawn && curCell.getFigure().getColor() == Color.BLACK && toMoveCell.getCords().number() == 0){
                System.out.println("BLACK PAWN CHANGE");
                state = new PawnChangeState(toMoveCell);
            }

            // Взятие на проходе
            if (curCell.getFigure() instanceof Pawn){
                Pawn curPawn = (Pawn)curCell.getFigure();
                if (curPawn.specialMove != null)
                {
                    int code = curPawn.specialMove.getMoveCode();
                    toMoveCell.setFigure(curCell.getFigure());
                    curCell.setFigure(null);
                    System.out.println("Обработка взятия на проходе");
                    cells[toMoveCell.getCords().number()+code][toMoveCell.getCords().letter()].setFigure(null);
                    clearIsMovable();
                    curPawn.specialMove = null;
                    if (currentTurn == Color.WHITE)
                        currentTurn = Color.BLACK;
                    else
                        currentTurn = Color.WHITE;
                    return;
                }
            }

            // Рокировка
            if (curCell.getFigure() instanceof King){
                King curKing = (King)curCell.getFigure();
                if (curKing.specialMove != null)
                {
                    int code = curKing.specialMove.getMoveCode();
                    toMoveCell.setFigure(curCell.getFigure());
                    curCell.setFigure(null);
                    System.out.println("Обработка рокировки");

                    if (code < 0){
                        cells[toMoveCell.getCords().number()][toMoveCell.getCords().letter()+1].setFigure(cells[toMoveCell.getCords().number()][0].getFigure());
                        cells[toMoveCell.getCords().number()][0].setFigure(null);
                    }
                    else {
                        cells[toMoveCell.getCords().number()][toMoveCell.getCords().letter()-1].setFigure(cells[toMoveCell.getCords().number()][7].getFigure());
                        cells[toMoveCell.getCords().number()][7].setFigure(null);
                    }

                    clearIsMovable();
                    curKing.specialMove = null;
                    if (currentTurn == Color.WHITE)
                        currentTurn = Color.BLACK;
                    else
                        currentTurn = Color.WHITE;
                    return;
                }
            }

            toMoveCell.setFigure(curCell.getFigure());
            curCell.setFigure(null);
            clearIsMovable();
            if (currentTurn == Color.WHITE)
                currentTurn = Color.BLACK;
            else
                currentTurn = Color.WHITE;
        }
        else {
            clearIsMovable();
        }
    }

    // Вызывается контроллером для получения состояния доски
    public Cell[][] getCells()
    {
        return this.cells;
    }

    // Вызывается контроллером при выборе фигуры для движения, для получения возможных ходов
    public void getMovableCells(Cell currentCell)
    {
        if (currentCell.getFigure().getColor() != currentTurn)
            return;
        currentCell.setPrevBoardState(this.snapshot);
        cells = currentCell.getMovableCells(cells);
    }

    public void clearIsMovable(){
        for(int i =0; i < 8; i++){
            for (int j =0; j< 8; j++){
                cells[i][j].setIsMovable(false);
            }
        }
    }

    public void setPawn(int n, int l, String Figure){
        if (n == 0){
            Fabric fabric = new FabricBlack();
            if (Figure.equals("Queen"))
                cells[7][l].setFigure(fabric.createQueen());
            if (Figure.equals("Knight"))
                cells[7][l].setFigure(fabric.createQueen());
            if (Figure.equals("Rook"))
                cells[7][l].setFigure(fabric.createRook());
            if (Figure.equals("Bishop"))
                cells[7][l].setFigure(fabric.createBishop());
        }
        if (n == 7){
            Fabric fabric = new FabricWhite();
            if (Figure.equals("Queen"))
                cells[7][l].setFigure(fabric.createQueen());
            if (Figure.equals("Knight"))
                cells[7][l].setFigure(fabric.createQueen());
            if (Figure.equals("Rook"))
                cells[7][l].setFigure(fabric.createRook());
            if (Figure.equals("Bishop"))
                cells[7][l].setFigure(fabric.createBishop());
        }

    }

    // Вызывает контроллером для возврата хода
    public void restore(){
        this.cells = snapshot.getCells();
        this.snapshot = snapshot.getPrevSnapshot();
        this.currentTurn = snapshot.getCurrentTurn();
        System.out.println(currentTurn);
        clearIsMovable();
    }

    public State getState(){
        return this.state;
    }
}

// Memento Pattern
class BoardSnapshot {
    private BoardSnapshot prevSnapshot;
    private Cell[][] cells;
    private Color currentTurn;

    public BoardSnapshot(Cell[][] cells, BoardSnapshot prevSnapshot, Color currentTurn){
        this.cells = new Cell[8][8];
        for (int i = 0; i < 8; i++){
            for (int j =0; j < 8; j++){
                this.cells[i][j] = cells[i][j].deepClone();
            }
        }
        this.prevSnapshot = prevSnapshot;
        this.currentTurn = currentTurn;
    }

    public Cell[][] getCells() {
        return cells;
    }

    public BoardSnapshot getPrevSnapshot() {
        if (prevSnapshot == null) {
            return this;
        }
        return prevSnapshot;
    }

    public Color getCurrentTurn(){
        return this.currentTurn;
    }

    public BoardSnapshot deepCopy(){
        return new BoardSnapshot(this.cells.clone(), this.prevSnapshot,  this.currentTurn);
    }
}
