package Models;

public class PawnChangeState implements State {
    Cell cell;

    public PawnChangeState(Cell cell) {
        this.cell = cell.deepClone();
    }

    @Override
    public Object handle() {
        return cell.getCords().number() + ":" + cell.getCords().letter();
    }
}
