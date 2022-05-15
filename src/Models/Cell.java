package Models;

public class Cell {
    private Figure figure;
    private final Cords cords;
    private boolean isMovable = false;

    private BoardSnapshot prevBoardState;

    public Cell(Figure figure, Cords cords)
    {
        this.figure = figure;
        this.cords = cords;
    }

    public Cell deepClone(){
        return new Cell(this.figure, this.cords);
    }

    public Cords getCords() {
        return cords;
    }

    public Figure getFigure() {
        return figure;
    }

    public boolean getIsMovable() {
        return isMovable;
    }

    public void setFigure(Figure figure) {
        this.figure = figure;
    }

    public void setIsMovable(boolean isMovable) {
        this.isMovable = isMovable;
    }

    public Cell[][] getMovableCells(Cell[][]  cells){
        if (figure instanceof Pawn){
            ((Pawn) figure).setPrevBoardState(prevBoardState);
        }
        if (figure instanceof King){
            ((King) figure).setPrevBoardState(prevBoardState);
        }
        return this.figure.getMovableCells(cells, this.cords);
    }

    public void setPrevBoardState(BoardSnapshot boardState){
        this.prevBoardState = boardState;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "figure=" + (figure==null ? null : figure.getClass() + " " + figure.getColor()) +
                ", cords=" + cords +
                ", isMovable=" + isMovable +
                '}';
    }
}
