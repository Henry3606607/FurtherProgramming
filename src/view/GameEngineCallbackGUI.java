package view;

import model.enumeration.BetType;
import model.interfaces.Coin;
import model.interfaces.CoinPair;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.interfaces.GameEngineCallback;
import view.summary.SpinnerView;
import view.summary.SummaryPanel;

import java.util.ArrayList;

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
        summaryPanel.playerResult(player);
    }

    @Override
    public void spinnerResult(CoinPair coinPair, GameEngine engine) {
        ArrayList<Player> finalPlayers = new ArrayList<>();
        for (Player p : engine.getAllPlayers()) {
            if(!p.getBetType().equals(BetType.NO_BET)){
                finalPlayers.add(p);
            }
        }
        summaryPanel.finalResults(finalPlayers, coinPair);
    }
}
