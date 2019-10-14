package view;

import model.enumeration.BetType;
import model.interfaces.CoinPair;
import model.interfaces.Player;

import javax.swing.*;

public class PlayerResult extends JPanel {
    private Player player;
    private JLabel result;
    private JLabel currentStatus;

    public PlayerResult(Player player){
        this.player = player;
        this.render();
    }

    public void render(){
        this.clear();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JLabel title = new JLabel(player.getPlayerName());

        result = new JLabel("Player has not spun");
        currentStatus = new JLabel("Playing...");

        add(title);
        add(result);
        add(currentStatus);

        this.refresh();
    }

    public void displayResult(){
        result.setText(String.format("Coin 1: %s, Coin 2: %s", player.getResult().getCoin1().getFace(), player.getResult().getCoin2().getFace()));
        currentStatus.setText("Ready!");
    }

    public void spinning(){
        currentStatus.setText("Spinning...");
        this.refresh();
    }

    public void displayWinOrLoss(CoinPair spinnerResult){
        if(this.didPlayerWin(spinnerResult)){
            currentStatus.setText("WINNER");
        }
        else{
            currentStatus.setText("LOSER");
        }
    }

    public void clear(){
        this.removeAll();
    }

    public void refresh(){
        this.revalidate();
        this.repaint();
    }

    public boolean didPlayerWin(CoinPair spinnerResult){
        if(player.getBetType().equals(BetType.BOTH)){
            if(spinnerResult.getCoin1().equals(player.getResult().getCoin1())
            && spinnerResult.getCoin2().equals(player.getResult().getCoin2())){
                return true;
            }
        }
        else if(player.getBetType().equals(BetType.COIN1)){
            if(spinnerResult.getCoin1().equals(player.getResult().getCoin1())){
                return true;
            }
        }
        else if(player.getBetType().equals(BetType.COIN2)){
            if(spinnerResult.getCoin2().equals(player.getResult().getCoin2())){
                return true;
            }
        }
        return false;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
