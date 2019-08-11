package model;

import model.enumeration.CoinFace;
import model.interfaces.Coin;

public class CoinImpl implements Coin {

    private int number;
    private CoinFace coinFace = CoinFace.HEADS;

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
        int flip = (int)(Math.random() * ((1 - 0) + 1)) + 0;
        if(flip == 1){
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
