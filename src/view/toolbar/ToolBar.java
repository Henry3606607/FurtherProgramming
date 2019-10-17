package view.toolbar;

import controller.*;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.AppFrame;

import javax.swing.*;
import java.awt.*;

public class ToolBar extends JToolBar {
    GameEngine gameEngine;
    private AppFrame appFrame;
    private JComboBox playerSelect;
    private JButton spinPlayer = new JButton("Spin Player");
    private JButton spinSpinnerButton;
    private JButton resetGame;
    private JPanel mainPanel;

    public ToolBar(GameEngine engine, AppFrame appFrame) {
        this.gameEngine = engine;
        this.appFrame = appFrame;
        renderToolbar();
    }

    public void renderToolbar() {
        this.clear();

        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(1, 5));

        playerSelect = new JComboBox();

        if (!gameEngine.getAllPlayers().isEmpty()) {
            for (Player player : gameEngine.getAllPlayers()) {
                playerSelect.addItem(player);

            }
        }

        playerSelect.addActionListener(new SelectPlayerController(appFrame, this));
        if (appFrame.getSelectedPlayer() == null) {
            playerSelect.setSelectedIndex(-1);
        }

        spinSpinnerButton = new JButton("Spin Spinner");
        spinSpinnerButton.addActionListener(new SpinSpinnerController(gameEngine, appFrame));

        resetGame = new JButton("Reset Game");
        resetGame.addActionListener(new ResetGameController(gameEngine, appFrame));

        spinPlayer.addActionListener(new SpinPlayerController(gameEngine, appFrame));
        spinPlayer.setEnabled(appFrame.getSelectedPlayer() != null);

        mainPanel.add(playerSelect);
        mainPanel.add(newPlayerButton());
        mainPanel.add(spinPlayer);
        mainPanel.add(spinSpinnerButton);
        mainPanel.add(resetGame);
        add(mainPanel);

        this.refresh();
    }

    public JButton newPlayerButton() {
        JButton addPlayerButton = new JButton("Add Player");
        addPlayerButton.addActionListener(new AddPlayerButtonController(gameEngine, appFrame));
        return addPlayerButton;
    }

    public void resizeToolbar(Dimension size){
        if(size.getWidth() < 640){
            mainPanel.setLayout(new GridLayout(2, 3));
        }
        else{
            mainPanel.setLayout(new GridLayout(1, 5));
        }
    }

    public void clear() {
        this.removeAll();
    }

    public void refresh() {
        this.revalidate();
        this.repaint();
    }

    public void spinnerSpinning(){
        spinSpinnerButton.setEnabled(false);
    }

    public void spinnerFinished(){
        spinSpinnerButton.setEnabled(true);
    }

    public JComboBox getPlayerSelect() {
        return playerSelect;
    }

    public void setPlayerSelect(JComboBox playerSelect) {
        this.playerSelect = playerSelect;
    }

    public void isSpinPlayerButtonDisabled(boolean isDisabled){
        this.spinPlayer.setEnabled(!isDisabled);
        this.refresh();
    }

    public void addNewPlayer(Player player){
        playerSelect.addItem(player);
        this.refresh();
    }

    public void autoSpin(){
        spinSpinnerButton.doClick();
    }

}
