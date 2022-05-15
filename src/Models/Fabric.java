package Models;

public interface Fabric {

    public Figure createPawn();
    public Figure createRook();
    public Figure createKnight();
    public Figure createBishop();
    public Figure createQueen();
    public Figure createKing();

    public void setSpecialMove(SpecialMove specialMove);
}

class FabricWhite implements Fabric{
    SpecialMove specialMove;

    @Override
    public void setSpecialMove(SpecialMove specialMove) {
        this.specialMove = specialMove;
    }

    @Override
    public Figure createPawn() {
        return new Pawn(Color.WHITE, specialMove);
    }

    @Override
    public Figure createRook() {
        return new Rook(Color.WHITE);
    }

    @Override
    public Figure createKnight() {
        return new Knight(Color.WHITE);
    }

    @Override
    public Figure createBishop() {
        return new Bishop(Color.WHITE);
    }

    @Override
    public Figure createQueen() {
        return new Queen(Color.WHITE);
    }

    @Override
    public Figure createKing() {
        return new King(Color.WHITE, specialMove);
    }
}

class FabricBlack implements Fabric{
    SpecialMove specialMove;

    @Override
    public void setSpecialMove(SpecialMove specialMove) {
        this.specialMove = specialMove;
    }

    @Override
    public Figure createPawn() {
        return new Pawn(Color.BLACK, specialMove);
    }

    @Override
    public Figure createRook() {
        return new Rook(Color.BLACK);
    }

    @Override
    public Figure createKnight() {
        return new Knight(Color.BLACK);
    }

    @Override
    public Figure createBishop() {
        return new Bishop(Color.BLACK);
    }

    @Override
    public Figure createQueen() {
        return new Queen(Color.BLACK);
    }

    @Override
    public Figure createKing() {
        return new King(Color.BLACK, specialMove);
    }
}
