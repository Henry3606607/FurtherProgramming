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
    AppFrame mainFrame;

    public PlayerView(Player player, GameEngine gameEngine, PlayerPanel parent, AppFrame mainFrame){
        this.player = player;
        this.gameEngine = gameEngine;
        this.parent = parent;
        this.mainFrame = mainFrame;

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




        JButton removePlayerButton = new JButton("Delete Player");
        removePlayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameEngine.removePlayer(player);
                parent.renderPlayers();
            }
        });
        this.createBetItems();
        listPane.add(removePlayerButton);

        this.add(listPane, BorderLayout.CENTER);
        this.refresh();
    }

    public void createBetItems(){
        if(player.getBet() != 0){
            JLabel currentBet = new JLabel(Integer.toString(player.getBet()));
            JLabel currentBetLabel = new JLabel("Current Bet: ");
            add(currentBetLabel);
            add(currentBet);

            JLabel betType = new JLabel(player.getBetType().toString());
            JLabel betTypeLabel = new JLabel("Bet Type: ");
            add(betTypeLabel);
            add(betType);

            JButton cancelBetButton = new JButton("Cancel bet");
            cancelBetButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    gameEngine.getPlayer(player.getPlayerId()).resetBet();
                    //parent.renderPlayer();
                }
            });

            add(cancelBetButton);
        }
        else{
            JButton createBetButton = new JButton("Add Bet");
            createBetButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    PlayerBetDialog playerBetView = new PlayerBetDialog(mainFrame, player, gameEngine);
                }
            });
            add(createBetButton);
        }

    }

    public void clear(){
        this.removeAll();
    }

    public void refresh(){
        this.revalidate();
        this.repaint();
    }
}
