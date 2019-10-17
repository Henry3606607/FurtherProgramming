package view;

import controller.AppFrameResizeController;
import model.SimplePlayer;
import model.enumeration.BetType;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.player.PlayerPanel;
import view.summary.SpinnerView;
import view.summary.SummaryPanel;
import view.toolbar.ToolBar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class AppFrame extends JFrame {
    private GameEngine gameEngine;
    private Container c;
    private SpinnerView spinnerView;
    private PlayerPanel playerPanel;
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem playerViewItem, spinViewItem;
    private StatusBar statusBar;
    private ToolBar toolBar;
    private SummaryPanel summaryPanel;
    private Player selectedPlayer;
    private int nextIdValue = 1;

    private String currentView = "player";

    public AppFrame(GameEngine gameEngine) {
        super("Assignment 2");
        c = getContentPane();
        c.setLayout(new GridBagLayout());



        this.gameEngine = gameEngine;

        gameEngine.addPlayer(new SimplePlayer(Integer.toString(getNextIdValue()), "The Coin Master", 1000));
        gameEngine.addPlayer(new SimplePlayer(Integer.toString(getNextIdValue()), "The Loser", 750));

        this.buildFrame();

        //TODO remove clear and refresh from components
    }

    public void buildFrame() {


        setBounds(100, 100, 640, 480);
        setMinimumSize(new Dimension(400, 400));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        statusBar = new StatusBar();
        spinnerView = new SpinnerView(gameEngine, this);
        summaryPanel = new SummaryPanel(gameEngine, this);
        playerPanel = new PlayerPanel(gameEngine, this, summaryPanel);
        toolBar = new ToolBar(gameEngine, AppFrame.this);


        setVisible(true);

        gameEngine.addGameEngineCallback(new GameEngineCallbackGUI(spinnerView, summaryPanel, this));

        render();
        this.addComponentListener(new AppFrameResizeController(spinnerView, toolBar));
    }

    @Override
    public void setSize(int width, int height) {
        System.out.println("setSize");
    }

    public void render() {
        this.clear();
        this.createMenu();
        c.add(summaryPanel, BorderLayout.EAST);
        c.add(toolBar, BorderLayout.NORTH);
        if (this.currentView.equals("player")) {
            c.add(playerPanel, BorderLayout.WEST);
        } else {
            c.add(spinnerView, BorderLayout.WEST);
        }

        c.add(statusBar, BorderLayout.SOUTH);

        this.refresh();
    }

    public void clear() {
        this.getContentPane().removeAll();
        c = this.getContentPane();
    }

    public void refresh() {
        c.revalidate();
        c.repaint();
    }

    public String getCurrentView() {
        return currentView;
    }

    public void setCurrentView(String currentView) {
        this.currentView = currentView;
    }

    public void selectPlayer(Player player) {
        setSelectedPlayer(player);
        spinnerView.switchPlayer(player);
        toolBar.isSpinPlayerButtonDisabled(false);
    }

    public void createMenu() {
        menuBar = new JMenuBar();
        menu = new JMenu("Menu");
        playerViewItem = new JMenuItem("Player View");
        spinViewItem = new JMenuItem("Spin View");

        playerViewItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setCurrentView("player");
                render();
            }
        });

        spinViewItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setCurrentView("spinner");
                render();
            }
        });

        menu.add(playerViewItem);
        menu.add(spinViewItem);
        menuBar.add(menu);
        setJMenuBar(menuBar);
    }

    public Player getSelectedPlayer() {
        return selectedPlayer;
    }

    public void setSelectedPlayer(Player selectedPlayer) {
        this.selectedPlayer = selectedPlayer;
        statusBar.switchCurrentPlayer(selectedPlayer);
        spinnerView.setCurrentPlayer(selectedPlayer);
    }

    public void canSpinnerSpin() {
        for (Player p : gameEngine.getAllPlayers()) {
            if (p.getBetType().equals(BetType.NO_BET) || p.getResult() == null) {
                return;
            }
        }
        toolBar.autoSpin();
    }

    public void newBetPlaced(Player player) {
        playerPanel.addNewBet(player);
        if (getSelectedPlayer() != null && player.getPlayerId().equals(getSelectedPlayer().getPlayerId())) {
            statusBar.betPlaced(player);
        }
    }

    public void spinnerSpinning() {
        toolBar.spinnerSpinning();
        summaryPanel.spinnerSpinning();
        spinnerView.switchToSpinnerView();
    }

    public void resetGame(){
        summaryPanel.resetGame();
        playerPanel.resetPlayers();
        this.refresh();
    }

    public PlayerPanel getPlayerPanel() {
        return playerPanel;
    }

    public void setPlayerPanel(PlayerPanel playerPanel) {
        this.playerPanel = playerPanel;
    }

    public void newPlayerAdded(Player player) {
        this.toolBar.addNewPlayer(player);
    }

    public StatusBar getStatusBar() {
        return statusBar;
    }

    public void setStatusBar(StatusBar statusBar) {
        this.statusBar = statusBar;
    }

    public SpinnerView getSpinnerView() {
        return spinnerView;
    }

    public void setSpinnerView(SpinnerView spinnerView) {
        this.spinnerView = spinnerView;
    }

    public SummaryPanel getSummaryPanel() {
        return summaryPanel;
    }

    public void setSummaryPanel(SummaryPanel summaryPanel) {
        this.summaryPanel = summaryPanel;
    }

    public int getNextIdValue() {
        return nextIdValue++;
    }

    public void setNextIdValue(int nextIdValue) {
        this.nextIdValue = nextIdValue;
    }
}
