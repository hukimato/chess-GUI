package Views;

import Presenters.Presenter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LostStrategy implements Strategy {
    String lost;

    public LostStrategy(String lostSide){
        lost = lostSide;
    }

    @Override
    public void execute(Presenter presenter, JLayeredPane layeredPane) {
        JFrame jFrame = new JFrame();
        JDialog jd = new JDialog(jFrame);
        jd.setLayout(new FlowLayout());
        jd.setBounds(500, 300, 400, 300);

        JLabel jLabel = new JLabel(lost+ " проиграла.");

        JButton jButton = new JButton("Начань заново");
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                presenter.restart();
                jd.setVisible(false);

                layeredPane.remove(0);
                layeredPane.add(presenter.reloadBoard(), JLayeredPane.DEFAULT_LAYER);
            }
        });

        jd.add(jLabel);
        jd.add(jButton);
        jd.setVisible(true);
    }
}
