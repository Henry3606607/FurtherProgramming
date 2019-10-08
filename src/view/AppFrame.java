package view;

import model.GameEngineImpl;
import model.interfaces.GameEngine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppFrame extends JFrame {
    private GameEngine gameEngine = new GameEngineImpl();
    private Container c = getContentPane();
    private PlayerPanel playerPanel;
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem playerView, spinnerView;
    private JToolBar toolbar;
    private SummaryPanel summaryPanel;



    private String currentView = "player";

    public AppFrame()
    {
        super("Assignment 2");

        setBounds(100, 100, 640, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        playerPanel = new PlayerPanel(gameEngine, this);
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
            createSpinView();
        }

        this.refresh();
    }

    public void createSpinView(){
        //c.add()
    }

    public void createSummaryPanel(){
        summaryPanel = new SummaryPanel(gameEngine, this);
        c.add(summaryPanel, BorderLayout.EAST);
    }

    public void createToolBar(){
        toolbar = new JToolBar();
        JPanel p = new JPanel();
        JButton spinButton = new JButton("Manual Spin");

        spinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameEngine.spinSpinner(100, 100, 100, 100,100,100);
            }
        });

        p.add(spinButton);
        toolbar.add(p);
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
}
