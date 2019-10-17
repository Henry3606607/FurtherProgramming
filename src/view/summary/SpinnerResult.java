package view.summary;

import model.interfaces.CoinPair;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class SpinnerResult extends JPanel {
    private JLabel result;
    private JLabel currentStatus;

    public SpinnerResult(){
        this.render();
    }

    public void render(){
        this.clear();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JLabel title = new JLabel("Game status");
        setBorder(new EmptyBorder(10, 0, 10, 0));

        result = new JLabel("Spinner has not spun");
        currentStatus = new JLabel("Waiting for other players...");

        add(title);
        add(result);
        add(currentStatus);

        this.refresh();
    }

    public void displayResult(CoinPair coinPair){
        result.setText(String.format("Coin 1: %s, Coin 2: %s", coinPair.getCoin1().getFace(), coinPair.getCoin2().getFace()));
        currentStatus.setText("Finished!");
    }

    public void spinning(){
        currentStatus.setText("Spinning...");
        this.refresh();
    }

    public void reset(){
        result.setText("Spinner has not spun");
        currentStatus.setText("Waiting for other players...");
    }

    public void clear(){
        this.removeAll();
    }

    public void refresh(){
        this.revalidate();
        this.repaint();
    }
}
