package login;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class AboutUsFXMLController implements Initializable {

    @FXML
    private AnchorPane upperPane;
    @FXML
    private JFXButton backButton;
    @FXML
    private Hyperlink link;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        backButton.setFocusTraversable(false);
        link.setFocusTraversable(false);
        link.setUnderline(false);
    }    

    @FXML
    private void backButtonPushed(ActionEvent event) throws IOException {
        Parent loginParent = FXMLLoader.load(getClass().getResource("/login/LoginFXML.fxml"));
        Scene loginScene = new Scene(loginParent);
        Stage mainWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
        mainWindow.setScene(loginScene);
    }

    @FXML
    private void linkOnAction(ActionEvent event) {
        ClassManagement a = new ClassManagement();
        a.getHostServices().showDocument("mailto:" + link.getText());
    }
    
}
