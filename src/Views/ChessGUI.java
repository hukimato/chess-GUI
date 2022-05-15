package Views;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

import Presenters.Presenter;

public class ChessGUI extends JFrame implements MouseListener, MouseMotionListener {
    JLayeredPane layeredPane;
    JPanel chessBoard;
    JLabel chessPiece;
    JButton prevState;
    int xAdjustment;
    int yAdjustment;

    Presenter presenter = new Presenter();

    public ChessGUI() {
        Container frame = getContentPane();
        frame.setLayout(new BorderLayout());




        JToolBar buttonPane = new JToolBar();
        frame.add(buttonPane, BorderLayout.NORTH);
        prevState = new JButton("Отменить ход");
        buttonPane.setPreferredSize(new Dimension(600, 100));
        prevState.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                presenter.restoreBoard();
                chessBoard = presenter.reloadBoard();

                layeredPane.remove(0);
                layeredPane.add(chessBoard, JLayeredPane.DEFAULT_LAYER);
            }
        });
        buttonPane.add(prevState);


        layeredPane = new JLayeredPane();
        frame.add(layeredPane, BorderLayout.CENTER);
        layeredPane.setPreferredSize(new Dimension(600, 600));
        layeredPane.addMouseListener(this);
        layeredPane.addMouseMotionListener(this);
        chessBoard = presenter.reloadBoard();
        layeredPane.add(chessBoard, JLayeredPane.DEFAULT_LAYER);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE );
        pack();
        setResizable(true);
        setLocationRelativeTo( null );
        setVisible(true);
        setPreferredSize(new Dimension(600, 700));
    }

    private boolean isAlreadyClickedOnce = false;
    public void mouseClicked(MouseEvent e) {
        if (!isAlreadyClickedOnce){
            chessPiece = null;
            Component c = chessBoard.findComponentAt(e.getX(), e.getY());
            if (c instanceof JPanel)
                return;
            chessPiece = (JLabel) c;

            presenter.getMovableCells(chessBoard, chessPiece);
            chessBoard = presenter.reloadBoard();

            layeredPane.remove(0);
            layeredPane.add(chessBoard, JLayeredPane.DEFAULT_LAYER);

            c = chessBoard.findComponentAt(e.getX(), e.getY());
            if (c instanceof JPanel)
                return;
            chessPiece = (JLabel) c;

            isAlreadyClickedOnce = true;
        }
        else {
            Component c = chessBoard.findComponentAt(e.getX(), e.getY());

            if (chessPiece == c){
                System.out.println("Выбрана та же фигура. Сброс");
                presenter.clearIsMovable();
                chessBoard = presenter.reloadBoard();
                layeredPane.remove(0);
                layeredPane.add(chessBoard, JLayeredPane.DEFAULT_LAYER);
                isAlreadyClickedOnce = false;
                return;
            }

            presenter.moveFigure(chessBoard, c);

            chessBoard = presenter.reloadBoard();
            layeredPane.remove(0);
            layeredPane.add(chessBoard, JLayeredPane.DEFAULT_LAYER);
            isAlreadyClickedOnce = false;

            Strategy strategy = presenter.checkStrategy();
            strategy.execute(presenter, layeredPane);

            chessBoard = presenter.reloadBoard();
            layeredPane.remove(0);
            layeredPane.add(chessBoard, JLayeredPane.DEFAULT_LAYER);
        }
    }
















    public void mousePressed(MouseEvent e) {
//        chessPiece = null;
//        Component c = chessBoard.findComponentAt(e.getX(), e.getY());
//
//        if (c instanceof JPanel)
//            return;
//
//        Point parentLocation = c.getParent().getLocation();
//        xAdjustment = parentLocation.x - e.getX();
//        yAdjustment = parentLocation.y - e.getY();
//        chessPiece = (JLabel) c;
//        chessPiece.setLocation(e.getX() + xAdjustment, e.getY() + yAdjustment);
//        chessPiece.setSize(chessPiece.getWidth(), chessPiece.getHeight());
//        layeredPane.add(chessPiece, JLayeredPane.DRAG_LAYER);
    }

    //Move the chess piece around

    public void mouseDragged(MouseEvent me) {
//        if (chessPiece == null) return;
//        chessPiece.setLocation(me.getX() + xAdjustment, me.getY() + yAdjustment);
    }

    //Drop the chess piece back onto the chess board

    public void mouseReleased(MouseEvent e) {
//        if (chessPiece == null) return;
//
//        chessPiece.setVisible(false);
//        Component c = chessBoard.findComponentAt(e.getX(), e.getY());
//
//        if (c instanceof JLabel) {
//            Container parent = c.getParent();
//            parent.remove(0);
//            parent.add(chessPiece);
//        } else {
//            Container parent = (Container) c;
//            parent.add(chessPiece);
//        }
//
//        chessPiece.setVisible(true);
    }



    public void mouseMoved(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }
}
