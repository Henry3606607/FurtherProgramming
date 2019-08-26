package model;

import model.enumeration.CoinFace;
import model.interfaces.Coin;

public class CoinImpl implements Coin {

    private int number;
    private CoinFace coinFace =  (int) (Math.random() * ((1 - 0) + 1)) + 0 == 1 ? CoinFace.HEADS : CoinFace.TAILS;

    public CoinImpl(int number){
        this.number = number;
    }

    @Override
    public int getNumber() {
        return this.number;
    }

    @Override
    public CoinFace getFace() {
        return this.coinFace;
    }

    @Override
    public void flip() {
        if(this.coinFace.equals(coinFace.TAILS)){
            this.coinFace = CoinFace.HEADS;
        }
        else{
            this.coinFace = CoinFace.TAILS;
        }
    }

    @Override
    public boolean equals(Coin coin) {
        return this.coinFace.equals(coin.getFace());
    }
}
