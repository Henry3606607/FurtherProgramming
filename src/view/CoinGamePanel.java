package view;

import javax.swing.*;

public abstract class CoinGamePanel extends JPanel {

    public void clear(){
        this.removeAll();
    }

    public void refresh(){
        this.revalidate();
        this.repaint();
    }
}
