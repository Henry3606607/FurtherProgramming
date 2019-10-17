package controller;

import model.interfaces.GameEngine;
import view.AppFrame;
import view.player.NewPlayerDialog;
import view.player.PlayerPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddPlayerButtonController implements ActionListener {
    private GameEngine gameEngine;
    private AppFrame appFrame;

    public AddPlayerButtonController(GameEngine gameEngine, AppFrame appFrame) {
        this.gameEngine = gameEngine;
        this.appFrame = appFrame;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        new NewPlayerDialog(appFrame, gameEngine);
    }
}
