package view;

import model.interfaces.GameEngine;
import model.interfaces.Player;

import javax.swing.*;
import java.awt.*;

public class PlayerPanel extends JPanel {
    GameEngine gameEngine;
    private AppFrame parent;

    public PlayerPanel(GameEngine engine, AppFrame parent){
        this.gameEngine = engine;
        this.parent = parent;
        renderPlayers();
    }

    public void renderPlayers(){
        this.clear();

        setBorder(BorderFactory.createTitledBorder("Players"));

        JPanel listPane = new JPanel();
        listPane.setLayout(new BoxLayout(listPane, BoxLayout.PAGE_AXIS));

        listPane.add(Box.createRigidArea(new Dimension(0,5)));
        listPane.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        JPanel playerPaneScroll = new JPanel();
        playerPaneScroll.setLayout(new BoxLayout(playerPaneScroll, BoxLayout.LINE_AXIS));
        playerPaneScroll.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        playerPaneScroll.add(Box.createHorizontalGlue());
        playerPaneScroll.add(Box.createRigidArea(new Dimension(10, 0)));

        listPane.add(newPlayerButton());

        for (Player p : gameEngine.getAllPlayers()) {
            PlayerView pView = new PlayerView(p, gameEngine, this, parent);
            listPane.add(pView);
        }

        this.add(listPane, BorderLayout.CENTER);
        this.add(playerPaneScroll, BorderLayout.PAGE_END);
        this.refresh();
    }

    public JButton newPlayerButton(){
        JButton addPlayerButton = new AddPlayerButton("Add Player", this);
        return addPlayerButton;
    }

    public void createNewPlayer(){
        NewPlayerDialog newPlayer = new NewPlayerDialog(parent, gameEngine);
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
