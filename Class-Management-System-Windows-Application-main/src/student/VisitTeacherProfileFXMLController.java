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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class VisitTeacherProfileFXMLController implements Initializable {

    @FXML
    private TextField nameField;
    @FXML
    private TextField institutionField;
    @FXML
    private TextField departmentField;
    @FXML
    private TextField contactNoField;
    @FXML
    private JFXButton backButton;
    @FXML
    private TextField emailField;
    @FXML
    private TextField designationField;
    
    static String teacherEmail;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        nameField.setFocusTraversable(false);
        institutionField.setFocusTraversable(false);
        departmentField.setFocusTraversable(false);
        designationField.setFocusTraversable(false);
        contactNoField.setFocusTraversable(false);
        emailField.setFocusTraversable(false);
        backButton.setFocusTraversable(false);
        
        TeacherDetails td;
        try {
            Database db = new Database();
            td = db.readTeacherDetails(teacherEmail);
            
            nameField.setText(td.getName());
            institutionField.setText(td.getInstitution());
            departmentField.setText(td.getDepartment());
            designationField.setText(td.getDesignation());
            contactNoField.setText(td.getContactNo());
            emailField.setText(td.getEmail());
            
        } catch (SQLException ex) {
            Logger.getLogger(VisitTeacherProfileFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public static void getTeacherEmail(String email){
        VisitTeacherProfileFXMLController.teacherEmail = email;
    }

    @FXML
    private void backButtonPushed(ActionEvent event) throws IOException {
        
        Parent classroomsParent = FXMLLoader.load(getClass().getResource("StudentClassroomsFXML.fxml"));
        Scene classroomsScene = new Scene(classroomsParent);
        Stage mainWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
        mainWindow.setScene(classroomsScene);
    }
    
}
