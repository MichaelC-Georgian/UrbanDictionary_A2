/*
 * Michael Caldwell, 200445010, 2020/12/08
 * Adv Java (Section 1) - Assignment 2
 */
package Controllers;

import Models.Definition;
import Utilities.SceneChanger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.awt.*;
import java.io.Console;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.stage.Stage;

public class DefinitionViewController implements Initializable {


    @FXML
    private Label titleLabel;

    @FXML
    private Button returnBtn;

    @FXML
    private Label wordLabel;

    @FXML
    private Label authorAndDateLabel;

    @FXML
    private Hyperlink permalinkHL;

    @FXML
    private TextArea definitionTextArea;

    @FXML
    private TextArea usageTextArea;

    @FXML
    void switchScene(ActionEvent event) throws IOException {
        SceneChanger.changeScene(event, "/Views/SearchUrbanDictionaryView.fxml", "Search");
    }

    /***
     * Upon clicking the hyperlink element in the DefinitionView, open the link in the default browser.
     * @param event the event that triggered this action/method.
     */
    @FXML
    void openLink(ActionEvent event) {
        try {
            Desktop.getDesktop().browse(new URI(permalinkHL.getText()));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        //Wait until the platform has loaded, prevents non null values from appearing to be null
        Platform.runLater(()->{
            Stage stage = (Stage) titleLabel.getScene().getWindow(); //get stage from a element
            Definition selectedItem = (Definition) stage.getUserData(); //Get selectedItem from stored userDate

            wordLabel.setText(selectedItem.getWord()); //Set word label appropriately

            definitionTextArea.setText(selectedItem.getDefinition()); //Set definition test area appropriately
            usageTextArea.setText(selectedItem.getExample()); //Set usage text area appropriately

            String credit = "By " + selectedItem.getAuthor() + " on " + selectedItem.getYMD(); //Prep label as it contains multiple values
            authorAndDateLabel.setText(credit); //Set author and date label with prepared text

            permalinkHL.setText(selectedItem.getPermalink()); //set permalink text.
        });

        //For some reason the above code requires itself to be done in a lambda expression... Not sure why.

    }
}
