package student;

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


public class StudentProfileFXMLController implements Initializable {
    
    @FXML
    private JFXButton backButton;
    @FXML
    private JFXButton editButton;
    @FXML
    private TextField nameField;
    @FXML
    private TextField yearField;
    @FXML
    private TextField institutionField;
    @FXML
    private TextField departmentField;
    @FXML
    private TextField semesterField;
    @FXML
    private TextField contactNoField;
    @FXML
    private TextField sectionField;
    @FXML
    private TextField idField;
    @FXML
    private TextField emailField;
    @FXML
    private Label savedText;
    
    static String userEmail;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        nameField.setFocusTraversable(false);
        institutionField.setFocusTraversable(false);
        departmentField.setFocusTraversable(false);
        yearField.setFocusTraversable(false);
        semesterField.setFocusTraversable(false);
        contactNoField.setFocusTraversable(false);
        sectionField.setFocusTraversable(false);
        idField.setFocusTraversable(false);
        emailField.setFocusTraversable(false);
        editButton.setFocusTraversable(false);
        backButton.setFocusTraversable(false);
        
        editButton.setText("Edit");
        savedText.setVisible(false);
        
        StudentDetails sd;
        try {
            Database db = new Database();
            sd = db.readStudentDetails(userEmail);
            
            nameField.setText(sd.getName());
            institutionField.setText(sd.getInstitution());
            departmentField.setText(sd.getDepartment());
            yearField.setText(sd.getYear());
            semesterField.setText(sd.getSemester());
            contactNoField.setText(sd.getContactNo());
            sectionField.setText(sd.getSection());
            idField.setText(sd.getId());
            emailField.setText(sd.getEmail());
            
        } catch (SQLException ex) {
            Logger.getLogger(StudentProfileFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void getUserEmail(String email){
        StudentProfileFXMLController.userEmail = email;
    }
    
    @FXML
    public void backButtonPushed(ActionEvent event) throws IOException{

        Parent studentDashboard = FXMLLoader.load(getClass().getResource("StudentDashboardFXML.fxml"));
        Scene dashboardScene = new Scene(studentDashboard);
        Stage mainWindow = (Stage) backButton.getScene().getWindow();
        mainWindow.setScene(dashboardScene);
        
    }
    
    @FXML
    public void editButtonPushed(ActionEvent event) throws SQLException{
        
        if(editButton.getText().equals("Edit")){
            
            editButton.setText("Save");
            savedText.setVisible(false);
            
            nameField.setEditable(true);
            institutionField.setEditable(true);
            departmentField.setEditable(true);
            yearField.setEditable(true);
            semesterField.setEditable(true);
            contactNoField.setEditable(true);
            sectionField.setEditable(true);
            idField.setEditable(true);
        }
        else{
            editButton.setText("Edit");
            savedText.setVisible(true);
            
            nameField.setEditable(false);
            institutionField.setEditable(false);
            departmentField.setEditable(false);
            yearField.setEditable(false);
            semesterField.setEditable(false);
            contactNoField.setEditable(false);
            sectionField.setEditable(false);
            idField.setEditable(false);
            
            StudentDetails sd = new StudentDetails(userEmail, nameField.getText(), idField.getText(), institutionField.getText(), departmentField.getText(), yearField.getText(), semesterField.getText(), sectionField.getText(), contactNoField.getText());
            Database db = new Database();
            db.editStudentDetails(sd);
        }
        
    }
    
}
