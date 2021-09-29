package com.example.gameoflifeepam.view;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;

public class Toolbar extends ToolBar {
public Button start,stop,clear,step;
    public Toolbar() {
         start = new Button("Start");
         stop = new Button("Stop");
         clear = new Button("Clear");
       //  step = new Button("Step");
        this.getItems().addAll(start,stop, clear);
    }
    }
