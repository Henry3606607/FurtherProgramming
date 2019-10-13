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
    private JTextArea textArea = new JTextArea(20, 20);

    public SummaryPanel(GameEngine engine, AppFrame parent){
        this.gameEngine = engine;
        this.parent = parent;
        render();
    }

    public void render(){
        this.clear();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JLabel title = new JLabel("Summary");

        textArea.setLineWrap(true);

        JScrollPane scrollWheel = new JScrollPane(textArea);
        scrollWheel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        textArea.setEditable(false);
        add(title);
        add(scrollWheel);

        this.refresh();
    }

    public void clear(){
        this.removeAll();
    }

    public void refresh(){
        this.revalidate();
        this.repaint();
    }

    public void appendMessage(String message){
        textArea.append(message + "\n");
    }

    public void printBetInformation(Player player){
        appendMessage(String.format("%s place a bet of %d %s", player.getPlayerName(), player.getBet(), player.getBetType().toString()));
    }

    public void playerJoined(Player player){
        appendMessage(String.format("%s has joined", player.getPlayerName()));
    }

    public void playerLeft(Player player){
        appendMessage(String.format("%s has quit", player.getPlayerName()));
    }

    public void cancelBet(Player player){
        appendMessage(String.format("%s has cancelled their bet", player.getPlayerName()));
    }

}
