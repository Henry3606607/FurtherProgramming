package controller;

import model.SimplePlayer;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.AppFrame;
import view.ErrorDialog;
import view.player.NewPlayerDialog;
import view.player.PlayerPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AddNewPlayerController implements ActionListener {
    private GameEngine gameEngine;
    private NewPlayerDialog newPlayerDialog;
    private JDialog dialog;
    private AppFrame appframe;


    public AddNewPlayerController(GameEngine gameEngine, NewPlayerDialog newPlayerDialog, AppFrame appframe, JDialog dialog) {
        this.gameEngine = gameEngine;
        this.newPlayerDialog = newPlayerDialog;
        this.appframe = appframe;
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
                Player newPlayer = new SimplePlayer(Integer.toString(appframe.getNextIdValue()), newPlayerDialog.getName().getText(), Integer.valueOf(newPlayerDialog.getPoints().getText()));
                gameEngine.addPlayer(newPlayer);
                dialog.setVisible(false);
                appframe.getPlayerPanel().addNewPlayer(newPlayer);
            } catch (NumberFormatException numberFormat) {
                errors.add("Points must be of type Integer");
                ErrorDialog errorDialog = new ErrorDialog(appframe.getPlayerPanel().getAppFrame(), errors);
            }
        } else {
            ErrorDialog errorDialog = new ErrorDialog(appframe.getPlayerPanel().getAppFrame(), errors);
        }

    }
}
