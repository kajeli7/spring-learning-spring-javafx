package com.lfftechnologies.learning.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import org.springframework.stereotype.Component;

@Component
public class LoggerTabController {

    @FXML private TextArea loggerTxtArea;

    public TextArea getLoggerTxtArea() {
        return loggerTxtArea;
    }
    
}
