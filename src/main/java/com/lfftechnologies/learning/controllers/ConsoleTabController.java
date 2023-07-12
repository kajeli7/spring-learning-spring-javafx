package com.lfftechnologies.learning.controllers;

import com.lfftechnologies.learning.services.MissionsService;
import java.io.IOException;
import java.io.PrintWriter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Lazy;


@Component
public class ConsoleTabController {

    @FXML private TextArea missionOverviewText;
    @FXML private ListView<String> missionsList;
    
    @Autowired @Qualifier("stringPrintWriter")
    private PrintWriter stackTraceWriter;
    
    @Autowired MissionsService service;
    private TabPaneManger tabManager;

    public void initialize() {
        ObservableList<String> missions = FXCollections.observableArrayList("Apollo", "Shuttle", "Skylab");
        missionsList.setItems(missions);
    }

    @FXML
    private void onMouseClicked(MouseEvent event) {
        missionOverviewText.clear();
        final String selectedItem = missionsList.getSelectionModel().getSelectedItem();
        missionOverviewText.positionCaret(0);
        missionOverviewText.appendText(getInfo(selectedItem));
    }
    
    @Autowired
    private void setTabManager(@Lazy TabPaneManger tabManager){
        this.tabManager = tabManager;
    }
 
    public String getInfo(String selectedItem) {
        String missionInfo = null ;
                
        try {
            missionInfo = service.getMissionInfo(selectedItem); 
            getLog().appendText("Sucessfully retrieved mission info for " + selectedItem + "\n");
        } catch (IOException exception) {
            exception.printStackTrace (stackTraceWriter);
            getLog().appendText(stackTraceWriter.toString() + "\n");
        }
        
        return missionInfo;
    }

    public TextArea getMissionOverviewText() {
        return missionOverviewText;
    }

    public ListView<String> getMissionsList() {
        return missionsList;
    }
    
    private TextArea getLog(){
        return tabManager.getVisualLog();
    }

}
