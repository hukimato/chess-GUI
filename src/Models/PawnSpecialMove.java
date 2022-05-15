package Models;

public class PawnSpecialMove implements SpecialMove{
    private int code;

    public PawnSpecialMove(int code){
        this.code = code;
    }
    @Override
    public int getMoveCode() {
        return this.code;
    }
}
