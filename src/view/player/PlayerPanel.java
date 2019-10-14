package view;

import model.interfaces.GameEngine;
import model.interfaces.Player;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PlayerPanel extends JPanel {
    private GameEngine gameEngine;
    private AppFrame parent;
    private SummaryPanel summaryPanel;
    private ArrayList<PlayerView> playerViews = new ArrayList<>();
    private JPanel allPlayersPanel;

    public PlayerPanel(GameEngine engine, AppFrame parent, SummaryPanel summaryPanel){
        this.gameEngine = engine;
        this.parent = parent;
        this.summaryPanel = summaryPanel;
        renderPlayers();
    }

    public void renderPlayers(){
        this.clear();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JLabel title = new JLabel("All Players");

        allPlayersPanel = new JPanel();

        JScrollPane scrollWheel = new JScrollPane(allPlayersPanel);
        scrollWheel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        allPlayersPanel.setLayout(new GridLayout(100, 1));

        setBorder(BorderFactory.createTitledBorder("Players"));

        createAllPlayers();
        add(scrollWheel);

        this.refresh();
    }

    public void createAllPlayers(){
        for (Player p : gameEngine.getAllPlayers()) {
            PlayerView pView = new PlayerView(p, gameEngine, this);
            playerViews.add(pView);
            allPlayersPanel.add(pView);
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
        allPlayersPanel.add(pView);
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
    }

    public SummaryPanel getSummaryPanel() {
        return summaryPanel;
    }

    public void setSummaryPanel(SummaryPanel summaryPanel) {
        this.summaryPanel = summaryPanel;
    }
}
