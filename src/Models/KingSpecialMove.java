package Models;

public class KingSpecialMove implements SpecialMove{
    private int code;

    public KingSpecialMove(int code){
        this.code = code;
    }
    @Override
    public int getMoveCode() {
        return this.code;
    }
}
