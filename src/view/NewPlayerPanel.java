package view;

import model.SimplePlayer;
import model.interfaces.GameEngine;
import model.interfaces.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewPlayerPanel extends JPanel {

    private PlayerPanel parent;
    private GameEngine gameEngine;

    public NewPlayerPanel(PlayerPanel parent, GameEngine gameEngine) {
        this.parent = parent;
        this.gameEngine = gameEngine;
        setBorder(BorderFactory.createTitledBorder("New Player"));

        JTextField name = new JTextField(10);
        JLabel nameLabel = new JLabel("Name: ");
        add(nameLabel);
        add(name);

        JTextField points = new JTextField(10);
        JLabel pointsLabel = new JLabel("Points: ");
        add(pointsLabel);
        add(points);

        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        JButton createPlayer = new JButton("Add");
        createPlayer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(e);
                Player newPlayer = new SimplePlayer("1", name.getText(), Integer.valueOf(points.getText()));
                gameEngine.addPlayer(newPlayer);
                parent.renderPlayers();
            }
        });


        add(createPlayer);
    }
}
