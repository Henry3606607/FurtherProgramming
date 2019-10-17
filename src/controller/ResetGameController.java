package controller;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.AppFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

public class ResetGameController implements ActionListener {
    private GameEngine gameEngine;
    private AppFrame appFrame;


    public ResetGameController(GameEngine gameEngine, AppFrame appFrame) {
        this.gameEngine = gameEngine;
        this.appFrame = appFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (Player p : gameEngine.getAllPlayers()) {
            p.resetBet();
        }
        appFrame.resetGame();
    }


}
