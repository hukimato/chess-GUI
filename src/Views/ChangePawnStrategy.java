package Views;

import Presenters.Presenter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangePawnStrategy implements Strategy {
    int number;
    int letter;

    public ChangePawnStrategy(String cords){
        String[] cords_str = cords.split(":");
        number = Integer.parseInt(cords_str[0]);
        letter = Integer.parseInt(cords_str[1]);
    }

    @Override
    public void execute(Presenter presenter, JLayeredPane layeredPane) {
        JFrame jFrame = new JFrame();
        JDialog jd = new JDialog(jFrame);
        jd.setLayout(new FlowLayout());
        jd.setBounds(500, 300, 400, 300);

        JLabel jLabel = new JLabel("Пешка дошла до дальней линии.");

        JButton queen = new JButton("Ферзь");
        queen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                presenter.changePawn(number, letter, "Queen");
                jd.setVisible(false);

                layeredPane.remove(0);
                layeredPane.add(presenter.reloadBoard(), JLayeredPane.DEFAULT_LAYER);
            }
        });
        jd.add(queen);

        JButton rook = new JButton("Ладья");
        queen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                presenter.changePawn(number, letter, "Rook");
                jd.setVisible(false);

                layeredPane.remove(0);
                layeredPane.add(presenter.reloadBoard(), JLayeredPane.DEFAULT_LAYER);
            }
        });
        jd.add(rook);

        JButton knight = new JButton("Конь");
        queen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                presenter.changePawn(number, letter, "Knight");
                jd.setVisible(false);
                layeredPane.remove(0);
                layeredPane.add(presenter.reloadBoard(), JLayeredPane.DEFAULT_LAYER);
            }
        });
        jd.add(knight);

        JButton bishop = new JButton("Слон");
        queen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                presenter.changePawn(number, letter, "Bishop");
                jd.setVisible(false);
                layeredPane.remove(0);
                layeredPane.add(presenter.reloadBoard(), JLayeredPane.DEFAULT_LAYER);
            }
        });
        jd.add(bishop);

        jd.add(jLabel);

        jd.setVisible(true);
    }
}
