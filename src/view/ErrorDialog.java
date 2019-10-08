package view;

import model.SimplePlayer;
import model.interfaces.GameEngine;
import model.interfaces.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;

public class ErrorDialog {

    private ArrayList<String> errors;
    private JFrame parent;
    private JDialog dialog;
    private JPanel panel;

    public ErrorDialog(JFrame parent, ArrayList<String> errors) {
        this.errors = errors;
        this.parent = parent;

        this.render();
    }

    public void render(){
        dialog = new JDialog(parent, "Error");
        this.createPanel();
        dialog.setSize(1000, 200);
        dialog.add(panel);
        dialog.setVisible(true);
    }

    public void createPanel(){
        panel = new JPanel();

        for (String error : errors) {
            JLabel errorLabel = new JLabel(error);
            panel.add(errorLabel);
        }



        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.setVisible(false);
            }
        });

        panel.add(closeButton);
    }
}
