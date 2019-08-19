package view;

import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.interfaces.Coin;
import model.interfaces.CoinPair;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.interfaces.GameEngineCallback;

/**
 * 
 * Skeleton implementation of GameEngineCallback showing Java logging behaviour
 * 
 * @author Caspar Ryan
 * @see view.interfaces.GameEngineCallback
 * 
 */
public class GameEngineCallbackImpl implements GameEngineCallback
{
   private static final Logger logger = Logger.getLogger(GameEngineCallback.class.getName());

   @Override
   public void spinnerCoinUpdate(Coin coin, GameEngine engine) {
      logger.log(Level.FINE, String.format("Spinner coin %d flipped to %s", coin.getNumber(), coin.getFace()));
   }

   @Override
   public void spinnerResult(CoinPair coinPair, GameEngine engine) {
      logger.log(Level.INFO, String.format("Spinner, final result=Coin %d: %s, Coin %d: %s", coinPair.getCoin1().getNumber(), coinPair.getCoin1().getFace(),
              coinPair.getCoin2().getNumber(), coinPair.getCoin2().getFace()));

      String finalResult = "";
      for (Player p : engine.getAllPlayers()) {
         finalResult += this.playerFinalResult(p);
      }
      logger.log(Level.INFO, String.format("Final Player Results%s", finalResult));
   }

   private String playerFinalResult(Player player){
      return String.format("\nPlayer: id=%s, name=%s, bet=%d, betType=%s, points=%d, RESULT .. Coin 1: %s, Coin 2: %s",
               player.getPlayerId(), player.getPlayerName(), player.getBet(), player.getBetType().toString(), player.getPoints(),
              player.getResult().getCoin1().getFace(), player.getResult().getCoin2().getFace());
   }

   public GameEngineCallbackImpl()
   {
      // NOTE need to also set the console to FINE in %JRE_HOME%\lib\logging.properties
      logger.setLevel(Level.FINE);
   }

   @Override
   public void playerCoinUpdate(Player player, Coin coin, GameEngine engine)
   {
      // intermediate results logged at Level.FINE
      logger.log(Level.FINE, String.format("%s coin %d flipped to %s", player.getPlayerName(), coin.getNumber(), coin.getFace()));
   }

   @Override
   public void playerResult(Player player, CoinPair coinPair, GameEngine engine)
   {
      // final results logged at Level.INFO
      logger.log(Level.INFO, String.format("%s, final result=Coin %d: %s, Coin %d: %s", player.getPlayerName(), coinPair.getCoin1().getNumber(), coinPair.getCoin1().getFace(),
              coinPair.getCoin2().getNumber(), coinPair.getCoin2().getFace()));
   }
}
