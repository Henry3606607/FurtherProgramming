package view;

import controller.CancelBetController;
import controller.PlayerLeaveController;
import model.interfaces.GameEngine;
import model.interfaces.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayerView extends JPanel {
    private Player player;
    GameEngine gameEngine;
    PlayerPanel playerPanel;

    public PlayerView(Player player, GameEngine gameEngine, PlayerPanel playerPanel){
        this.player = player;
        this.gameEngine = gameEngine;
        this.playerPanel = playerPanel;

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




        JButton removePlayerButton = new JButton("Leave");
        removePlayerButton.addActionListener(new PlayerLeaveController(gameEngine, this.player, playerPanel));

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
            cancelBetButton.addActionListener(new CancelBetController(gameEngine, player, this));

            add(cancelBetButton);
        }
        else{
            JButton createBetButton = new JButton("Add Bet");
            createBetButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    PlayerBetDialog playerBetView = new PlayerBetDialog(playerPanel, player, gameEngine);
                }
            });
            add(createBetButton);
        }

    }

    public void cancelBet(){
        playerPanel.getSummaryPanel().cancelBet(player);
        this.renderPlayer();
    }

    public void clear(){
        this.removeAll();
    }

    public void refresh(){
        this.revalidate();
        this.repaint();
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
