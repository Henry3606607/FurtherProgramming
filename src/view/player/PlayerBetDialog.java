package view;

import controller.PlaceBetController;
import model.enumeration.BetType;
import model.interfaces.GameEngine;
import model.interfaces.Player;

import javax.swing.*;
import java.awt.*;

public class PlayerBetDialog {
    private Player player;
    private GameEngine gameEngine;
    private PlayerPanel playerPanel;
    private JDialog dialog;
    private JPanel panel;
    private JTextField betText;
    private JComboBox betTypeCombo;

    public PlayerBetDialog(PlayerPanel playerPanel, Player player, GameEngine gameEngine){
        this.player = player;
        this.gameEngine = gameEngine;
        this.playerPanel = playerPanel;

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;


        render();
    }

    public void render(){
        dialog = new JDialog(playerPanel.getAppFrame(), "Create Bet");
        this.createPanel();
        dialog.setSize(1000, 200);
        dialog.add(panel);
        dialog.setVisible(true);
    }

    public void createPanel(){
        panel = new JPanel();

        betText = new JTextField(10);
        JLabel placeBetlabel = new JLabel("Make bet: ");
        panel.add(placeBetlabel);
        panel.add(betText);

        String[] betTypes = {
                BetType.BOTH.toString(),
                BetType.COIN1.toString(),
                BetType.COIN2.toString()};

        //Create the combo box, select item at index 4.
        //Indices start at 0, so 4 specifies the pig.
        betTypeCombo = new JComboBox(betTypes);
        betTypeCombo.setSelectedIndex(0);
        panel.add(betTypeCombo);

        JButton placeBetButton = new JButton("Place new bet");
        placeBetButton.addActionListener(new PlaceBetController(gameEngine, this, dialog, playerPanel.getAppFrame()));

        panel.add(placeBetButton);
    }

    public JTextField getBetText() {
        return betText;
    }

    public void setBetText(JTextField betText) {
        this.betText = betText;
    }

    public JComboBox getBetTypeCombo() {
        return betTypeCombo;
    }

    public void setBetTypeCombo(JComboBox betTypeCombo) {
        this.betTypeCombo = betTypeCombo;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
