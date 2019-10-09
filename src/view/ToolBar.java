package view;

import model.interfaces.GameEngine;
import model.interfaces.Player;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToolBar extends JToolBar {
    GameEngine gameEngine;
    private AppFrame parent;

    public ToolBar(GameEngine engine, AppFrame parent){
        this.gameEngine = engine;
        this.parent = parent;
        renderToolbar();
    }

    public void renderToolbar(){
        this.clear();

        JPanel p = new JPanel();
        JButton spinButton = new JButton("Manual Spin");

        spinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        gameEngine.spinSpinner (100, 1000, 100, 50, 500, 50) ;
                    }
                });
            }
        });


        JComboBox playerSelect = new JComboBox();

        if(!gameEngine.getAllPlayers().isEmpty()){
            for (Player player : gameEngine.getAllPlayers()) {
                playerSelect.addItem(player);

            }
        }

        playerSelect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.selectPlayer((Player) playerSelect.getSelectedItem());
            }
        });
        if(parent.getSelectedPlayer() == null){
            playerSelect.setSelectedIndex(-1);
        }


        add(playerSelect);

        p.add(spinButton);
        add(p);

        this.refresh();
    }

    public void clear(){
        this.removeAll();
    }

    public void refresh(){
        this.revalidate();
        this.repaint();
    }

}
