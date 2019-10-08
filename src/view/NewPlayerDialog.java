package view;

import model.SimplePlayer;
import model.interfaces.GameEngine;
import model.interfaces.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class NewPlayerDialog {

    private AppFrame parent;
    private GameEngine gameEngine;
    private JDialog dialog;
    private JPanel panel;

    public NewPlayerDialog(AppFrame parent, GameEngine gameEngine) {
        this.parent = parent;
        this.gameEngine = gameEngine;

        this.render();
    }

    public void render(){
        dialog = new JDialog(parent, "New Player");
        this.createPanel();
        dialog.setSize(1000, 200);
        dialog.add(panel);
        dialog.setVisible(true);
    }

    public void createPanel(){
        panel = new JPanel();

        JTextField name = new JTextField(10);
        JLabel nameLabel = new JLabel("Name: ");
        panel.add(nameLabel);
        panel.add(name);

        JTextField points = new JTextField(10);
        JLabel pointsLabel = new JLabel("Points: ");
        panel.add(pointsLabel);
        panel.add(points);

        panel.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        JButton createPlayer = new JButton("Add");
        createPlayer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<String> errors = new ArrayList<>();
                if(name.getText().isBlank()){
                    errors.add("Name is required");
                }
                if(points.getText().isBlank()){
                    errors.add("Points is required");
                }
                if(errors.isEmpty()) {
                    try {
                        Player newPlayer = new SimplePlayer("1", name.getText(), Integer.valueOf(points.getText()));
                        gameEngine.addPlayer(newPlayer);
                        dialog.setVisible(false);
                        parent.render();
                    } catch (NumberFormatException numberFormat) {
                        errors.add("Bet must be of type Integer");
                        ErrorDialog errorDialog = new ErrorDialog(parent, errors);
                    }
                }
                else{
                    ErrorDialog errorDialog = new ErrorDialog(parent,errors );
                }

            }
        });


        panel.add(createPlayer);
    }
}
