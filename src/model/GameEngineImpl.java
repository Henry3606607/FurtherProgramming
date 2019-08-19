package model;

import model.enumeration.BetType;
import model.interfaces.Coin;
import model.interfaces.CoinPair;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.GameEngineCallbackImpl;
import view.interfaces.GameEngineCallback;

import java.util.ArrayList;
import java.util.Collection;

public class GameEngineImpl implements GameEngine {

    private Collection<Player> players = new ArrayList<Player>();
    private Collection<GameEngineCallback> gameEngineCallbacks = new ArrayList<GameEngineCallback>();

    @Override
    public void spinPlayer(Player player, int initialDelay1, int finalDelay1, int delayIncrement1, int initialDelay2, int finalDelay2, int delayIncrement2) throws IllegalArgumentException {

        player.setResult(new CoinPairImpl());
        //delay
        player.getResult()
                .getCoin1()
                .flip();
        this.updatePlayer(player, player.getResult().getCoin1());

        //delay2
        player.getResult()
                .getCoin2()
                .flip();
        this.updatePlayer(player, player.getResult().getCoin2());
    }

    private void playerResult(Player player){
        for (GameEngineCallback gameEngineCallback : gameEngineCallbacks) {
            gameEngineCallback.playerResult(player, player.getResult(), this);
        }
    }

    private void updatePlayer(Player player, Coin coin){
        for (GameEngineCallback gameEngineCallback : gameEngineCallbacks) {
            gameEngineCallback.playerCoinUpdate(player, coin, this);
        }
    }

    private void spinnerCoinUpdate(Coin coin){
        for (GameEngineCallback gameEngineCallback : gameEngineCallbacks) {
            gameEngineCallback.spinnerCoinUpdate(coin, this);
        }
    }

    private void spinnerResult(CoinPair cp){
        for (GameEngineCallback gameEngineCallback : gameEngineCallbacks) {
            gameEngineCallback.spinnerResult(cp, this);
        }
    }

    public void spinSpinner(int initialDelay1, int finalDelay1, int delayIncrement1,
                            int initialDelay2, int finalDelay2, int delayIncrement2) throws IllegalArgumentException{
        CoinPair cp = new CoinPairImpl();
        cp.getCoin1().flip();
        this.spinnerCoinUpdate(cp.getCoin1());

        cp.getCoin2().flip();
        this.spinnerCoinUpdate(cp.getCoin2());

        applyBetResults(cp);
        this.spinnerResult(cp);
    }

    public void resetPlayers(){
        for (Player player : players) {
            player.resetBet();
        }
    }

    @Override
    public void applyBetResults(CoinPair spinnerResult) {
        for (Player player : players) {
            player.getBetType().applyWinLoss(player, spinnerResult);
        }
    }

    @Override
    public void addPlayer(Player player) {
        if(this.players.contains(player)){
            return;
        }
        this.players.add(player);
    }

    @Override
    public Player getPlayer(String id) {
        for (Player player : this.players) {
            if(player.getPlayerId().equals(id)){
                return player;
            }
        };
        return null;
    }

    @Override
    public boolean removePlayer(Player player) {
        if(this.players.remove(player)){
            return true;
        }
        return false;
    }

    @Override
    public void addGameEngineCallback(GameEngineCallback gameEngineCallback) {
        this.gameEngineCallbacks.add(gameEngineCallback);
    }

    @Override
    public boolean removeGameEngineCallback(GameEngineCallback gameEngineCallback) {
        if(this.gameEngineCallbacks.remove(gameEngineCallback)){
            return true;
        }
        return false;
    }

    @Override
    public Collection<Player> getAllPlayers() {
        return this.players;
    }

    @Override
    public boolean placeBet(Player player, int bet, BetType betType) {
        if(player.setBet(bet)) {
            player.setBetType(betType);
            return true;
        }
        player.setBetType(BetType.NO_BET);
        return false;
    }



}
