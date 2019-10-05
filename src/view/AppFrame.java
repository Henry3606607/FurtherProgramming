package view;

import model.GameEngineImpl;
import model.interfaces.GameEngine;

import javax.swing.*;
import java.awt.*;

public class AppFrame extends JFrame {
    GameEngine gameEngine = new GameEngineImpl();
    Container c = getContentPane();
    PlayerPanel playerPanel;

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
        createPlayers();
        this.refresh();
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
}
