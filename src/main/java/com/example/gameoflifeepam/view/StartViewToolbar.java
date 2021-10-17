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


    private final Button setParamsButton;
    private final TextField epochsField;
    private final TextField sizeGridXField;
    private final TextField sizeGridYField;
    private final TextField timeOfFrameField;
    private final Label epochsLabel;
    private final Label sizeGridXLabel;
    private final Label sizeGridYLabel;
    private final Label timeOfFrameLabel;
    private final Label errorLabel;

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

        this.getItems().addAll(epochsLabel,
                epochsField,
                sizeGridXLabel,
                sizeGridXField,
                sizeGridYLabel,
                sizeGridYField,
                timeOfFrameLabel,
                timeOfFrameField,
                errorLabel,
                setParamsButton);
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



