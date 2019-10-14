package controller;

import model.interfaces.GameEngine;
import view.player.NewPlayerDialog;
import view.player.PlayerPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddPlayerButtonController implements ActionListener {
    private GameEngine gameEngine;
    private PlayerPanel playerPanel;

    public AddPlayerButtonController(GameEngine gameEngine, PlayerPanel playerPanel) {
        this.gameEngine = gameEngine;
        this.playerPanel = playerPanel;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        new NewPlayerDialog(playerPanel, gameEngine);
    }
}
