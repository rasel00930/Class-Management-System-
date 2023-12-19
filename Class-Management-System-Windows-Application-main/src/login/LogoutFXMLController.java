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
import javafx.stage.Stage;


public class LogoutFXMLController implements Initializable {

    @FXML
    private JFXButton yesButton;
    @FXML
    private JFXButton noButton;
    
    static Stage mainWindow;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        yesButton.setFocusTraversable(false);
        noButton.setFocusTraversable(false);
    }
    
    public static void getMainWindow(Stage mainWindow){
        LogoutFXMLController.mainWindow = mainWindow;
    }

    @FXML
    private void yesButtonPushed(ActionEvent event) throws IOException {
        
        Stage logoutWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
        logoutWindow.close();
        
        Parent loginParent = FXMLLoader.load(getClass().getResource("LoginFXML.fxml"));
        Scene loginScene = new Scene(loginParent);
        mainWindow.setScene(loginScene);
        mainWindow.show();
    }

    @FXML
    private void noButtonPushed(ActionEvent event) {
        
        Stage logoutWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
        logoutWindow.close();
    }
    
}
