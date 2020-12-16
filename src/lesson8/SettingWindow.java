package lesson8;

import javax.swing.*;
import java.awt.*;

public class SettingWindow extends JFrame {
    private static final int WINDOW_X = GameWindow.WINDOW_X + 50;
    private static final int WINDOW_Y = GameWindow.WINDOW_Y + 50;
    private static final int WINDOW_WIDTH = GameWindow.WINDOW_WIDTH - 100;
    private static final int WINDOW_HEIGHT = 400;

    private final int MIN_FIELD_SIZE = 3;
    private final int MAX_FIELD_SIZE = 10;

    private GameWindow gameWindow;

    private JRadioButton rbHumVsAi;
    private JRadioButton rbHumVsHum;
    private ButtonGroup gameMode;

    private JSlider slFieldSize;
    private JSlider slWinnigLength;

    public SettingWindow(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
        setBounds(WINDOW_X, WINDOW_Y, WINDOW_WIDTH, WINDOW_HEIGHT);
        setTitle("TicTacToe setting");
        setLayout(new GridLayout(8, 1));

        add(new JLabel("Choose game mode:"));

        rbHumVsAi = new JRadioButton("HumVsAi", true);
        add(rbHumVsAi);
        rbHumVsHum = new JRadioButton("HumVsHum");
        rbHumVsHum.setEnabled(false);
        add(rbHumVsHum);
        gameMode = new ButtonGroup();
        gameMode.add(rbHumVsAi);
        gameMode.add(rbHumVsHum);

        add(new JLabel("Choose size:"));
        slFieldSize = new JSlider(MIN_FIELD_SIZE, MAX_FIELD_SIZE, MIN_FIELD_SIZE);
        slFieldSize.setMajorTickSpacing(1);
        slFieldSize.setPaintTicks(true);
        slFieldSize.setPaintLabels(true);
        add(slFieldSize);

        add(new JLabel("Choose winning length:"));
        slWinnigLength = new JSlider(MIN_FIELD_SIZE, MIN_FIELD_SIZE, MIN_FIELD_SIZE);
        slWinnigLength.setMajorTickSpacing(1);
        slWinnigLength.setPaintTicks(true);
        slWinnigLength.setPaintLabels(true);
        add(slWinnigLength);

        slFieldSize.addChangeListener(e -> {
            slWinnigLength.setMaximum(slFieldSize.getValue());
        });

        JButton btnStartAGame = new JButton("Start a game");
        add(btnStartAGame);
        btnStartAGame.addActionListener(e -> {
            int mode;
            if (rbHumVsAi.isSelected()) {
                mode = BattleMap.MODE_H_VS_AI;
            } else {
                mode = BattleMap.MODE_H_VS_H;
            }

            int fieldSize = slFieldSize.getValue();
            int winningLength = slWinnigLength.getValue();

            Logic.SIZE = fieldSize;
            Logic.DOTS_TO_WIN = winningLength;
            Logic.initMap();
            Logic.gameFinished = false;

            gameWindow.startNewGame(mode, fieldSize, winningLength);

            setVisible(false);
        });


        setVisible(false);
    }
}
