package view;

import model.interfaces.GameEngine;
import model.interfaces.Player;

import javax.swing.*;
import java.util.ArrayList;

public class PlayerPanel extends JPanel {
    private GameEngine gameEngine;
    private AppFrame parent;
    private SummaryPanel summaryPanel;
    private ArrayList<PlayerView> playerViews = new ArrayList<>();

    public PlayerPanel(GameEngine engine, AppFrame parent, SummaryPanel summaryPanel){
        this.gameEngine = engine;
        this.parent = parent;
        this.summaryPanel = summaryPanel;
        renderPlayers();
    }

    public void renderPlayers(){
        this.clear();

        setBorder(BorderFactory.createTitledBorder("Players"));

        createAllPlayers();
        this.refresh();
    }

    public void createAllPlayers(){
        for (Player p : gameEngine.getAllPlayers()) {
            PlayerView pView = new PlayerView(p, gameEngine, this);
            playerViews.add(pView);
            add(pView);
        }
    }

    public void createNewPlayer(){
        new NewPlayerDialog(this, gameEngine);
        this.refresh();
    }


    public void removePlayer(Player player){
        summaryPanel.playerLeft(player);
        this.renderPlayers();
    }

    public void addNewPlayer(Player player){
        PlayerView pView = new PlayerView(player, gameEngine, this);
        playerViews.add(pView);
        add(pView);
        getAppFrame().newPlayerAdded(player);
        summaryPanel.playerJoined(player);
        this.refresh();
    }

    public void clear(){
        this.removeAll();
    }

    public void refresh(){
        this.revalidate();
        this.repaint();
    }

    public AppFrame getAppFrame(){
        return this.parent;
    }

    public void addNewBet(Player player){
        for (PlayerView p : playerViews) {
            if(p.getPlayer().getPlayerId().equals(player.getPlayerId())){
                p.renderPlayer();
            }
        }

        summaryPanel.printBetInformation(player);
    }

    public SummaryPanel getSummaryPanel() {
        return summaryPanel;
    }

    public void setSummaryPanel(SummaryPanel summaryPanel) {
        this.summaryPanel = summaryPanel;
    }
}
