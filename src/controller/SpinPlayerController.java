package controller;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.AppFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SpinPlayerController implements ActionListener {
    private GameEngine gameEngine;
    private AppFrame appFrame;

    public SpinPlayerController(GameEngine gameEngine, AppFrame appFrame) {
        this.gameEngine = gameEngine;
        this.appFrame = appFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (hasPlayerBet()) {
            new Thread() {
                @Override
                public void run() {
                    gameEngine.spinPlayer(appFrame.getSelectedPlayer(), 100, 1000, 100, 50, 500, 50);
                }
            }.start();
        }
    }

    public boolean hasPlayerBet() {
        if (appFrame.getSelectedPlayer().getBet() > 0) {
            return true;
        }
        JOptionPane.showMessageDialog(null, "Player has not placed a bet",
                "no bet", JOptionPane.ERROR_MESSAGE);
        return false;
    }
}
