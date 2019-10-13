package controller;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.PlayerPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

public class PlayerLeaveController implements ActionListener {
    private GameEngine gameEngine;
    private Player player;
    private PlayerPanel playerPanel;


    public PlayerLeaveController(GameEngine gameEngine, Player player, PlayerPanel playerPanel) {
        this.gameEngine = gameEngine;
        this.player = player;
        this.playerPanel = playerPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gameEngine.removePlayer(player);
        playerPanel.removePlayer(player);
    }
}
