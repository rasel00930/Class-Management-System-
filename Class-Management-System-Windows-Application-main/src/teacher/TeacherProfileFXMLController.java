package teacher;

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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class TeacherProfileFXMLController implements Initializable {

    @FXML
    private TextField nameField;
    @FXML
    private TextField institutionField;
    @FXML
    private TextField departmentField;
    @FXML
    private TextField contactNoField;
    @FXML
    private JFXButton editButton;
    @FXML
    private JFXButton backButton;
    @FXML
    private TextField emailField;
    @FXML
    private Label savedText;
    @FXML
    private TextField designationField;
    
    static String userEmail;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        nameField.setFocusTraversable(false);
        institutionField.setFocusTraversable(false);
        departmentField.setFocusTraversable(false);
        designationField.setFocusTraversable(false);
        contactNoField.setFocusTraversable(false);
        emailField.setFocusTraversable(false);
        editButton.setFocusTraversable(false);
        backButton.setFocusTraversable(false);
        
        editButton.setText("Edit");
        savedText.setVisible(false);
        
        TeacherDetails td;
        try {
            Database db = new Database();
            td = db.readTeacherDetails(userEmail);
            
            nameField.setText(td.getName());
            institutionField.setText(td.getInstitution());
            departmentField.setText(td.getDepartment());
            designationField.setText(td.getDesignation());
            contactNoField.setText(td.getContactNo());
            emailField.setText(td.getEmail());
            
        } catch (SQLException ex) {
            Logger.getLogger(TeacherProfileFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public static void getUserEmail(String email){
        TeacherProfileFXMLController.userEmail = email;
    }

    @FXML
    private void backButtonPushed(ActionEvent event) throws IOException {
        
        Parent teacherDashboard = FXMLLoader.load(getClass().getResource("TeacherDashboardFXML.fxml"));
        Scene dashboardScene = new Scene(teacherDashboard);
        Stage mainWindow = (Stage) backButton.getScene().getWindow();
        mainWindow.setScene(dashboardScene);
    }
    
    @FXML
    private void editButtonPushed(ActionEvent event) throws SQLException {
        
        if(editButton.getText().equals("Edit")){
            
            editButton.setText("Save");
            savedText.setVisible(false);
            
            nameField.setEditable(true);
            institutionField.setEditable(true);
            departmentField.setEditable(true);
            designationField.setEditable(true);
            contactNoField.setEditable(true);
        }
        else{
            editButton.setText("Edit");
            savedText.setVisible(true);
            
            nameField.setEditable(false);
            institutionField.setEditable(false);
            departmentField.setEditable(false);
            designationField.setEditable(false);
            contactNoField.setEditable(false);
            
            TeacherDetails td = new TeacherDetails(userEmail, nameField.getText(), institutionField.getText(), departmentField.getText(), designationField.getText(), contactNoField.getText());
            Database db = new Database();
            db.editTeacherDetails(td);
        }
    }
    
}
