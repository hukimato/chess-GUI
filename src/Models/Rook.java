package Models;

public class Rook implements Figure{
    private Color color;

    public Rook(Color color){
        this.color = color;
    }

    @Override
    public Cell[][] getMovableCells(Cell[][] cells, Cords cords) {
        for (int i =cords.number() + 1; i < 8; i++){
            if (cells[i][cords.letter()].getFigure() == null)
                cells[i][cords.letter()].setIsMovable(true);
            if (cells[i][cords.letter()].getFigure() != null && cells[i][cords.letter()].getFigure().getColor() != this.color){
                cells[i][cords.letter()].setIsMovable(true);
                break;
            }
            if (cells[i][cords.letter()].getFigure() != null && cells[i][cords.letter()].getFigure().getColor() == this.color)
                break;
        }
        for (int i =cords.number() - 1; i >= 0; i--){
            if (cells[i][cords.letter()].getFigure() == null)
                cells[i][cords.letter()].setIsMovable(true);
            if (cells[i][cords.letter()].getFigure() != null && cells[i][cords.letter()].getFigure().getColor() != this.color){
                cells[i][cords.letter()].setIsMovable(true);
                break;
            }
            if (cells[i][cords.letter()].getFigure() != null && cells[i][cords.letter()].getFigure().getColor() == this.color)
                break;
        }
        for (int i =cords.letter() + 1; i < 8; i++){
            if (cells[cords.number()][i].getFigure() == null)
                cells[cords.number()][i].setIsMovable(true);
            if (cells[cords.number()][i].getFigure() != null && cells[cords.number()][i].getFigure().getColor() != this.color) {
                cells[cords.number()][i].setIsMovable(true);
                break;
            }
            if (cells[cords.number()][i].getFigure() != null && cells[cords.number()][i].getFigure().getColor() == this.color)
                break;
        }
        for (int i =cords.letter() - 1; i >= 0; i--){
            if (cells[cords.number()][i].getFigure() == null)
                cells[cords.number()][i].setIsMovable(true);
            if (cells[cords.number()][i].getFigure() != null && cells[cords.number()][i].getFigure().getColor() != this.color) {
                cells[cords.number()][i].setIsMovable(true);
                break;
            }
            if (cells[cords.number()][i].getFigure() != null && cells[cords.number()][i].getFigure().getColor() == this.color)
                break;
        }
        return cells;
    }

    @Override
    public Color getColor() {
        return this.color;
    }
}
