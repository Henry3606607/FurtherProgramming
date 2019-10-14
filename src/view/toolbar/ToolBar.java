package view.toolbar;

import controller.AddPlayerButtonController;
import controller.SelectPlayerController;
import controller.SpinPlayerController;
import controller.SpinSpinnerController;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.AppFrame;

import javax.swing.*;

public class ToolBar extends JToolBar {
    GameEngine gameEngine;
    private AppFrame appFrame;
    private JComboBox playerSelect;
    private JButton spinPlayer = new JButton("Spin Player");
    private JButton spinSpinnerButton;

    public ToolBar(GameEngine engine, AppFrame appFrame) {
        this.gameEngine = engine;
        this.appFrame = appFrame;
        renderToolbar();
    }

    public void renderToolbar() {
        this.clear();

        JPanel p = new JPanel();

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


        add(playerSelect);
        add(newPlayerButton());


        spinSpinnerButton = new JButton("Spin Spinner");
        spinSpinnerButton.addActionListener(new SpinSpinnerController(gameEngine, appFrame.getSpinnerView(), appFrame.getSummaryPanel()));

        spinPlayer.addActionListener(new SpinPlayerController(gameEngine, appFrame));
        spinPlayer.setEnabled(appFrame.getSelectedPlayer() != null);

        p.add(spinPlayer);
        p.add(spinSpinnerButton);
        add(p);

        this.refresh();
    }

    public JButton newPlayerButton() {
        JButton addPlayerButton = new JButton("Add Player");
        addPlayerButton.addActionListener(new AddPlayerButtonController(gameEngine, appFrame.getPlayerPanel()));
        return addPlayerButton;
    }

    public void clear() {
        this.removeAll();
    }

    public void refresh() {
        this.revalidate();
        this.repaint();
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
