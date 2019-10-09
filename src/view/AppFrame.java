package view;

import model.GameEngineImpl;
import model.interfaces.GameEngine;
import model.interfaces.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppFrame extends JFrame {
    private GameEngine gameEngine;
    private GameEngineCallbackGUI callbackGUI = new GameEngineCallbackGUI();;
    private Container c = getContentPane();
    private CoinPanel coinPanel;
    private PlayerPanel playerPanel;
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem playerView, spinnerView;
    private ToolBar toolbar;
    private SummaryPanel summaryPanel;
    private Player selectedPlayer;



    private String currentView = "player";

    public AppFrame()
    {
        super("Assignment 2");

        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run() {
                gameEngine = new GameEngineImpl();
                buildFrame();
            }
        });


    }

    public void buildFrame(){
        gameEngine.addGameEngineCallback(callbackGUI);

        setBounds(100, 100, 640, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        playerPanel = new PlayerPanel(gameEngine, this);
        callbackGUI.setPlayerPanel(playerPanel);

        coinPanel = new CoinPanel(gameEngine, this);
        callbackGUI.setCoinPanel(coinPanel);
        setVisible(true);

        render();
    }

    public void render(){
        this.clear();
        this.createMenu();
        this.createSummaryPanel();
        this.createToolBar();
        if(this.currentView.equals("player")){
            createPlayers();
        }
        else{
            createCoinView();
        }

        this.refresh();
    }

    public void createCoinView(){
        c.add(coinPanel, BorderLayout.WEST);
    }

    public void createSummaryPanel(){
        summaryPanel = new SummaryPanel(gameEngine, this);
        c.add(summaryPanel, BorderLayout.EAST);
    }

    public void createToolBar(){
        toolbar = new ToolBar(gameEngine, AppFrame.this);
        c.add(toolbar, BorderLayout.NORTH);
    }

    public void createPlayers()
    {
        c.add(playerPanel, BorderLayout.WEST);
        playerPanel.renderPlayers();
    }

    public void clear(){
        this.getContentPane().removeAll();
        c = this.getContentPane();
    }

    public void refresh(){
        c.revalidate();
        c.repaint();
    }

    public String getCurrentView() {
        return currentView;
    }

    public void setCurrentView(String currentView) {
        this.currentView = currentView;
    }

    public void selectPlayer(Player player){
        setSelectedPlayer(player);
        coinPanel.setCurrentPlayer(player);
        coinPanel.renderCoins();
    }

    public void createMenu(){
        menuBar = new JMenuBar();
        menu = new JMenu("Menu");
        playerView = new JMenuItem("Player View");
        spinnerView = new JMenuItem("Spin View");

        playerView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setCurrentView("player");
                render();
            }
        });

        spinnerView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setCurrentView("spinner");
                render();
            }
        });

        menu.add(playerView);
        menu.add(spinnerView);
        menuBar.add(menu);
        setJMenuBar(menuBar);
    }

    public Player getSelectedPlayer() {
        return selectedPlayer;
    }

    public void setSelectedPlayer(Player selectedPlayer) {
        this.selectedPlayer = selectedPlayer;
    }
}
