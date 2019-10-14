package controller;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.player.PlayerView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CancelBetController implements ActionListener {
    private GameEngine gameEngine;
    private Player player;
    private PlayerView playerView;

    public CancelBetController(GameEngine gameEngine, Player player, PlayerView playerView) {
        this.gameEngine = gameEngine;
        this.player = player;
        this.playerView = playerView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gameEngine.getPlayer(player.getPlayerId()).resetBet();
        playerView.cancelBet();
    }
}
