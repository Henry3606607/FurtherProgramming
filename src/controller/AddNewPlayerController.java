package controller;

import model.SimplePlayer;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.ErrorDialog;
import view.NewPlayerDialog;
import view.PlayerPanel;
import view.ToolBar;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AddNewPlayerController implements ActionListener {
    private GameEngine gameEngine;
    private NewPlayerDialog newPlayerDialog;
    private PlayerPanel playerPanel;
    private JDialog dialog;


    public AddNewPlayerController(GameEngine gameEngine, NewPlayerDialog newPlayerDialog, PlayerPanel playerPanel, JDialog dialog) {
        this.gameEngine = gameEngine;
        this.newPlayerDialog = newPlayerDialog;
        this.playerPanel = playerPanel;
        this.dialog = dialog;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ArrayList<String> errors = new ArrayList<>();
        if (newPlayerDialog.getName().getText().isBlank()) {
            errors.add("Name is required");
        }
        if (newPlayerDialog.getPoints().getText().isBlank()) {
            errors.add("Points is required");
        }
        if (errors.isEmpty()) {
            try {
                Player newPlayer = new SimplePlayer("1", newPlayerDialog.getName().getText(), Integer.valueOf(newPlayerDialog.getPoints().getText()));
                gameEngine.addPlayer(newPlayer);
                dialog.setVisible(false);
                playerPanel.addNewPlayer(newPlayer);
            } catch (NumberFormatException numberFormat) {
                errors.add("Bet must be of type Integer");
                ErrorDialog errorDialog = new ErrorDialog(playerPanel.getAppFrame(), errors);
            }
        } else {
            ErrorDialog errorDialog = new ErrorDialog(playerPanel.getAppFrame(), errors);
        }

    }
}
