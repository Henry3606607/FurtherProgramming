package controller;

import model.interfaces.Player;
import view.AppFrame;
import view.ToolBar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectPlayerController implements ActionListener {
    private AppFrame appFrame;
    private ToolBar toolBar;

    public SelectPlayerController(AppFrame appFrame, ToolBar toolBar) {
            this.appFrame = appFrame;
            this.toolBar = toolBar;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(toolBar.getPlayerSelect().getSelectedItem() != null){
            Player player = (Player) toolBar.getPlayerSelect().getSelectedItem();
            appFrame.selectPlayer(player);
        }

    }
}
