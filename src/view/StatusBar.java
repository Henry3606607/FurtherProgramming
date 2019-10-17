package view;

import model.interfaces.Player;

import javax.swing.*;
import java.awt.*;

public class StatusBar extends CoinGamePanel {
    private JLabel status1;
    private JLabel status2;
    private JLabel status3;

    public StatusBar() {
        renderStatusBar();
    }

    public void renderStatusBar() {
        setLayout(new GridLayout(1, 3));
        status1 = new JLabel("Player Status: N/A");
        status2 = new JLabel("Points: N/A");
        status3 = new JLabel("Bet: N/A");
        add(status1);
        add(status2);
        add(status3);
        this.refresh();
    }


    public void switchCurrentPlayer(Player currentPlayer) {
        status1.setText("Player Status: " + currentPlayer.getPlayerName());
        status2.setText("Points: " + currentPlayer.getPoints());
        status3.setText("Bet: " + currentPlayer.getBet());
        this.refresh();
    }

    public void betPlaced(Player player){
        status3.setText("Bet: " + player.getBet());
        this.refresh();
    }


    public void clear() {
        this.removeAll();
    }

    public void refresh() {
        this.revalidate();
        this.repaint();
    }

}
