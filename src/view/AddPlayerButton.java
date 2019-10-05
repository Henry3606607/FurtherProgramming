package view;

import controller.ButtonClickController;
import model.interfaces.Player;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddPlayerButton extends JButton implements ActionListener {

    private String name;
    private PlayerPanel parent;

    public AddPlayerButton(String name, PlayerPanel parent){
        super(name);
        this.name = name;
        this.parent = parent;

        this.setBounds(0, 0, 100, 40);
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        parent.createNewPlayer();
    }
}



