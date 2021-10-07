package com.example.gameoflifeepam.view;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;


public class StartViewToolbar extends ToolBar{
    static final String SET_PARAMS_BUTTON_TEXT = "Start";
    static final String EPOCHS_FIELD_TEXT = "Epochs";
    static final String SIZE_GRID_X_FIELD_TEXT = "Size X";
    static final String SIZE_GRID_Y_FIELD_TEXT = "Size Y";
    static final String TIME_OF_FRAME_FIELD_TEXT = "Time of frame";


    private  Button setParamsButton;
    private  TextField epochsField;
    private  TextField sizeGridXField;
    private  TextField sizeGridYField;
    private  TextField timeOfFrameField;
    private  Label epochsLabel;
    private  Label sizeGridXLabel;
    private  Label sizeGridYLabel;
    private  Label timeOfFrameLabel;
    private  Label errorLabel;

    public StartViewToolbar() {
        setParamsButton = new Button(SET_PARAMS_BUTTON_TEXT);
        epochsField = new TextField();
        sizeGridXField = new TextField();
        sizeGridYField = new TextField();
        timeOfFrameField = new TextField();
        epochsLabel = new Label(EPOCHS_FIELD_TEXT);
        timeOfFrameLabel = new Label(TIME_OF_FRAME_FIELD_TEXT);
        sizeGridXLabel = new Label(SIZE_GRID_X_FIELD_TEXT);
        sizeGridYLabel = new Label(SIZE_GRID_Y_FIELD_TEXT);
        errorLabel = new Label();

        this.getItems().addAll(epochsLabel, epochsField, sizeGridXLabel, sizeGridXField, sizeGridYLabel, sizeGridYField,timeOfFrameLabel,timeOfFrameField,errorLabel, setParamsButton);
    }

    public Button getSetParamsButton() {
        return setParamsButton;
    }

    public TextField getEpochsField() {
        return epochsField;
    }

    public TextField getSizeGridXField() {
        return sizeGridXField;
    }

    public TextField getSizeGridYField() {
        return sizeGridYField;
    }

    public TextField getTimeOfFrameField() {
        return timeOfFrameField;
    }

    public Label getErrorLabel() {
        return errorLabel;
    }
}



