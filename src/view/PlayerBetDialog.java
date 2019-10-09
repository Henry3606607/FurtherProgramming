package view;

import model.enumeration.BetType;
import model.interfaces.GameEngine;
import model.interfaces.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PlayerBetDialog {
    private Player player;
    GameEngine gameEngine;
    AppFrame parent;
    private JDialog dialog;
    private JPanel panel;

    public PlayerBetDialog(AppFrame parent, Player player, GameEngine gameEngine){
        this.player = player;
        this.gameEngine = gameEngine;
        this.parent = parent;

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;


        render();
    }

    public void render(){
        dialog = new JDialog(parent, "Create Bet");
        this.createPanel();
        dialog.setSize(1000, 200);
        dialog.add(panel);
        dialog.setVisible(true);
    }

    public void createPanel(){
        panel = new JPanel();

        JTextField betText = new JTextField(10);
        JLabel placeBetlabel = new JLabel("Make bet: ");
        panel.add(placeBetlabel);
        panel.add(betText);

        String[] betTypes = {BetType.NO_BET.toString(),
                BetType.BOTH.toString(),
                BetType.COIN1.toString(),
                BetType.COIN2.toString()};

        //Create the combo box, select item at index 4.
        //Indices start at 0, so 4 specifies the pig.
        JComboBox betTypeCombo = new JComboBox(betTypes);
        betTypeCombo.setSelectedIndex(0);
        panel.add(betTypeCombo);

        JButton placeBetButton = new JButton("Place new bet");
        placeBetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<String> errors = new ArrayList<>();
                if (betText.getText().isBlank()) {
                    errors.add("bet value is required");
                }
                if (betTypeCombo.getSelectedItem() == null) {
                    errors.add("Bet type is required");
                }
                if (errors.isEmpty()) {
                    try {
                        gameEngine.placeBet(player, Integer.valueOf(betText.getText()), BetType.valueOf((String) betTypeCombo.getSelectedItem()));
                        dialog.setVisible(false);
                        parent.render();
                    } catch (NumberFormatException numberFormat) {
                        errors.add("Bet must be of type Integer");
                        ErrorDialog errorDialog = new ErrorDialog(parent, errors);
                    }
                } else {
                    ErrorDialog errorDialog = new ErrorDialog(parent, errors);
                }
            }
        });

        panel.add(placeBetButton);
    }
}
