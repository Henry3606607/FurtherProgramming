package view;

import model.enumeration.BetType;
import model.interfaces.GameEngine;
import model.interfaces.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayerBetView extends JPanel {
    private Player player;
    GameEngine gameEngine;
    PlayerView parent;

    public PlayerBetView(Player player, GameEngine gameEngine, PlayerView parent){
        this.player = player;
        this.gameEngine = gameEngine;
        this.parent = parent;

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;


        if(player.getBet() == 0){
            JTextField betText = new JTextField(10);
            JLabel placeBetlabel = new JLabel("Make bet: ");
            add(placeBetlabel);
            add(betText);

            String[] petStrings = {BetType.NO_BET.toString(),
                    BetType.BOTH.toString(),
                    BetType.COIN1.toString(),
                    BetType.COIN2.toString()};

            //Create the combo box, select item at index 4.
            //Indices start at 0, so 4 specifies the pig.
            JComboBox betTypeCombo = new JComboBox(petStrings);
            betTypeCombo.setSelectedIndex(0);
            add(betTypeCombo);

            JButton placeBetButton = new JButton("Place new bet");
            placeBetButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    gameEngine.placeBet(player, Integer.valueOf(betText.getText()), BetType.valueOf((String) betTypeCombo.getSelectedItem()));
                    parent.renderPlayer();
                }
            });

            add(placeBetButton);
        }
        else{
            JLabel currentBet = new JLabel(Integer.toString(player.getBet()));
            JLabel currentBetLabel = new JLabel("Current Bet: ");
            add(currentBetLabel);
            add(currentBet, gbc);

            JLabel betType = new JLabel(player.getBetType().toString());
            JLabel betTypeLabel = new JLabel("Bet Type: ");
            add(betTypeLabel);
            add(betType, gbc);

            JButton cancelBetButton = new JButton("Cancel bet");
            cancelBetButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    gameEngine.getPlayer(player.getPlayerId()).resetBet();
                    parent.renderPlayer();
                }
            });

            add(cancelBetButton);
        }
    }
}
