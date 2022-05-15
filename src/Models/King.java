package Models;

public class King implements Figure{
    private Color color;
    private BoardSnapshot prevBoardState;

    SpecialMove specialMove;

    public King(Color color, SpecialMove specialMove){
        this.color = color;
        this.specialMove = specialMove;
    }

    @Override
    public Cell[][] getMovableCells(Cell[][] cells, Cords cords) {


        // Рокировка
        if (this.color == Color.WHITE){
            if (cords.number() == 0 && cords.letter() == 4) // Король в стартовом положении
            {
                if (cells[0][0].getFigure() instanceof Rook && cells[0][1].getFigure() == null &&
                        cells[0][2].getFigure() == null && cells[0][3].getFigure() == null ){ // Ладья в стартовом положении
                    // Поля между не под боем
                    boolean beatableFields = false;

//                    for (int i =0; i < 8; i++){
//                        for (int j = 0 ; j < 8; j++){
//                            if (cells[i][j].getFigure() != null && cells[i][j].getFigure().getColor() == Color.BLACK)
//                            {
//                                Cell[][] tmpCells;
//                                if (cells[i][j].getFigure() instanceof King){
//                                    tmpCells = ((King) cells[i][j].getFigure()).getBeatableCells(cells, new Cords(i,j));
//                                }
//                                else
//                                    tmpCells = cells[i][j].getMovableCells(cells.clone());
//                                if (tmpCells[0][1].getIsMovable() || tmpCells[0][2].getIsMovable() || tmpCells[0][3].getIsMovable() )
//                                {
//                                    beatableFields = true;
//                                }
//                            }
//                        }
//                    }
                    // Король и ладья не делали ход
                    do {
                        if (prevBoardState.getPrevSnapshot().getCells()[0][4].getFigure() instanceof King &&
                                prevBoardState.getPrevSnapshot().getCells()[0][0].getFigure() instanceof Rook)
                            prevBoardState = prevBoardState.getPrevSnapshot();
                        else
                            break;
                    } while (prevBoardState.getPrevSnapshot().getCurrentTurn() != prevBoardState.getCurrentTurn());
                    if (prevBoardState.getPrevSnapshot().getCurrentTurn() == prevBoardState.getCurrentTurn() && !beatableFields)
                        cells[0][2].setIsMovable(true);
                        specialMove = new KingSpecialMove(-2);
                }
                if (cells[0][7].getFigure() instanceof Rook&& cells[0][5].getFigure() == null &&
                        cells[0][6].getFigure() == null){ // Ладья в стартовом положении
                    // Поля между не под боем
                    boolean beatableFields = false;

//                    for (int i =0; i < 8; i++){
//                        for (int j = 0 ; j < 8; j++){
//                            if (cells[i][j].getFigure() != null && cells[i][j].getFigure().getColor() == Color.BLACK)
//                            {
//                                Cell[][] tmpCells;
//                                if (cells[i][j].getFigure() instanceof King){
//                                    tmpCells = ((King) cells[i][j].getFigure()).getBeatableCells(cells, new Cords(i,j));
//                                }
//                                else
//                                    tmpCells = cells[i][j].getMovableCells(cells.clone());
//                                if (tmpCells[0][6].getIsMovable() || tmpCells[0][5].getIsMovable())
//                                {
//                                    beatableFields = true;
//                                }
//                            }
//                        }
//                    }
                    // Король и ладья не делали ход
                    do {
                        System.out.println(prevBoardState.getCurrentTurn());
                        if (prevBoardState.getPrevSnapshot().getCells()[0][4].getFigure() instanceof King &&
                                prevBoardState.getPrevSnapshot().getCells()[0][7].getFigure() instanceof Rook)
                            prevBoardState = prevBoardState.getPrevSnapshot();
                        else
                            break;
                    } while (prevBoardState.getPrevSnapshot().getCurrentTurn() != prevBoardState.getCurrentTurn());
                    if (prevBoardState.getPrevSnapshot().getCurrentTurn() == prevBoardState.getCurrentTurn() && !beatableFields)
                        cells[0][6].setIsMovable(true);
                    specialMove = new KingSpecialMove(+2);
                }
            }
        }
        else {
            if (cords.number() == 7 && cords.letter() == 4) // Король в стартовом положении
            {
                if (cells[7][0].getFigure() instanceof Rook && cells[7][1].getFigure() == null &&
                        cells[7][2].getFigure() == null && cells[7][3].getFigure() == null ){ // Ладья в стартовом положении
                    // Поля между не под боем
                    boolean beatableFields = false;

//                    for (int i =0; i < 8; i++){
//                        for (int j = 0 ; j < 8; j++){
//                            if (cells[i][j].getFigure() != null && cells[i][j].getFigure().getColor() == Color.WHITE)
//                            {
//                                Cell[][] tmpCells;
//                                if (cells[i][j].getFigure() instanceof King){
//                                    tmpCells = ((King) cells[i][j].getFigure()).getBeatableCells(cells, new Cords(i,j));
//                                }
//                                else
//                                    tmpCells = cells[i][j].getMovableCells(cells.clone());
//                                if (tmpCells[7][1].getIsMovable() || tmpCells[7][2].getIsMovable() || tmpCells[7][3].getIsMovable() )
//                                {
//                                    beatableFields = true;
//                                }
//                            }
//                        }
//                    }
                    // Король и ладья не делали ход
                    do {
                        if (prevBoardState.getPrevSnapshot().getCells()[7][4].getFigure() instanceof King &&
                                prevBoardState.getPrevSnapshot().getCells()[7][0].getFigure() instanceof Rook)
                            prevBoardState = prevBoardState.getPrevSnapshot();
                        else
                            break;
                    } while (prevBoardState.getPrevSnapshot().getCurrentTurn() != prevBoardState.getCurrentTurn());
                    if (prevBoardState.getPrevSnapshot().getCurrentTurn() == prevBoardState.getCurrentTurn() && !beatableFields)
                        cells[7][2].setIsMovable(true);
                    specialMove = new KingSpecialMove(-2);
                }
                if (cells[7][7].getFigure() instanceof Rook&& cells[7][5].getFigure() == null &&
                        cells[7][6].getFigure() == null){ // Ладья в стартовом положении
                    // Поля между не под боем
                    boolean beatableFields = false;

//                    for (int i =0; i < 8; i++){
//                        for (int j = 0 ; j < 8; j++){
//                            if (cells[i][j].getFigure() != null && cells[i][j].getFigure().getColor() == Color.WHITE)
//                            {
//                                Cell[][] tmpCells;
//                                if (cells[i][j].getFigure() instanceof King){
//                                    tmpCells = ((King) cells[i][j].getFigure()).getBeatableCells(cells, new Cords(i,j));
//                                }
//                                else
//                                    tmpCells = cells[i][j].getMovableCells(cells.clone());
//                                if (tmpCells[7][6].getIsMovable() || tmpCells[7][5].getIsMovable())
//                                {
//                                    beatableFields = true;
//                                }
//                            }
//                        }
//                    }
                    // Король и ладья не делали ход
                    do {
                        if (prevBoardState.getPrevSnapshot().getCells()[7][4].getFigure() instanceof King &&
                                prevBoardState.getPrevSnapshot().getCells()[7][7].getFigure() instanceof Rook)
                            prevBoardState = prevBoardState.getPrevSnapshot();
                        else
                            break;
                    } while (prevBoardState.getPrevSnapshot().getCurrentTurn() != prevBoardState.getCurrentTurn());
                    if (prevBoardState.getPrevSnapshot().getCurrentTurn() == prevBoardState.getCurrentTurn() && !beatableFields)
                        cells[7][6].setIsMovable(true);
                    specialMove = new KingSpecialMove(+2);
                }
            }
        }

        if (cords.number() + 1 < 8){
            if (cells[cords.number()+1][cords.letter()].getFigure() == null || cells[cords.number()+1][cords.letter()].getFigure().getColor() != this.color)
                cells[cords.number()+1][cords.letter()].setIsMovable(true);
            if (cords.letter() + 1 < 8 &&
                    ( cells[cords.number()+1][cords.letter()+1].getFigure() == null ||
                            cells[cords.number()+1][cords.letter()+1].getFigure().getColor() != this.color) )
                cells[cords.number()+1][cords.letter()+1].setIsMovable(true);
            if (cords.letter() - 1 >= 0 &&
                    ( cells[cords.number()+1][cords.letter()+1].getFigure() == null ||
                            cells[cords.number()+1][cords.letter()+1].getFigure().getColor() != this.color))
                cells[cords.number()+1][cords.letter()+1].setIsMovable(true);
        }
        if (cords.number() - 1 >= 0){
            if (cells[cords.number()-1][cords.letter()].getFigure() == null ||
                    cells[cords.number()-1][cords.letter()].getFigure().getColor() != this.color)
                cells[cords.number()-1][cords.letter()].setIsMovable(true);
            if (cords.letter() + 1 < 8 && (cells[cords.number()-1][cords.letter()+1].getFigure() == null || cells[cords.number()-1][cords.letter()+1].getFigure().getColor() != this.color))
                cells[cords.number()-1][cords.letter()+1].setIsMovable(true);
            if (cords.letter() - 1 >= 0 && (cells[cords.number()-1][cords.letter()+1].getFigure() == null || cells[cords.number()-1][cords.letter()+1].getFigure().getColor() != this.color))
                cells[cords.number()-1][cords.letter()+1].setIsMovable(true);
        }
        if (cords.letter() + 1 < 8 && (cells[cords.number()][cords.letter()+1].getFigure() == null || cells[cords.number()][cords.letter()+1].getFigure().getColor() != this.color))
            cells[cords.number()][cords.letter()+1].setIsMovable(true);
        if (cords.letter() - 1 >= 0 && (cells[cords.number()][cords.letter()-1].getFigure()==null || cells[cords.number()][cords.letter()-1].getFigure().getColor() != this.color))
            cells[cords.number()][cords.letter()-1].setIsMovable(true);

        return cells;
    }

