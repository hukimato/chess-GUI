package Models;

import java.util.concurrent.CyclicBarrier;

public class Pawn implements Figure{
    private Color color;
    private BoardSnapshot prevBoardState;

    SpecialMove specialMove;

    public Pawn(Color color, SpecialMove specialMove){
        this.color = color;
        this.specialMove = specialMove;
    }

    @Override
    public Cell[][] getMovableCells(Cell[][] cells, Cords cords) {
        if (this.color == Color.WHITE){
            // Первый ход
            if (cords.number() == 1 && cells[cords.number()+2][cords.letter()].getFigure() == null){
                cells[cords.number()+2][cords.letter()].setIsMovable(true);
            }

            // Взятие на проходе
            if (cords.number() == 4){
                if (cells[cords.number()][cords.letter()+1].getFigure() instanceof Pawn &&
                        cells[6][cords.letter()+1].getFigure() == null){
                    if (prevBoardState.getPrevSnapshot().getCells()[6][cords.letter()+1].getFigure() instanceof Pawn &&
                            prevBoardState.getPrevSnapshot().getCells()[cords.number()][cords.letter()+1].getFigure() == null) {
                        cells[cords.number() + 1][cords.letter() + 1].setIsMovable(true);
                        specialMove = new PawnSpecialMove(-1);
                    }
                }
                if (cells[cords.number()][cords.letter()-1].getFigure() instanceof Pawn &&
                        cells[6][cords.letter()-1].getFigure() == null){
                    if (prevBoardState.getCells()[6][cords.letter()-1].getFigure() instanceof Pawn &&
                            prevBoardState.getCells()[cords.number()][cords.letter()-1].getFigure() == null){
                        cells[cords.number()+1][cords.letter()-1].setIsMovable(true);
                        specialMove = new PawnSpecialMove(-1);
                    }
                }
            }

            if (cords.number()+1 < 8 &&
                    cells[cords.number()+1][cords.letter()].getFigure() == null){
                cells[cords.number()+1][cords.letter()].setIsMovable(true);
            }

            if (cords.number()+1 < 8 && cords.letter()-1 >= 0 &&
                    cells[cords.number()+1][cords.letter()-1].getFigure() != null &&
                    this.color != cells[cords.number()+1][cords.letter()-1].getFigure().getColor()) {
                cells[cords.number()+1][cords.letter()-1].setIsMovable(true);
            }

            if (cords.number()+1 < 8 && cords.letter()+1 < 8 &&
                    cells[cords.number()+1][cords.letter()+1].getFigure() != null &&
                    this.color != cells[cords.number()+1][cords.letter()+1].getFigure().getColor()) {
                cells[cords.number()+1][cords.letter()+1].setIsMovable(true);
            }
        } else {
            if (cords.number() == 6 && cells[cords.number()-2][cords.letter()].getFigure() == null){
                cells[cords.number()-2][cords.letter()].setIsMovable(true);
            }

            // Взятие на проходе
            if (cords.number() == 3){
                if (cells[cords.number()][cords.letter()+1].getFigure() instanceof Pawn &&
                        cells[1][cords.letter()+1].getFigure() == null){
                    if (prevBoardState.getCells()[1][cords.letter()+1].getFigure() instanceof Pawn &&
                            prevBoardState.getCells()[cords.number()][cords.letter()+1].getFigure() == null)
                    {
                        cells[cords.number()-1][cords.letter()+1].setIsMovable(true);
                        specialMove = new PawnSpecialMove(+1);
                    }

                }
                if (cells[cords.number()][cords.letter()-1].getFigure() instanceof Pawn &&
                        cells[1][cords.letter()-1].getFigure() == null){
                    if (prevBoardState.getCells()[1][cords.letter()-1].getFigure() instanceof Pawn &&
                            prevBoardState.getCells()[cords.number()][cords.letter()-1].getFigure() == null)
                    {
                        cells[cords.number()-1][cords.letter()-1].setIsMovable(true);
                        specialMove = new PawnSpecialMove(+1);
                    }

                }
            }

            if (cords.number()-1 >= 0 &&
                    cells[cords.number()-1][cords.letter()].getFigure() == null){
                cells[cords.number()-1][cords.letter()].setIsMovable(true);
            }

            if (cords.number()-1 >= 0 && cords.letter()-1 >= 0 &&
                    cells[cords.number()-1][cords.letter()-1].getFigure() != null &&
                    this.color != cells[cords.number()-1][cords.letter()-1].getFigure().getColor()) {
                cells[cords.number()-1][cords.letter()-1].setIsMovable(true);
            }

            if (cords.number()-1 >= 0 && cords.letter()+1 < 8 &&
                    cells[cords.number()-1][cords.letter()+1].getFigure() != null &&
                    this.color != cells[cords.number()-1][cords.letter()+1].getFigure().getColor()) {
                cells[cords.number()-1][cords.letter()+1].setIsMovable(true);
            }
        }


        return cells;
    }

    @Override
    public Color getColor() {
        return this.color;
    }

    public void setPrevBoardState(BoardSnapshot board) { this.prevBoardState = board; }
}
