package view;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.interfaces.GameEngineCallback;

import javax.swing.*;
import java.awt.*;

public class SummaryPanel extends JPanel {
    GameEngine gameEngine;
    private GameEngineCallback callback;
    private AppFrame parent;

    public SummaryPanel(GameEngine engine, AppFrame parent){
        this.gameEngine = engine;
        this.parent = parent;
        render();
    }

    public void render(){
        this.clear();

        setBorder(BorderFactory.createTitledBorder("Summary Panel"));

        JPanel listPane = new JPanel();
        listPane.setLayout(new BoxLayout(listPane, BoxLayout.PAGE_AXIS));

        listPane.add(Box.createRigidArea(new Dimension(0,5)));
        listPane.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        JPanel summaryScroll = new JPanel();
        summaryScroll.setLayout(new BoxLayout(summaryScroll, BoxLayout.LINE_AXIS));
        summaryScroll.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        summaryScroll.add(Box.createHorizontalGlue());
        summaryScroll.add(Box.createRigidArea(new Dimension(10, 0)));

        JLabel label = new JLabel("Players:");
        listPane.add(label);
        for (Player player : gameEngine.getAllPlayers()) {
            JLabel playerLabel = new JLabel("player "+player.getPlayerId() + ": " + player.getPlayerName());
            JLabel currentBets = new JLabel("bet: "+player.getBetType() + " " + player.getBet());
            JLabel status = new JLabel("result: "+player.getResult() + " " + (player.getPoints() - player.getBet()));
            listPane.add(playerLabel);
            listPane.add(currentBets);
            listPane.add(status);
        }


        this.add(listPane, BorderLayout.CENTER);
        this.add(summaryScroll, BorderLayout.PAGE_END);




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
