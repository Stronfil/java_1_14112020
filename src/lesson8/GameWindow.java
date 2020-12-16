package lesson8;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {
    static final int WINDOW_X = 600;
    static final int WINDOW_Y = 400;
    static final int WINDOW_WIDTH = 505;
    static final int WINDOW_HEIGHT = 555;

    private SettingWindow settingWindow;
    private BattleMap battleMap;

    public GameWindow() {
        setBounds(WINDOW_X, WINDOW_Y, WINDOW_WIDTH, WINDOW_HEIGHT);
        setTitle("TicTacToe");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        settingWindow = new SettingWindow(this);
        battleMap = new BattleMap(this);
        add(battleMap, BorderLayout.CENTER);

        JPanel panel = new JPanel(new GridLayout(1, 2));
        JButton btnStartNewGame = new JButton("Start New Game");
        panel.add(btnStartNewGame);
        JButton btnExit = new JButton("Exit");
        panel.add(btnExit);
        add(panel, BorderLayout.SOUTH);

        btnStartNewGame.addActionListener(e -> {
            settingWindow.setVisible(true);
        });

        btnExit.addActionListener(e -> {
            System.exit(0);
        });

        setVisible(true);
    }

    public void startNewGame(int mode, int fieldSize, int winningLength){
        battleMap.startNewGame(mode, fieldSize, winningLength);
    }
}
