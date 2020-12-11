/*
 * Michael Caldwell, 200445010, 2020/12/08
 * Adv Java (Section 1) - Assignment 2
 */

package Controllers;

import Models.Definition;
import Models.UrbanDictionaryResponse;
import Utilities.APIUtility;
import Utilities.SceneChanger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import static Utilities.SceneChanger.changeScene;

public class SearchUrbanDictionaryViewController implements Initializable {
    Definition selectedItem;

    @FXML
    private TextField searchTextField;

    @FXML
    private Button searchBtn;

    @FXML
    private ListView<Definition> definitionListView;

    @FXML
    private Label rowsReturnedLabel;

    @FXML
    private Label errorLabel;

    @FXML
    private Button detailsBtn;


    /**
     * When the search button is pressed on the view
     * call this method, which calls the urban dictionary and searches the
     * entered term and then populates the listview
     */
    @FXML
    private void getDefinitions() {
        //Set searchText to the contents of the text field
        String searchText = searchTextField.getText();

        if (!searchText.trim().isBlank()) {
            //Clear the definition ListView
            definitionListView.getItems().clear();

            //If nothing or only spaces are entered into the searchText, show error message. Otherwise, execute below code.
            try {
                //Get response data by calling API with searchText
                UrbanDictionaryResponse response = APIUtility.callUrbanDictionary(searchText);

                //Convert the response to an array list.
                List<Definition> definitions = Arrays.asList(response.getList());

                //Add all array instances to the ListView
                definitionListView.getItems().addAll(definitions);

                //State the number of definitions found.
                rowsReturnedLabel.setText("Definitions Found: " + String.valueOf(definitions.size()));

            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            errorLabel.setText("Please enter a search term first");
        }
    }

    @FXML
    void getSelection(MouseEvent event) {
        //Upon making a selection, set the selectedItem so the details button can be pressed.
        selectedItem = definitionListView.getSelectionModel().getSelectedItem();

        System.out.println(selectedItem);
    }

    /***
     * When called, change the scene to the Definition View
     * @param event That event responsible for calling this method, should be a click on the detailsBtn
     * @throws IOException If there's an error finding or reading the file, this error will be thrown.
     */
    @FXML
    void switchScene(ActionEvent event) throws IOException {

        //Set selectedItem based on what's selected in the list view
        selectedItem = definitionListView.getSelectionModel().getSelectedItem();

        //If an item is  selected, change to the definition view and pass the selected item to it.
        //Else, ask user to select an item.
        if (!(selectedItem == null)) {
            errorLabel.setText("");

            SceneChanger.changeScene(event, "/Views/DefinitionView.fxml", "Definition View", selectedItem);
        } else {
            errorLabel.setText("Please select an item first");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Make conditional labels blank until used.
        rowsReturnedLabel.setText("");
        errorLabel.setText("");

    }
}
