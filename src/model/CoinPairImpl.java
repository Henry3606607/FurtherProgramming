package model;

import model.interfaces.Coin;
import model.interfaces.CoinPair;

public class CoinPairImpl implements CoinPair {

    Coin coin1;
    Coin coin2;

    public CoinPairImpl(){
        this.coin1 = new CoinImpl(1);
        this.coin2 = new CoinImpl(2);
    }

    @Override
    public Coin getCoin1() {
        return this.coin1;
    }

    @Override
    public Coin getCoin2() {
        return this.coin2;
    }

    @Override
    public boolean equals(CoinPair coinPair) {
        return coin1.equals(coin2) && coinPair.getCoin1().equals(coinPair.getCoin2());
    }
}
