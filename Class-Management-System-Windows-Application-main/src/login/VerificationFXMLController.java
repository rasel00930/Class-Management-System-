package login;

import database.*;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class VerificationFXMLController implements Initializable {

    @FXML
    private Label codeError;
    @FXML
    private TextField signUpOTPField;
    @FXML
    private JFXButton confirmButton;
    
    static Stage mainWindow;
    static String verificationCode;
    static Account ac;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        signUpOTPField.setFocusTraversable(false);
        confirmButton.setFocusTraversable(false);
        
        codeError.setText("");
    }    
    
    public static void getVerificationCode(String verificationCode, Stage mainWindow){
        VerificationFXMLController.verificationCode = verificationCode;
        VerificationFXMLController.mainWindow = mainWindow;
    }
    
    public static void getAccountInfo(Account ac){
        VerificationFXMLController.ac = ac;
    }
    
    @FXML
    private void confirmButton(ActionEvent event) throws IOException, SQLException {
        String userInput = signUpOTPField.getText();
        
        if(userInput.equals("")){
            codeError.setText("Code field empty!");
            return;
        }
        
        if(!userInput.equals(verificationCode)){
            codeError.setText("Invalid Code!");
            return;
        }
        
        Database db = new Database();
        db.createAccount(ac.getEmail(), ac.getPassword(), ac.getAccountType());
        
        if(ac.getAccountType().equals("teacher")){

            TeacherDetailsFXMLController.getUserEmail(ac.getEmail());
            
            Stage verificationWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
            verificationWindow.close();

            Parent detailsParent = FXMLLoader.load(getClass().getResource("/login/TeacherDetailsFXML.fxml"));
            Scene detailsScene = new Scene(detailsParent);
            mainWindow.setScene(detailsScene);
        }
        else if(ac.getAccountType().equals("student")){
            
            StudentDetailsFXMLController.getUserEmail(ac.getEmail());
            
            Stage verificationWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
            verificationWindow.close();

            Parent detailsParent = FXMLLoader.load(getClass().getResource("/login/StudentDetailsFXML.fxml"));
            Scene detailsScene = new Scene(detailsParent);
            mainWindow.setScene(detailsScene);
        }
        
    }
    
}
