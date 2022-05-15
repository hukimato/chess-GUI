package Models;

public class Knight implements Figure{
    private Color color;

    public Knight(Color color){
        this.color = color;
    }

    @Override
    public Cell[][] getMovableCells(Cell[][] cells, Cords cords) {
        int letter = cords.letter();
        int number = cords.number();
        if (letter + 2 < 8){
            if (number+1 < 8)
                if (cells[number+1][letter+2].getFigure() == null || cells[number+1][letter+2].getFigure().getColor() != this.color)
                    cells[number+1][letter+2].setIsMovable(true);
            if (number-1 >= 0)
                if (cells[number-1][letter+2].getFigure() == null || cells[number-1][letter+2].getFigure().getColor() != this.color)
                    cells[number-1][letter+2].setIsMovable(true);
        }
        if (letter - 2 >= 0){
            if (number+1 < 8)
                if (cells[number+1][letter-2].getFigure()==null || cells[number+1][letter-2].getFigure().getColor() != this.color)
                    cells[number+1][letter-2].setIsMovable(true);
            if (number-1 >= 0)
                if (cells[number-1][letter-2].getFigure()==null || cells[number-1][letter-2].getFigure().getColor() != this.color)
                    cells[number-1][letter-2].setIsMovable(true);
        }
        if (number - 2 >= 0){
            if (letter+1 < 8)
                if (cells[number-2][letter+1].getFigure()==null || cells[number-2][letter+1].getFigure().getColor() != this.color)
                    cells[number-2][letter+1].setIsMovable(true);
            if (letter-1 >= 0)
                if (cells[number-2][letter-1].getFigure()==null || cells[number-2][letter-1].getFigure().getColor() != this.color)
                    cells[number-2][letter-1].setIsMovable(true);
        }
        if (number + 2 < 8){
            if (letter+1 < 8)
                if (cells[number+2][letter+1].getFigure()==null || cells[number+2][letter+1].getFigure().getColor() != this.color)
                    cells[number+2][letter+1].setIsMovable(true);
            if (letter-1 >= 0)
                if (cells[number+2][letter-1].getFigure()==null || cells[number+2][letter-1].getFigure().getColor() != this.color)
                    cells[number+2][letter-1].setIsMovable(true);
        }
        return cells;
    }

    @Override
    public Color getColor() {
        return this.color;
    }
}
