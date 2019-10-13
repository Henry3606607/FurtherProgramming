package controller;

import model.GameEngineImpl;
import model.interfaces.GameEngine;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameEngineController extends Thread {

    private GameEngine gameEngine;

    @Override
    public void run() {
        this.setGameEngine(new GameEngineImpl());
        while(true){

        }
    }

    public GameEngine getGameEngine() {
        return gameEngine;
    }

    public void setGameEngine(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }
}
