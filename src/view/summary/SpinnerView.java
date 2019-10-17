package view.summary;

import model.CoinImpl;
import model.enumeration.CoinFace;
import model.interfaces.Coin;
import model.interfaces.CoinPair;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.AppFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class SpinnerView extends JPanel {
    private Player currentPlayer;
    private Coin coin1 = new CoinImpl(1);
    private Coin coin2 = new CoinImpl(2);
    private JLabel coin1Face;
    private JLabel coin2Face;
    private Dimension mainFrameDimensions = new Dimension(100, 100);

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


        coin1Face = new JLabel(getScaledImage(getImage(this.getCoin1().getFace())));
        coin2Face = new JLabel(getScaledImage(getImage(this.getCoin2().getFace())));

        add(coin1Face);
        add(coin2Face);
        this.refresh();
    }

    public String getImage(CoinFace face){
        if(face.equals(CoinFace.TAILS)){
            return "img/tails.png";
        }
        return "img/heads.png";
    }

    private ImageIcon getScaledImage(String imageUrl){
        ImageIcon imageIcon = new ImageIcon(imageUrl);
        Image image = imageIcon.getImage();
        int width = (int) mainFrameDimensions.getWidth() / 3;
        int height = (int) mainFrameDimensions.getHeight() / 3;
        int ratio = width / height;
        Image newimg = image.getScaledInstance(ratio > (420 * 420) ? 420 * width / 420 : width, ratio > (420 * 420) ? height : 420 * width/420,  java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(newimg);
    }

    public void resizeCoins(Dimension dimension){
        this.mainFrameDimensions = dimension;
        this.renderCoins();
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
        coin1Face.setIcon(getScaledImage(getImage(this.getCoin1().getFace())));
        coin2Face.setIcon(getScaledImage(getImage(this.getCoin2().getFace())));
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
