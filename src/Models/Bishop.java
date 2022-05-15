package Models;

public class Bishop implements Figure{
    private Color color;

    public Bishop(Color color){
        this.color = color;
    }

    @Override
    public Cell[][] getMovableCells(Cell[][] cells, Cords cords) {
        for (int i = cords.number()+1, j = cords.letter()+1; i < 8 && j < 8; i++, j++){
            if (cells[i][j].getFigure() == null){
                cells[i][j].setIsMovable(true);
            }
            if (cells[i][j].getFigure() != null && cells[i][j].getFigure().getColor() != this.color){
                cells[i][j].setIsMovable(true);
                break;
            }
            if (cells[i][j].getFigure() != null && cells[i][j].getFigure().getColor() == this.color){
                break;
            }
        }
        for (int i = cords.number()-1, j = cords.letter()+1; i >= 0 && j < 8; i--, j++){
            if (cells[i][j].getFigure() == null){
                cells[i][j].setIsMovable(true);
            }
            if (cells[i][j].getFigure() != null && cells[i][j].getFigure().getColor() != this.color){
                cells[i][j].setIsMovable(true);
                break;
            }
            if (cells[i][j].getFigure() != null && cells[i][j].getFigure().getColor() == this.color){
                break;
            }
        }
        for (int i = cords.number()+1, j = cords.letter()-1; i < 8 && j >= 0; i++, j--){
            if (cells[i][j].getFigure() == null){
                cells[i][j].setIsMovable(true);
            }
            if (cells[i][j].getFigure() != null && cells[i][j].getFigure().getColor() != this.color){
                cells[i][j].setIsMovable(true);
                break;
            }
            if (cells[i][j].getFigure() != null && cells[i][j].getFigure().getColor() == this.color){
                break;
            }
        }
        for (int i = cords.number()-1, j = cords.letter()-1; i >= 0 && j >= 0; i--, j--){
            if (cells[i][j].getFigure() == null){
                cells[i][j].setIsMovable(true);
            }
            if (cells[i][j].getFigure() != null && cells[i][j].getFigure().getColor() != this.color){
                cells[i][j].setIsMovable(true);
                break;
            }
            if (cells[i][j].getFigure() != null && cells[i][j].getFigure().getColor() == this.color){
                break;
            }
        }
        return cells;
    }

    @Override
    public Color getColor() {
        return this.color;
    }
}
