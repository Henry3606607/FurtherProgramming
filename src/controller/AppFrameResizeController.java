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
        AppFrame test = (AppFrame) e.getSource();
        System.out.println(test.getSize());
        spinnerView.resizeCoins(test.getSize());
        toolBar.resizeToolbar(test.getSize());
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
