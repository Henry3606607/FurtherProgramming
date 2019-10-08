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

        JPanel coinViewScroll = new JPanel();
        coinViewScroll.setLayout(new BoxLayout(coinViewScroll, BoxLayout.LINE_AXIS));
        coinViewScroll.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        coinViewScroll.add(Box.createHorizontalGlue());
        coinViewScroll.add(Box.createRigidArea(new Dimension(10, 0)));


        JLabel label = new JLabel("test");
        add(label);


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
