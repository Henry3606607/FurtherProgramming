package view.summary;

import model.enumeration.BetType;
import model.interfaces.CoinPair;
import model.interfaces.Player;
import view.CoinGamePanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class PlayerResult extends CoinGamePanel {
    private Player player;
    private JLabel result;
    private JLabel currentStatus;
    private PlayerStatus playerStatus = PlayerStatus.PLAYING;

    public enum PlayerStatus {
        PLAYING, SPINNING, READY
    }

    public PlayerResult(Player player) {
        this.player = player;
        this.render();
    }

    public void render() {
        this.clear();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JLabel title = new JLabel(player.getPlayerName());
        setBorder(new EmptyBorder(10, 0, 10, 0));

        result = new JLabel("Player has not spun");
        currentStatus = new JLabel("Playing...");

        add(title);
        add(result);
        add(currentStatus);

        this.refresh();
    }

    public void displayResult() {
        result.setText(String.format("Coin 1: %s, Coin 2: %s", player.getResult().getCoin1().getFace(), player.getResult().getCoin2().getFace()));
        this.setStatusAndLabel(PlayerStatus.READY);
    }

    public void spinning() {
        this.setStatusAndLabel(PlayerStatus.SPINNING);
        this.refresh();
    }

    public void setStatusAndLabel(PlayerStatus status) {
        this.playerStatus = status;
        if (status.equals(PlayerStatus.PLAYING)) {
            currentStatus.setText("Playing...");
        } else if (status.equals(PlayerStatus.SPINNING)) {
            currentStatus.setText("Spinning...");
        } else if (status.equals(PlayerStatus.READY)) {
            currentStatus.setText("Ready!");
        }
    }

    public void displayWinOrLoss(CoinPair spinnerResult) {
        if (player.getResult() != null) {
            if (this.didPlayerWin(spinnerResult)) {
                currentStatus.setText("WINNER");
            } else {
                currentStatus.setText("LOSER");
            }
        }
    }

    public void reset() {
        result.setText("Player has not spun");
        this.setStatusAndLabel(PlayerStatus.PLAYING);
    }

    public boolean didPlayerWin(CoinPair spinnerResult) {
        if (player.getBetType().equals(BetType.BOTH)) {
            if (spinnerResult.getCoin1().equals(player.getResult().getCoin1())
                    && spinnerResult.getCoin2().equals(player.getResult().getCoin2())) {
                return true;
            }
        } else if (player.getBetType().equals(BetType.COIN1)) {
            if (spinnerResult.getCoin1().equals(player.getResult().getCoin1())) {
                return true;
            }
        } else if (player.getBetType().equals(BetType.COIN2)) {
            if (spinnerResult.getCoin2().equals(player.getResult().getCoin2())) {
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

    public PlayerStatus getPlayerStatus() {
        return playerStatus;
    }

    public void setPlayerStatus(PlayerStatus playerStatus) {
        this.playerStatus = playerStatus;
    }
}
