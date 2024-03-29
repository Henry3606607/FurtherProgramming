package model.enumeration;

import model.interfaces.CoinPair;
import model.interfaces.Player;

/**
 * Provided enum type for Further Programming representing a type of Bet<br>
 * See inline comments for where you need to provide additional code
 *
 * @author Caspar Ryan
 */
public enum BetType {

    COIN1 {
        @Override
        public String toString() {
            return "COIN1";
        }

        @Override
        public void applyWinLoss(Player player, CoinPair spinnerResult) {
            if(player.getResult().getCoin1().equals(spinnerResult.getCoin1())){
                player.setPoints(player.getBet());
            }
            else{
                player.setPoints(-player.getBet());
            }
        }
    },
    COIN2 {
        @Override
        public String toString() {
            return "COIN2";
        }
        @Override
        public void applyWinLoss(Player player, CoinPair spinnerResult) {
            if(player.getResult().getCoin2().equals(spinnerResult.getCoin2())){
                player.setPoints(player.getBet());
            }
            else{
                player.setPoints(-player.getBet());
            }
        }
    },
    BOTH {
        @Override
        public String toString() {
            return "BOTH";
        }
        @Override
        public void applyWinLoss(Player player, CoinPair spinnerResult) {
            if(player.getResult().getCoin1().equals(spinnerResult.getCoin1()) &&
                    player.getResult().getCoin2().equals(spinnerResult.getCoin2())){
                player.setPoints(player.getBet() * 2);
            }
            else{
                player.setPoints(-player.getBet());
            }
        }
    },
    NO_BET {
        @Override
        public String toString() {
            return "NO_BET";
        }
        @Override
        public void applyWinLoss(Player player, CoinPair spinnerResult) {
            return;
        }
    };

    // TODO finish this class with other enum constants

    /**
     * This method is to be overridden for each bet type<br>
     * see assignment specification for betting odds and how win/loss is applied
     *
     * @param player        - the player to apply the win/loss points balance adjustment
     * @param spinnerResult - the CoinPair result of the spinner to compare to
     */
    public abstract void applyWinLoss(Player player, CoinPair spinnerResult);

    public abstract String toString();
}