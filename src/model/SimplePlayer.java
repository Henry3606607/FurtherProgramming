package model;

import model.enumeration.BetType;
import model.interfaces.CoinPair;
import model.interfaces.Player;

public class SimplePlayer implements Player {

    private String playerId;
    private String playerName;
    private int bet;
    private int points;
    private BetType betType = BetType.NO_BET;
    private CoinPair result;

    public SimplePlayer(String playerId, String playerName, int points){
        this.playerId = playerId;
        this.playerName = playerName;
        this.points = points;
    }

    @Override
    public String getPlayerName() {
        return this.playerName;
    }

    @Override
    public void setPlayerName(String playerName) {
     this.playerName = playerName;
    }

    @Override
    public int getPoints() {
        return this.points;
    }

    @Override
    public void setPoints(int points) {
    this.points += points;
    }

    @Override
    public String getPlayerId() {
        return this.playerId;
    }

    @Override
    public boolean setBet(int bet) {
        if(this.getPoints() - bet >= 0){
            this.bet = bet;
            this.setPoints(-bet);
            return true;
        }
        return false;
    }

    @Override
    public int getBet() {
        return this.bet;
    }

    @Override
    public void setBetType(BetType betType) {
        this.betType = betType;
    }

    @Override
    public BetType getBetType() {
        return this.betType;
    }

    @Override
    public void resetBet() {
        //TODO no bet?
        this.bet = 0;
        this.betType = BetType.NO_BET;
    }

    @Override
    public CoinPair getResult() {
        return this.result;
    }

    @Override
    public void setResult(CoinPair coinPair) {
        this.result = coinPair;
    }
}
