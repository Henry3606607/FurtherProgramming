package model;

import model.enumeration.BetType;
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

        //delay2
        player.getResult()
                .getCoin2()
                .flip();
        //playerCoinUpdate()
        //player result();
    }

    @Override
    public void applyBetResults(CoinPair spinnerResult) {

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
        return false;
    }

    public void spinSpinner(int initialDelay1, int finalDelay1, int delayIncrement1,
                            int initialDelay2, int finalDelay2, int delayIncrement2) throws IllegalArgumentException{

    }

}
