package com.example.gameoflifeepam.view;

import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;

public class MainViewToolbar extends ToolBar {
    static final String START_BUTTON_TEXT = "Start";
    static final String STOP_BUTTON_TEXT = "Stop";
    static final String CLEAR_BUTTON_TEXT = "Clear";

    private final Button start;
    private final Button stop;
    private final Button clear;

    public MainViewToolbar() {
        start = new Button(START_BUTTON_TEXT);
        stop = new Button(STOP_BUTTON_TEXT);
        clear = new Button(CLEAR_BUTTON_TEXT);
        this.getItems().addAll(start, stop, clear);
    }

    public Button getStart() {
        return start;
    }

    public Button getStop() {
        return stop;
    }

    public Button getClear() {
        return clear;
    }
}