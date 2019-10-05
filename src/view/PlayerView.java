package view;

import model.interfaces.GameEngine;
import model.interfaces.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayerView extends JPanel {
    private Player player;
    GameEngine gameEngine;
    PlayerPanel parent;

    public PlayerView(Player player, GameEngine gameEngine, PlayerPanel parent){
        this.player = player;
        this.gameEngine = gameEngine;
        this.parent = parent;

        renderPlayer();
    }

    public void renderPlayer(){
        this.clear();

        JPanel listPane = new JPanel();
        listPane.setLayout(new BoxLayout(listPane, BoxLayout.PAGE_AXIS));

        listPane.add(Box.createRigidArea(new Dimension(0,5)));
        listPane.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));

        setBorder(BorderFactory.createTitledBorder(player.getPlayerName()));

        JLabel points = new JLabel(Integer.toString(player.getPoints()));
        JLabel pointsLabel = new JLabel("Points: ");
        add(pointsLabel);
        add(points);

        PlayerBetView playerBetView = new PlayerBetView(player, gameEngine, this);
        listPane.add(playerBetView);

        JButton removePlayerButton = new JButton("Delete Player");
        removePlayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameEngine.removePlayer(player);
                parent.renderPlayers();
            }
        });

        listPane.add(removePlayerButton);

        this.add(listPane, BorderLayout.CENTER);
        this.refresh();
    }

    public void clear(){
        this.removeAll();
    }

    public void refresh(){
        this.revalidate();
        this.repaint();
    }
}
