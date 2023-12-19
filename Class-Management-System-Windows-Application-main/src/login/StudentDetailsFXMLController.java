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
import student.StudentDashboardFXMLController;


public class StudentDetailsFXMLController implements Initializable {

    
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
    private TextField yearField;
    @FXML
    private TextField semesterField;
    @FXML
    private TextField sectionField;
    @FXML
    private TextField contactNoField;
    @FXML
    private TextField idField;
    @FXML
    private JFXButton doneButton;
    
    static String userEmail;
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        firstNameField.setFocusTraversable(false);
        lastNameField.setFocusTraversable(false);
        institutionField.setFocusTraversable(false);
        departmentField.setFocusTraversable(false);
        yearField.setFocusTraversable(false);
        semesterField.setFocusTraversable(false);
        sectionField.setFocusTraversable(false);
        idField.setFocusTraversable(false);
        contactNoField.setFocusTraversable(false);
        doneButton.setFocusTraversable(false);
        
        firstNameField.setText("");
        lastNameField.setText("");
        institutionField.setText("");
        departmentField.setText("");
        yearField.setText("");
        semesterField.setText("");
        sectionField.setText("");
        idField.setText("");
        contactNoField.setText("");
        alertText.setVisible(false);
        
    }
    
    public static void getUserEmail(String email){
        StudentDetailsFXMLController.userEmail = email;
    }
    
    @FXML
    private void doneButtonPushed(ActionEvent event) throws IOException, SQLException {
        
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String institution = institutionField.getText();
        String id = idField.getText();
        String department = departmentField.getText();
        String year = yearField.getText();
        String semester = semesterField.getText();
        String section = sectionField.getText();
        String contactNo = contactNoField.getText();
        
        if(firstName.equals("") || lastName.equals("") || institution.equals("")|| department.equals("") || year.equals("") || semester.equals("") || section.equals("") || id.equals("") || contactNo.equals("")) {
            alertText.setVisible(true);
            return;
        }
        
        StudentDetails sd = new StudentDetails(userEmail, firstName + " " + lastName, id, institution, department, year, semester, section, contactNo);
        
        Database db = new Database();     
        db.insertStudentDetails(sd);
        
        StudentDashboardFXMLController.getUserEmail(userEmail);
        
        Parent dashboardParent = FXMLLoader.load(getClass().getResource("/student/StudentDashboardFXML.fxml"));
        Scene dashboardScene = new Scene(dashboardParent);
        Stage mainWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
        mainWindow.setScene(dashboardScene);
        mainWindow.show();
    }

}
