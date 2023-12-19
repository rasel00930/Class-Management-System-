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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class VisitStudentProfileFXMLController implements Initializable {
    
    @FXML
    private JFXButton backButton;
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
    
    static String studentEmail;
    
    
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
        backButton.setFocusTraversable(false);
        
        
        StudentDetails sd;
        try {
            Database db = new Database();
            sd = db.readStudentDetails(studentEmail);
            
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
            Logger.getLogger(VisitStudentProfileFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void getUserEmail(String email){
        VisitStudentProfileFXMLController.studentEmail = email;
    }
    
    @FXML
    public void backButtonPushed(ActionEvent event) throws IOException{

        Parent studentsParent = FXMLLoader.load(getClass().getResource("StudentsFXML.fxml"));
        Scene studentsScene = new Scene(studentsParent);
        Stage mainWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
        mainWindow.setScene(studentsScene);
    }
     
}