    @Override
    public Color getColor() {
        return this.color;
    }

    public void setPrevBoardState(BoardSnapshot board) { this.prevBoardState = board; }

    private Cell[][] getBeatableCells(Cell[][] cells, Cords cords) {
        Cell[][] ret_cells = new Cell[8][8];
        for (int i = 0; i < 8; i++){
            for (int j =0; j < 8; j++){
                ret_cells[i][j] = cells[i][j].deepClone();
                ret_cells[i][j].setIsMovable(false);
            }
        }

        if (cords.number() + 1 < 8) {
            if (ret_cells[cords.number() + 1][cords.letter()].getFigure() == null || ret_cells[cords.number() + 1][cords.letter()].getFigure().getColor() != this.color)
                ret_cells[cords.number() + 1][cords.letter()].setIsMovable(true);
            if (cords.letter() + 1 < 8 &&
                    (ret_cells[cords.number() + 1][cords.letter() + 1].getFigure() == null ||
                            ret_cells[cords.number() + 1][cords.letter() + 1].getFigure().getColor() != this.color))
                ret_cells[cords.number() + 1][cords.letter() + 1].setIsMovable(true);
            if (cords.letter() - 1 >= 0 &&
                    (ret_cells[cords.number() + 1][cords.letter() + 1].getFigure() == null ||
                            ret_cells[cords.number() + 1][cords.letter() + 1].getFigure().getColor() != this.color))
                ret_cells[cords.number() + 1][cords.letter() + 1].setIsMovable(true);
        }
        if (cords.number() - 1 >= 0) {
            if (ret_cells[cords.number() - 1][cords.letter()].getFigure() == null ||
                    ret_cells[cords.number() - 1][cords.letter()].getFigure().getColor() != this.color)
                ret_cells[cords.number() - 1][cords.letter()].setIsMovable(true);
            if (cords.letter() + 1 < 8 && (ret_cells[cords.number() - 1][cords.letter() + 1].getFigure() == null || ret_cells[cords.number() - 1][cords.letter() + 1].getFigure().getColor() != this.color))
                ret_cells[cords.number() - 1][cords.letter() + 1].setIsMovable(true);
            if (cords.letter() - 1 >= 0 && (ret_cells[cords.number() - 1][cords.letter() + 1].getFigure() == null || ret_cells[cords.number() - 1][cords.letter() + 1].getFigure().getColor() != this.color))
                ret_cells[cords.number() - 1][cords.letter() + 1].setIsMovable(true);
        }
        if (cords.letter() + 1 < 8 && (ret_cells[cords.number()][cords.letter() + 1].getFigure() == null || ret_cells[cords.number()][cords.letter() + 1].getFigure().getColor() != this.color))
            ret_cells[cords.number()][cords.letter() + 1].setIsMovable(true);
        if (cords.letter() - 1 >= 0 && (ret_cells[cords.number()][cords.letter() - 1].getFigure() == null || ret_cells[cords.number()][cords.letter() - 1].getFigure().getColor() != this.color))
            ret_cells[cords.number()][cords.letter() - 1].setIsMovable(true);

        return ret_cells;
    }
}
