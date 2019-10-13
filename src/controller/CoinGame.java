package controller;

import model.GameEngineImpl;
import model.interfaces.GameEngine;
import view.AppFrame;
import view.GameEngineCallbackImpl;

import javax.swing.*;

public class CoinGame {
    private final GameEngine gameEngine;

    public CoinGame(){
        gameEngine = new GameEngineImpl();
        gameEngine.addGameEngineCallback(new GameEngineCallbackImpl());

        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                new AppFrame(gameEngine);
            }
        });
    }

    public static void main(String[] args)
    {
        new CoinGame();
    }
}
