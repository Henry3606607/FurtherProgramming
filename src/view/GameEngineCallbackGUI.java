package view;

import model.interfaces.Coin;
import model.interfaces.CoinPair;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.interfaces.GameEngineCallback;

import java.util.logging.Level;

public class GameEngineCallbackGUI implements GameEngineCallback {
    private SpinnerView spinnerView;
    private SummaryPanel summaryPanel;
    private AppFrame appFrame;

    public GameEngineCallbackGUI(SpinnerView spinnerView, SummaryPanel summaryPanel, AppFrame appFrame) {
        this.spinnerView = spinnerView;
        this.summaryPanel = summaryPanel;
        this.appFrame = appFrame;
    }

    @Override
    public void playerCoinUpdate(Player player, Coin coin, GameEngine engine) {
        if(coin.getNumber() == 1) {
            spinnerView.updateCoin1(player, coin);
        }
        else{
            spinnerView.updateCoin2(player, coin);
        }
    }

    @Override
    public void spinnerCoinUpdate(Coin coin, GameEngine engine) {
        if(coin.getNumber() == 1){
            spinnerView.updateSpinnerCoin1(coin);
        }
        else{
            spinnerView.updateSpinnerCoin2(coin);
        }
    }

    @Override
    public void playerResult(Player player, CoinPair coinPair, GameEngine engine) {
        summaryPanel.appendMessage(String.format("%s, final result=Coin %d: %s, Coin %d: %s", player.getPlayerName(), coinPair.getCoin1().getNumber(), coinPair.getCoin1().getFace(),
                coinPair.getCoin2().getNumber(), coinPair.getCoin2().getFace()));

        this.appFrame.canSpinnerSpin();
    }

    @Override
    public void spinnerResult(CoinPair coinPair, GameEngine engine) {
        summaryPanel.appendMessage(String.format("Spinner, final result=Coin %d: %s, Coin %d: %s", coinPair.getCoin1().getNumber(), coinPair.getCoin1().getFace(),
                coinPair.getCoin2().getNumber(), coinPair.getCoin2().getFace()));

        String finalResult = "";
        for (Player p : engine.getAllPlayers()) {
            finalResult += this.playerFinalResult(p);
        }
    }

    private String playerFinalResult(Player player){
        return String.format("\nPlayer: id=%s, name=%s, bet=%d, betType=%s, points=%d, RESULT .. Coin 1: %s, Coin 2: %s",
                player.getPlayerId(), player.getPlayerName(), player.getBet(), player.getBetType().toString(), player.getPoints(),
                player.getResult().getCoin1().getFace(), player.getResult().getCoin2().getFace());
    }
}
