package view;

import model.interfaces.Coin;
import model.interfaces.CoinPair;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.interfaces.GameEngineCallback;

public class GameEngineCallbackGUI implements GameEngineCallback {
    private PlayerPanel playerPanel;
    private CoinPanel coinPanel;
    private SummaryPanel summaryPanel;

    @Override
    public void playerCoinUpdate(Player player, Coin coin, GameEngine engine) {
        if(coin.getNumber() == 1) {
            coinPanel.updateCoin1(player, coin);
        }
        else{
            coinPanel.updateCoin2(player, coin);
        }
    }

    @Override
    public void spinnerCoinUpdate(Coin coin, GameEngine engine) {
        if(coin.getNumber() == 1){
            coinPanel.updateSpinnerCoin1(coin);
        }
        else{
            coinPanel.updateSpinnerCoin2(coin);
        }
    }

    @Override
    public void playerResult(Player player, CoinPair coinPair, GameEngine engine) {
        summaryPanel.render();
    }

    @Override
    public void spinnerResult(CoinPair coinPair, GameEngine engine) {

    }

    public PlayerPanel getPlayerPanel() {
        return playerPanel;
    }

    public void setPlayerPanel(PlayerPanel playerPanel) {
        this.playerPanel = playerPanel;
    }

    public CoinPanel getCoinPanel() {
        return coinPanel;
    }

    public void setCoinPanel(CoinPanel coinPanel) {
        this.coinPanel = coinPanel;
    }

    public SummaryPanel getSummaryPanel() {
        return summaryPanel;
    }

    public void setSummaryPanel(SummaryPanel summaryPanel) {
        this.summaryPanel = summaryPanel;
    }
}
