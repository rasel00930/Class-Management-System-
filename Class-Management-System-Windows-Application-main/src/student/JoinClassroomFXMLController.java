package student;

import database.*;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
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
import javafx.stage.Stage;

public class JoinClassroomFXMLController implements Initializable {

    @FXML
    private TextField codeField;
    @FXML
    private JFXButton joinButton;
    @FXML
    private Label codeError;
    
    static Stage mainWindow;
    static String userEmail;
    ArrayList<Classroom> classroomList;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        codeField.setFocusTraversable(false);
        joinButton.setFocusTraversable(false);
        
        codeError.setText("");
        
        try {
            Database db = new Database();
            classroomList = db.getAllClassrooms();
        } catch (SQLException ex) {
            Logger.getLogger(JoinClassroomFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    public static void getUserEmail(String email, Stage mainWindow){
        JoinClassroomFXMLController.userEmail = email;
        JoinClassroomFXMLController.mainWindow = mainWindow;
    }
    
    @FXML
    private void joinButtonPushed(ActionEvent event) throws SQLException, IOException {
        
        String classroomCode = codeField.getText();
        
        if(classroomCode.equals("")){
            codeError.setText("Code field empty!");
            return;
        }
        
        boolean found = false;
        for(Classroom c:classroomList){
            if(c.getClassroomCode().equals(classroomCode)){
                found = true;
                break;
            }
        }
        if(!found){
            codeError.setText("Classroom not found!");
            return;
        }
        
        Database db = new Database();
        ArrayList<StudentAccount> acList = db.getAllStudentAccountsOfaClassroom(classroomCode);
        boolean matched = false;
        for(StudentAccount ac:acList){
            if(ac.getEmail().equals(userEmail)){
                matched = true;
                break;
            }
        }
        if(matched){
            codeError.setText("You're already in this classroom!");
            return;
        }
        
        StudentAccount studentAC = new StudentAccount(userEmail, db.getStudentAccountID(userEmail));
        db.joinClassroom(classroomCode, studentAC);
        
        Stage joinClassroomWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
        joinClassroomWindow.close();
        
        StudentClassroomsFXMLController.getUserEmail(userEmail);
        StudentClassroomsFXMLController.getClassroomIndex(-1);
        
        Parent classroomsParent = FXMLLoader.load(getClass().getResource("/student/StudentClassroomsFXML.fxml"));
        Scene classroomsScene = new Scene(classroomsParent);
        mainWindow.setScene(classroomsScene);
    }
    
}
