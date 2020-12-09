/*
 * Michael Caldwell, 200445010, 2020/12/08
 * Adv Java (Section 1) - Assignment 2
 */

package Utilities;

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
     * @param viewName a filename such as "UnitCardTableView.fxml"
     * @param title as in a program title/name like Microsoft Word
     */
    public static void changeScene(ActionEvent event, String viewName, String title) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(new Object(){}.getClass().getResource(viewName));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        scene.getStylesheets().add("styles.css");
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle(title);
        //stage.getIcons().add(new Image("file:resources/images/icon.png"));
        stage.show();
    }
}
