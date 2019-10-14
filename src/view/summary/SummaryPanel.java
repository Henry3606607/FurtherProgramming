package view.summary;

import model.interfaces.CoinPair;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.AppFrame;
import view.interfaces.GameEngineCallback;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SummaryPanel extends JPanel {
    GameEngine gameEngine;
    private GameEngineCallback callback;
    private AppFrame parent;
    private ArrayList<PlayerResult> playerResults = new ArrayList<>();
    private SpinnerResult spinnerResult;
    private JPanel allPlayersPanel;

    public SummaryPanel(GameEngine engine, AppFrame parent) {
        this.gameEngine = engine;
        this.parent = parent;
        render();
    }

    public void render() {
        this.clear();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JLabel title = new JLabel("Summary");

        allPlayersPanel = new JPanel();

        JScrollPane scrollWheel = new JScrollPane(allPlayersPanel);
        scrollWheel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        allPlayersPanel.setLayout(new GridLayout(100, 1));

        spinnerResult = new SpinnerResult();
        allPlayersPanel.add(spinnerResult);

        for (Player p : gameEngine.getAllPlayers()) {
            PlayerResult pResult = new PlayerResult(p);
            playerResults.add(pResult);
            allPlayersPanel.add(pResult);
        }

        add(title);
        add(scrollWheel);

        this.refresh();
    }

    public void clear() {
        this.removeAll();
    }

    public void refresh() {
        this.revalidate();
        this.repaint();
    }

    public void appendMessage(String message) {
        //textArea.append(message + "\n");
    }

    public void playerJoined(Player player) {
        PlayerResult playerResult = new PlayerResult(player);
        playerResults.add(playerResult);
        allPlayersPanel.add(playerResult);
        this.refresh();
    }

    public void playerLeft(Player player) {
        PlayerResult playerResultFound = null;
        for (PlayerResult playerResult : playerResults) {
            if (playerResult.getPlayer().getPlayerId().equals(player.getPlayerId())) {
                playerResultFound = playerResult;
                break;
            }
        }
        if (playerResultFound != null) {
            playerResults.remove(playerResultFound);
            allPlayersPanel.remove(playerResultFound);
            this.refresh();
        }

    }

    public void cancelBet(Player player) {
        appendMessage(String.format("%s has cancelled their bet", player.getPlayerName()));
    }

    public void finalResults(ArrayList<Player> finalPlayers, CoinPair spinnerCoins) {
        spinnerResult.displayResult(spinnerCoins);
        for (PlayerResult playerResult : playerResults) {
            for (Player finalPlayer : finalPlayers) {
                if (finalPlayer.getPlayerId().equals(playerResult.getPlayer().getPlayerId())) {
                    playerResult.displayWinOrLoss(spinnerCoins);
                }
            }
        }
        this.refresh();
    }

    public void playerResult(Player player) {
        PlayerResult playerResult = findPlayer(player);
        if (playerResult != null) {
            playerResult.displayResult();
        }
        this.refresh();
    }

    public void playerSpinning(Player player) {
        PlayerResult playerResult = findPlayer(player);
        if (playerResult != null) {
            playerResult.spinning();
        }
        this.refresh();
    }

    public PlayerResult findPlayer(Player player) {
        for (PlayerResult playerResult : playerResults) {
            if (player.getPlayerId().equals(playerResult.getPlayer().getPlayerId())) {
                return playerResult;
            }
        }
        return null;
    }

    public void spinnerSpinning(){
        spinnerResult.spinning();
    }

}
