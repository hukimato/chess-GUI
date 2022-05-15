package Views;

import Presenters.Presenter;

import javax.swing.*;

public interface Strategy {
    public void execute(Presenter presenter, JLayeredPane layeredPane);
}

