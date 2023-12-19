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
import teacher.TeacherDashboardFXMLController;


public class TeacherDetailsFXMLController implements Initializable {

    @FXML
    private Label alertText;
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField institutionField;
    @FXML
    private TextField departmentField;
    @FXML
    private TextField designationField;
    @FXML
    private TextField contactNoField;
    @FXML
    private JFXButton doneButton;
    
    static String userEmail;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        firstNameField.setFocusTraversable(false);
        lastNameField.setFocusTraversable(false);
        institutionField.setFocusTraversable(false);
        departmentField.setFocusTraversable(false);
        designationField.setFocusTraversable(false);
        contactNoField.setFocusTraversable(false);
        doneButton.setFocusTraversable(false);
        
        firstNameField.setText("");
        lastNameField.setText("");
        institutionField.setText("");
        departmentField.setText("");
        designationField.setText("");
        contactNoField.setText("");
        alertText.setVisible(false);
        
    }
    
    public static void getUserEmail(String email){
        TeacherDetailsFXMLController.userEmail = email;
    }
    
    @FXML
    private void doneButtonPushed(ActionEvent event) throws IOException, SQLException{
        
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String institution = institutionField.getText();
        String department = departmentField.getText();
        String designation = designationField.getText();
        String contactNo = contactNoField.getText();
        
        if(firstName.equals("") || lastName.equals("") || institution.equals("")|| department.equals("") || designation.equals("") || contactNo.equals("")) {
            alertText.setVisible(true);
            return;
        }
        
        TeacherDetails td = new TeacherDetails(userEmail, firstName + " " + lastName, institution, department, designation, contactNo);
        
        Database db = new Database();     
        db.insertTeacherDetails(td);
        
        TeacherDashboardFXMLController.getUserEmail(userEmail);
        
        Parent dashboardParent = FXMLLoader.load(getClass().getResource("/teacher/TeacherDashboardFXML.fxml"));
        Scene dashboardScene = new Scene(dashboardParent);
        Stage mainWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
        mainWindow.setScene(dashboardScene);
        mainWindow.show();
        
    }

}
