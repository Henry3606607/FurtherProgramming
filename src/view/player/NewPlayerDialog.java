package view.player;

import controller.AddNewPlayerController;
import model.interfaces.GameEngine;
import view.AppFrame;
import view.player.PlayerPanel;

import javax.swing.*;
import java.awt.*;

public class NewPlayerDialog {

    private AppFrame appFrame;
    private GameEngine gameEngine;
    private JDialog dialog;
    private JPanel panel;
    private JTextField points;
    private JTextField name;

    public NewPlayerDialog(AppFrame appFrame, GameEngine gameEngine) {
        this.gameEngine = gameEngine;
        this.appFrame = appFrame;
        this.render();
    }

    public void render(){
        dialog = new JDialog(appFrame, "New Player");
        this.createPanel();
        dialog.setSize(500, 200);
        dialog.setLocationRelativeTo(null);
        dialog.add(panel);
        dialog.setVisible(true);
    }

    public void createPanel(){
        panel = new JPanel();

        name = new JTextField(10);
        JLabel nameLabel = new JLabel("Name: ");
        panel.add(nameLabel);
        panel.add(name);

        points = new JTextField(10);
        JLabel pointsLabel = new JLabel("Points: ");
        panel.add(pointsLabel);
        panel.add(points);

        panel.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        JButton createPlayer = new JButton("Add");
        createPlayer.addActionListener(new AddNewPlayerController(gameEngine, this, appFrame, dialog));

        panel.add(createPlayer);
    }

    public JTextField getPoints() {
        return points;
    }

    public void setPoints(JTextField points) {
        this.points = points;
    }

    public JTextField getName() {
        return name;
    }

    public void setName(JTextField name) {
        this.name = name;
    }
}
