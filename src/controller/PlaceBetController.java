package controller;

import model.enumeration.BetType;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.ErrorDialog;
import view.PlayerBetDialog;
import view.PlayerPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PlaceBetController implements ActionListener {
    private GameEngine gameEngine;
    private PlayerBetDialog playerBetDialog;
    private JDialog dialog;
    private PlayerPanel playerPanel;

    public PlaceBetController(GameEngine gameEngine, PlayerBetDialog playerBetDialog, JDialog dialog, PlayerPanel playerPanel) {
        this.gameEngine = gameEngine;
        this.playerBetDialog = playerBetDialog;
        this.dialog = dialog;
        this.playerPanel = playerPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ArrayList<String> errors = new ArrayList<>();
        if (playerBetDialog.getBetText().getText().isBlank()) {
            errors.add("bet value is required");
        }
        if (playerBetDialog.getBetTypeCombo().getSelectedItem() == null) {
            errors.add("Bet type is required");
        }
        if (errors.isEmpty()) {
            try {
                //TODO check if enough points


                gameEngine.placeBet(playerBetDialog.getPlayer(), Integer.valueOf(playerBetDialog.getBetText().getText()), BetType.valueOf((String) playerBetDialog.getBetTypeCombo().getSelectedItem()));
                dialog.setVisible(false);
                playerPanel.addNewBet(playerBetDialog.getPlayer());
            } catch (NumberFormatException numberFormat) {
                errors.add("Bet must be of type Integer");
                ErrorDialog errorDialog = new ErrorDialog(playerPanel.getAppFrame(), errors);
            }
        } else {
            ErrorDialog errorDialog = new ErrorDialog(playerPanel.getAppFrame(), errors);
        }
    }
}
