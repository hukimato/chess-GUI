package Models;

public interface Figure {

    public Cell[][] getMovableCells(Cell[][] cells, Cords cords);

    public Color getColor();
}
