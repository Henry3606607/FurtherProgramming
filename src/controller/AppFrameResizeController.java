package controller;

import view.AppFrame;
import view.summary.SpinnerView;
import view.toolbar.ToolBar;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class AppFrameResizeController implements ComponentListener {
    private SpinnerView spinnerView;
    private ToolBar toolBar;

    public AppFrameResizeController(SpinnerView spinnerView, ToolBar toolBar) {
        this.spinnerView = spinnerView;
        this.toolBar = toolBar;
    }

    @Override
    public void componentResized(ComponentEvent e) {
        AppFrame a = (AppFrame) e.getSource();
        spinnerView.resizeCoins(a.getSize());
        toolBar.resizeToolbar(a.getSize());
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
