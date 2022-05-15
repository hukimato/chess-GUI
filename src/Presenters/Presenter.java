package Presenters;

import Models.*;
import Models.Color;
import Views.ChangePawnStrategy;
import Views.DefaultStrategy;
import Views.LostStrategy;
import Views.Strategy;

import javax.swing.*;
import java.awt.*;

public class Presenter {
    private Board board;
    private Cell[][] cells;

    public Presenter(){
        board = new Board();
    }


    // Методы для работы с Моделью доски
    private Cell curCell;
    private JLabel curPiece;
    public void getMovableCells(JPanel chessBoard, JLabel chessPiece){
        cells = board.getCells();
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                JPanel panel = (JPanel) chessBoard.getComponent(63 - (i*8+j));
                JLabel piece;
                try {
                    piece = (JLabel) panel.getComponent(0);
                } catch (ArrayIndexOutOfBoundsException e) {
                    piece = null;
                }
                if (piece == chessPiece) {
                    curCell = cells[i][7-j];
                    curPiece = chessPiece;
                    board.getMovableCells(curCell);
                    return;
                }
            }
        }
        System.out.println("Первый клик");
    }

    public void moveFigure(JPanel chessBoard, Component c){
        // Сброс выбора фигуры для хода
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                JPanel panel = (JPanel) chessBoard.getComponent(63 - (i * 8 + j));
                JLabel piece;
                try {
                    piece = (JLabel) panel.getComponent(0);
                } catch (ArrayIndexOutOfBoundsException e) {
                    piece = null;
                }
                if (piece != null && c == piece) {
                    board.moveFigure(curCell, cells[i][7 - j]);
                    return;
                }
                if (piece == null && c == panel){
                    board.moveFigure(curCell, cells[i][7 - j]);
                    return;
                }
            }
        }
    }

    public Strategy checkStrategy(){
        if (board.getState() instanceof LoseState) {
            return new LostStrategy(board.getState().handle().toString());
        }
        if (board.getState() instanceof PawnChangeState) {
            return new ChangePawnStrategy(board.getState().handle().toString());
        }
        return new DefaultStrategy();
    }

    public void restoreBoard(){
        board.restore();
    }


    public JPanel reloadBoard(){
        cells = board.getCells();
        JPanel chessBoard = createChessBoard();
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                if (cells[i][7-j].getFigure() != null){
                    JLabel piece = new JLabel(getFigureImg(cells[i][7-j].getFigure()));
                    JPanel panel = (JPanel) chessBoard.getComponent(63 - (i*8+j));
                    panel.add(piece);
                }
                if (cells[i][7-j].getIsMovable() && cells[i][7-j].getFigure() == null){
                    JLabel piece = new JLabel(new ImageIcon("D:/Chess/img/movable.png"));
                    JPanel panel = (JPanel) chessBoard.getComponent(63 - (i*8+j));
                    panel.add(piece);
                }
                if (cells[i][7-j].getIsMovable() && cells[i][7-j].getFigure() != null){
                    JPanel panel = (JPanel) chessBoard.getComponent(63 - (i*8+j));
                    panel.setBackground(new java.awt.Color(244,78,66));
                }
            }
        }

        return chessBoard;
    }

    public void clearIsMovable(){
        board.clearIsMovable();
    }

    public void changePawn(int number, int letter, String figure){
        board.setPawn(number, letter, figure);
    }

    public void restart(){
        board = new Board();
    }

    private ImageIcon getFigureImg(Figure figure){
        if (figure.getColor() == Color.WHITE){
            if (figure instanceof Pawn) {
                return new ImageIcon("D:/Chess/img/pawnW.png");
            }
            if (figure instanceof Rook) {
                return new ImageIcon("D:/Chess/img/rookW.png");
            }
            if (figure instanceof Knight) {
                return new ImageIcon("D:/Chess/img/horseW.png");
            }
            if (figure instanceof Bishop) {
                return new ImageIcon("D:/Chess/img/bishopW.png");
            }
            if (figure instanceof Queen) {
                return new ImageIcon("D:/Chess/img/queenW.png");
            }
            if (figure instanceof King) {
                return new ImageIcon("D:/Chess/img/kingW.png");
            }
        } else {
            if (figure instanceof Pawn) {
                return new ImageIcon("D:/Chess/img/pawnB.png");
            }
            if (figure instanceof Rook) {
                return new ImageIcon("D:/Chess/img/rookB.png");
            }
            if (figure instanceof Knight) {
                return new ImageIcon("D:/Chess/img/horseB.png");
            }
            if (figure instanceof Bishop) {
                return new ImageIcon("D:/Chess/img/bishopB.png");
            }
            if (figure instanceof Queen) {
                return new ImageIcon("D:/Chess/img/queenB.png");
            }
            if (figure instanceof King) {
                return new ImageIcon("D:/Chess/img/kingB.png");
            }
        }
        return null;
    }

    private JPanel createChessBoard (){
        Dimension boardSize = new Dimension(600, 600);

        JPanel chessBoard = new JPanel();
        chessBoard.setLayout(new GridLayout(8, 8));
        chessBoard.setPreferredSize(boardSize);
        chessBoard.setBounds(0, 0, boardSize.width, boardSize.height);

        for (int i = 0; i < 64; i++) {
            JPanel square = new JPanel(new BorderLayout());
            chessBoard.add(square);
            int row = (i / 8) % 2;
            if (row == 0)
                square.setBackground(i % 2 == 0 ? new java.awt.Color(79,76,76) : new java.awt.Color(240,240,240));
            else
                square.setBackground(i % 2 == 0 ? new java.awt.Color(240,240,240) : new java.awt.Color(79,76,76));
        }

        return chessBoard;
    }
}
