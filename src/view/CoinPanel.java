package view;

import model.CoinImpl;
import model.enumeration.CoinFace;
import model.interfaces.Coin;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.interfaces.GameEngineCallback;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CoinPanel extends JPanel {
    private GameEngine gameEngine;
    private GameEngineCallback callback;
    private AppFrame parent;

    private Player currentPlayer;
    private Coin coin1 = new CoinImpl(1);
    private Coin coin2 = new CoinImpl(2);

    private CurrentView currentView = CurrentView.SPINNER;

    public enum CurrentView{
        PLAYER, SPINNER
    }

    public CoinPanel(GameEngine engine, AppFrame parent){
        this.gameEngine = engine;
        this.parent = parent;
        renderCoins();
    }

    public void renderCoins(){
        this.clear();

        setBorder(BorderFactory.createTitledBorder("Coins"));

        JPanel listPane = new JPanel();
        listPane.setLayout(new BoxLayout(listPane, BoxLayout.PAGE_AXIS));

        listPane.add(Box.createRigidArea(new Dimension(0,5)));
        listPane.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        JPanel coinViewScroll = new JPanel();
        coinViewScroll.setLayout(new BoxLayout(coinViewScroll, BoxLayout.LINE_AXIS));
        coinViewScroll.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        coinViewScroll.add(Box.createHorizontalGlue());
        coinViewScroll.add(Box.createRigidArea(new Dimension(10, 0)));

        JLabel coin1Face = new JLabel("Coin 1: " + this.getCoin1().getFace());
        JLabel coin2Face = new JLabel("Coin 2: " + this.getCoin2().getFace());

        if(getCurrentPlayer() != null){
            renderPlayerSpinButtion();
        }


        listPane.add(coin1Face);
        listPane.add(coin2Face);
        this.add(listPane, BorderLayout.CENTER);
        this.add(coinViewScroll, BorderLayout.PAGE_END);

        this.refresh();
    }

    public void renderPlayerSpinButtion(){
        JButton spinPlayerButton = new JButton("Spin for "+getCurrentPlayer().getPlayerName());

        spinPlayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        gameEngine.spinPlayer (getCurrentPlayer(), 100, 1000, 100, 50, 500, 50); ;
                    }
                });
            }
        });

        add(spinPlayerButton);
    }

    public void clear(){
        this.removeAll();
    }

    public void refresh(){
        this.revalidate();
        this.repaint();
    }

    public void updateCoin1(Player player, Coin coin){
        if(player.getPlayerId().equals(this.getCurrentPlayer().getPlayerId())){
            this.setCoin1(coin);
            this.renderCoins();
        }
    }

    public void updateCoin2(Player player, Coin coin){
        if(player.getPlayerId().equals(this.getCurrentPlayer().getPlayerId())){
            this.setCoin2(coin);
            this.renderCoins();
        }
    }

    public void updateSpinnerCoin1(Coin coin){
        if(this.getCurrentView().equals(CurrentView.SPINNER)){
            this.setCoin1(coin);
            this.renderCoins();
        }
    }

    public void updateSpinnerCoin2(Coin coin){
        if(this.getCurrentView().equals(CurrentView.SPINNER)){
            this.setCoin2(coin);
            this.renderCoins();
        }
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;

    }

    public Coin getCoin1() {
        return coin1;
    }

    public void setCoin1(Coin coin1) {
        this.coin1 = coin1;

    }

    public Coin getCoin2() {
        return coin2;
    }

    public void setCoin2(Coin coin2) {
        this.coin2 = coin2;
    }

    public CurrentView getCurrentView() {
        return currentView;
    }

    public void setCurrentView(CurrentView currentView) {
        this.currentView = currentView;
    }
}
