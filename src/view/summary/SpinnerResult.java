package view.summary;

import model.interfaces.CoinPair;
import view.CoinGamePanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class SpinnerResult extends CoinGamePanel {
    private JLabel result;
    private JLabel currentStatus;
    private SpinnerStatus spinnerStatus = SpinnerStatus.WAITING;
    private CoinPair lastResult;

    public enum SpinnerStatus {
        WAITING, SPINNING, READY
    }

    public SpinnerResult() {
        this.render();
    }

    public void render() {
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

    public void displayResult(CoinPair coinPair) {
        this.setLastResult(coinPair);
        result.setText(String.format("Coin 1: %s, Coin 2: %s", coinPair.getCoin1().getFace(), coinPair.getCoin2().getFace()));
        updateSpinnerStatusAndLabel(SpinnerStatus.READY);
    }

    public void spinning() {
        this.updateSpinnerStatusAndLabel(SpinnerStatus.SPINNING);
    }

    public void reset() {
        this.setLastResult(null);
        this.updateSpinnerStatusAndLabel(SpinnerStatus.WAITING);
    }

    public void updateSpinnerStatusAndLabel(SpinnerStatus status){
        this.spinnerStatus = status;
        if(status.equals(SpinnerStatus.WAITING)){
            result.setText("Spinner has not spun");
        }
        else if(status.equals(SpinnerStatus.SPINNING)){
            currentStatus.setText("Spinning...");
        }
        else if(status.equals(SpinnerStatus.READY)){
            currentStatus.setText("Finished");
        }
        this.refresh();
    }

    public SpinnerStatus getSpinnerStatus() {
        return spinnerStatus;
    }

    public void setSpinnerStatus(SpinnerStatus spinnerStatus) {
        this.spinnerStatus = spinnerStatus;
    }

    public CoinPair getLastResult() {
        return lastResult;
    }

    public void setLastResult(CoinPair lastResult) {
        this.lastResult = lastResult;
    }
}
