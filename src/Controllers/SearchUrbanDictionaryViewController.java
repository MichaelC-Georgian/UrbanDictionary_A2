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
    private void getDefinitions(){

        String searchText = searchTextField.getText();
        definitionListView.getItems().clear();

        try {
            UrbanDictionaryResponse response = APIUtility.callUrbanDictionary(searchText);

            List<Definition> definitions = Arrays.asList(response.getList());

            definitionListView.getItems().addAll(definitions);

            rowsReturnedLabel.setText(String.valueOf(definitions.size()));

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void getSelection(MouseEvent event) {
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
        selectedItem = definitionListView.getSelectionModel().getSelectedItem();
        System.out.println(selectedItem);
        if (!(selectedItem == null)) {
            errorLabel.setText("");



            SceneChanger.changeScene(event, "/Views/DefinitionView.fxml", "Definition View", selectedItem);
        }
        else
            {
            errorLabel.setText("Please select an item first");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Make conditional sections blank.
        rowsReturnedLabel.setText("");
        errorLabel.setText("");

//        movieListView.getSelectionModel().selectedItemProperty().addListener(
//                (obs, oldValue, movieSelected) -> {
//                    movieImageView.setImage(new Image(movieSelected.getPoster()));
//                }
//        );
    }
}
