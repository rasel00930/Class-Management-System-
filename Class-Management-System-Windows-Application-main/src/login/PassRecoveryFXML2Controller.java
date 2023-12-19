package login;

import database.*;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import student.StudentDashboardFXMLController;
import teacher.TeacherDashboardFXMLController;

public class PassRecoveryFXML2Controller implements Initializable {

    @FXML
    private TextField newPassField;
    @FXML
    private TextField confirmPassField;
    @FXML
    private Label passConditionText1;
    @FXML
    private Label passConditionText2;
    @FXML
    private Label passConditionText3;
    @FXML
    private JFXButton confirmButton;
    
    static Stage mainWindow;
    static Account userAC;
    boolean lenthError;
    boolean charError;
    boolean matchError;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        newPassField.setFocusTraversable(false);
        confirmPassField.setFocusTraversable(false);
        confirmButton.setFocusTraversable(false);
        
        passConditionText1.setTextFill(Paint.valueOf("Red"));
        passConditionText2.setTextFill(Paint.valueOf("Red"));
        passConditionText3.setTextFill(Paint.valueOf("Red"));
        
        lenthError = true;
        charError = true;
        matchError = true;
        
    }    
    
    public static void getAccount(Account ac, Stage mainWindow){
        PassRecoveryFXML2Controller.userAC = ac;
        PassRecoveryFXML2Controller.mainWindow = mainWindow;
    }
    
    @FXML
    private void confirmButton(ActionEvent event) throws IOException, SQLException {
        
        if(lenthError || charError || matchError){
            return;
        }
        
        String password = newPassField.getText();
        
        Database db = new Database();
        db.changePassword(userAC.getEmail(), password);
        
        Stage passRecoveryWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
        passRecoveryWindow.close();
        
        if(userAC.getAccountType().equals("teacher")){
                    
            TeacherDashboardFXMLController.getUserEmail(userAC.getEmail());

            Parent dashboardParent = FXMLLoader.load(getClass().getResource("/teacher/TeacherDashboardFXML.fxml"));
            Scene teacherDashboardScene = new Scene(dashboardParent);
            mainWindow.setScene(teacherDashboardScene);
                    
        }
        else if(userAC.getAccountType().equals("student")){
                    
            StudentDashboardFXMLController.getUserEmail(userAC.getEmail());

            Parent dashboardParent = FXMLLoader.load(getClass().getResource("/student/StudentDashboardFXML.fxml"));
            Scene studentDashboardScene = new Scene(dashboardParent);
            mainWindow.setScene(studentDashboardScene);
        }
        
    }

    @FXML
    private void newPassFieldAction(KeyEvent event) {
        String newPass = newPassField.getText();
        String confirmPass = confirmPassField.getText();
        if(newPass.length() >= 6){
            passConditionText1.setTextFill(Paint.valueOf("Green"));
            lenthError = false;
        }
        else{
            passConditionText1.setTextFill(Paint.valueOf("Red"));
            lenthError = true;
        }
        if(newPass.toLowerCase().matches("(.*[a-z].*)") && newPass.matches("(.*[0-9].*)")){
            passConditionText2.setTextFill(Paint.valueOf("Green"));
            charError = false;
        }
        else{
            passConditionText2.setTextFill(Paint.valueOf("Red"));
            charError = true;
        }
        if(confirmPass.equals(newPass) && !newPass.equals("")){
            passConditionText3.setTextFill(Paint.valueOf("Green"));
            matchError = false;
        }
        else{
            passConditionText3.setTextFill(Paint.valueOf("Red"));
            matchError = true;
        }
    }

    @FXML
    private void confirmPassFieldAction(KeyEvent event) {
        String newPass = newPassField.getText();
        String confirmPass = confirmPassField.getText();
        
        if(confirmPass.equals(newPass) && !newPass.equals("")){
            passConditionText3.setTextFill(Paint.valueOf("Green"));
            matchError = false;
        }
        else{
            passConditionText3.setTextFill(Paint.valueOf("Red"));
            matchError = true;
        }
    }
    
}
