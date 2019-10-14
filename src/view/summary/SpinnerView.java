package view;

import model.CoinImpl;
import model.enumeration.CoinFace;
import model.interfaces.Coin;
import model.interfaces.CoinPair;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.interfaces.GameEngineCallback;

import javax.swing.*;
import java.awt.*;

public class SpinnerView extends JPanel {
    private Player currentPlayer;
    private Coin coin1 = new CoinImpl(1);
    private Coin coin2 = new CoinImpl(2);
    private JLabel coin1Face;
    private JLabel coin2Face;

    private ImageIcon heads = new ImageIcon("img/heads.png");
    private ImageIcon tails = new ImageIcon("img/tails.png");

    private CurrentView currentView = CurrentView.SPINNER;

    public enum CurrentView{
        PLAYER, SPINNER
    }

    public SpinnerView(GameEngine engine, AppFrame parent){
        render();
    }

    public void render(){
        this.clear();

        setBorder(BorderFactory.createTitledBorder("Spinning for " + currentView.toString()));
        setLayout(new GridLayout(1, 2));


        coin1Face = new JLabel(getImage(this.getCoin1().getFace()));
        coin2Face = new JLabel(getImage(this.getCoin2().getFace()));

        add(coin1Face);
        add(coin2Face);
        this.refresh();
    }

    public ImageIcon getImage(CoinFace face){
        if(face.equals(CoinFace.TAILS)){
            return this.tails;
        }
        return this.heads;
    }

    public void switchPlayer(Player player){
        this.setCurrentPlayer(player);
        if(player.getResult() != null){
            this.setCoins(player.getResult());
        }
        setCurrentView(CurrentView.PLAYER);
    }

    public void switchToSpinnerView(){
        this.setCurrentView(CurrentView.SPINNER);
    }

    public void setCoins(CoinPair coins){
        setCoin1(coins.getCoin1());
        setCoin2(coins.getCoin2());
        this.renderCoins();
    }

    public void renderCoins(){
        coin1Face.setIcon((getImage(this.getCoin1().getFace())));
        coin2Face.setIcon((getImage(this.getCoin2().getFace())));
        this.refresh();
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
            if(coin != null){
                this.renderCoins();
            }

        }
    }

    public void updateCoin2(Player player, Coin coin){
        if(player.getPlayerId().equals(this.getCurrentPlayer().getPlayerId())){
            this.setCoin2(coin);
            if(coin != null) {
                this.renderCoins();
            }
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
        setBorder(BorderFactory.createTitledBorder(currentView.toString()));
    }
}
