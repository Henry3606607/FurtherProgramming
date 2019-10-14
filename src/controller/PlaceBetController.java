package controller;

import model.enumeration.BetType;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.AppFrame;
import view.ErrorDialog;
import view.player.PlayerBetDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PlaceBetController implements ActionListener {
    private GameEngine gameEngine;
    private PlayerBetDialog playerBetDialog;
    private JDialog dialog;
    private AppFrame appFrame;
    private ArrayList<String> errors = new ArrayList<>();

    public PlaceBetController(GameEngine gameEngine, PlayerBetDialog playerBetDialog, JDialog dialog, AppFrame appFrame) {
        this.gameEngine = gameEngine;
        this.playerBetDialog = playerBetDialog;
        this.dialog = dialog;
        this.appFrame = appFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Player player = playerBetDialog.getPlayer();
        if (isBetValid(player, playerBetDialog.getBetText().getText(), BetType.valueOf((String) playerBetDialog.getBetTypeCombo().getSelectedItem()))) {
            gameEngine.placeBet(player, Integer.valueOf(playerBetDialog.getBetText().getText()), BetType.valueOf((String) playerBetDialog.getBetTypeCombo().getSelectedItem()));
            dialog.setVisible(false);
            appFrame.newBetPlaced(player);
        } else {
            new ErrorDialog(appFrame, errors);
        }
    }

        public boolean isBetValid (Player player, String betText, BetType type){
            if (!betText.isBlank()) {
                int bet;
                try {
                    bet = Integer.valueOf(betText);
                    if (bet > 0) {
                        if (player.getPoints() >= bet) {
                            return true;
                        } else {
                            errors.add("Player does not have enough points");
                        }
                    } else {
                        errors.add("Bet must be greater then 0");
                    }
                } catch (NumberFormatException numberFormat) {
                    errors.add("Bet must be of type Integer");
                }
            }
            else{
                errors.add("Bet amount is required");
            }
            return false;
        }
    }
