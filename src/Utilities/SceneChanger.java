/*
 * Michael Caldwell, 200445010, 2020/12/08
 * Adv Java (Section 1) - Assignment 2
 */

package Utilities;

import Models.Definition;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneChanger {

    /**
     * Changes to a different view/scene based on params
     * @param event must be an ActionEvent
     * @param viewName a filename such as "/Views/DefinitionView.fxml"
     * @param title as in a program title/name like Microsoft Word
     */
    public static void changeScene(ActionEvent event, String viewName, String title) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(new Object(){}.getClass().getResource(viewName));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/Views/styles.css");
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle(title);
        //stage.getIcons().add(new Image("file:resources/images/icon.png"));
        stage.show();
    }

    /***
     * An overloaded ChangeScene method that changes to a different view/scene and passes a single definition object.
     * @param event must be an ActionEvent
     * @param viewName a filename such as "/Views/DefinitionView.fxml"
     * @param title as in a program title/name like Microsoft Word
     * @param selectedItem a definition object used to pass information onto the next scene
     * @throws IOException if there are issues with the files this error will be thrown.
     */
    public static void changeScene(ActionEvent event, String viewName, String title, Definition selectedItem) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(new Object(){}.getClass().getResource(viewName));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/Views/styles.css");
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        // Credit to https://dev.to/devtony101/javafx-3-ways-of-passing-information-between-scenes-1bm8 for intro to setUserData
        stage.setUserData(selectedItem); //Saves data to an accessible location
        stage.setScene(scene);
        stage.setTitle(title);
        //stage.getIcons().add(new Image("file:resources/images/icon.png"));
        stage.show();
    }
}
