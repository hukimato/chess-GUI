import Views.ChessGUI;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        JFrame frame = new ChessGUI();

    }
}
// Models.Board - фасад для работы с классами Models
// Абстрактная фабрика происзводит фигуры
// Presenter - адаптер/посредник между Board и ChessGUI
// Memento - хранит состояния доски (организован как односвязный список)
// Прототип реализован в классе Object
//
