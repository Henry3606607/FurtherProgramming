package controller;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.AppFrame;
import view.summary.SpinnerView;
import view.summary.SummaryPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

public class SpinSpinnerController implements ActionListener {
    private GameEngine gameEngine;
    private Collection<Player> players;
    private AppFrame appFrame;


    public SpinSpinnerController(GameEngine gameEngine, AppFrame appFrame) {
        this.gameEngine = gameEngine;
        this.players = gameEngine.getAllPlayers();
        this.appFrame = appFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (checkBets()) {
            if (checkPlayerSpins()) {
                new Thread() {
                    @Override
                    public void run() {
                        gameEngine.spinSpinner(100, 1000, 100, 50, 500, 50);
                    }
                }.start();
                appFrame.spinnerSpinning();

            }
        }
    }

    public boolean checkBets() {
        int input;
        for (Player player : players) {
            if (player.getBet() <= 0) {
                input = JOptionPane.showConfirmDialog(null,
                        "Some players haven't placed a bet, are you sure you want to spin?.", "Unplaced Bets!",
                        JOptionPane.YES_NO_OPTION);
                if (input == JOptionPane.NO_OPTION) {
                    return false;
                } else if (input == JOptionPane.YES_OPTION) {
                    return true;
                }
            }
        }
        return true;
    }

    public boolean checkPlayerSpins() {
        for (Player player : players) {
            if (player.getResult() == null && player.getBet() > 0) {
                JOptionPane.showMessageDialog(null, "Not all players have spun",
                        "Missing spins", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        return true;
    }
}
