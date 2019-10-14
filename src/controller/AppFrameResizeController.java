package controller;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.AppFrame;
import view.summary.SpinnerView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.Collection;

public class AppFrameResizeController implements ComponentListener {
    private SpinnerView spinnerView;

    public AppFrameResizeController(SpinnerView spinnerView) {
        this.spinnerView = spinnerView;
    }

    @Override
    public void componentResized(ComponentEvent e) {
        AppFrame test = (AppFrame) e.getSource();
        spinnerView.resizeCoins(test.getSize());
    }

    @Override
    public void componentMoved(ComponentEvent e) {

    }

    @Override
    public void componentShown(ComponentEvent e) {

    }

    @Override
    public void componentHidden(ComponentEvent e) {

    }
}
