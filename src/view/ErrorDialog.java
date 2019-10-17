package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ErrorDialog {

    private ArrayList<String> errors;
    private AppFrame parent;
    private JDialog dialog;
    private JPanel panel;

    public ErrorDialog(AppFrame parent, ArrayList<String> errors) {
        this.errors = errors;
        this.parent = parent;

        this.render();
    }

    public void render(){
        dialog = new JDialog(parent, "Error");
        this.createPanel();
        dialog.setLocationRelativeTo(null);
        dialog.setSize(500, 200);
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
