package Models;

public class LoseState implements State {
    Color lostSide;

    public LoseState(Color color) {
        lostSide = color;
    }

    @Override
    public Object handle() {
        return lostSide;
    }
}
