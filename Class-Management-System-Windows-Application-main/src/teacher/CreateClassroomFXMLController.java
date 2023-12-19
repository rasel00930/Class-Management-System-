package teacher;

import database.*;
import security.RandomCode;

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


public class CreateClassroomFXMLController implements Initializable {

    @FXML
    private JFXButton createButton;
    @FXML
    private TextField classroomNameField;
    @FXML
    private TextField courseNameField;
    @FXML
    private Label classroomNameError;
    @FXML
    private Label courseNameError;
    
    static Stage mainWindow;
    static String userEmail;
    ArrayList<Classroom> classroomList;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        classroomNameField.setFocusTraversable(false);
        courseNameField.setFocusTraversable(false);
        createButton.setFocusTraversable(false);
        
        classroomNameError.setVisible(false);
        courseNameError.setVisible(false);
        
        try {
            Database db = new Database();
            classroomList = db.getAllClassrooms();
        } catch (SQLException ex) {
            Logger.getLogger(CreateClassroomFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }    
    
    public static void getUserEmail(String email, Stage mainWindow){
        CreateClassroomFXMLController.userEmail = email;
        CreateClassroomFXMLController.mainWindow = mainWindow;
    }
    
    @FXML
    private void createButtonPushed(ActionEvent event) throws SQLException, IOException {
        
        String classroomName = classroomNameField.getText();
        String courseName = courseNameField.getText();
        
        if(classroomName.equals("")){
            classroomNameError.setVisible(true);
            return;
        }
        if(courseName.equals("")){
            courseNameError.setVisible(true);
            return;
        }
        
        String classroomCode;
        while(true){
            boolean matched = false;
            classroomCode = RandomCode.getRandomCode();
            
            for(Classroom classroom:classroomList){
                if(classroom.getClassroomCode().equals(classroomCode)){
                    matched = true;
                    break;
                }
            }
            if(!matched){
                break;
            }
        }
        
        Classroom croom = new Classroom(classroomCode, classroomName, courseName, userEmail, "");
        
        Database db = new Database();
        db.createClassroom(croom);
        
        Stage createClassroomWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
        createClassroomWindow.close();
        
        TeacherClassroomsFXMLController.getUserEmail(userEmail);
        TeacherClassroomsFXMLController.getClassroomIndex(-1);
        
        Parent classroomsParent = FXMLLoader.load(getClass().getResource("/teacher/TeacherClassroomsFXML.fxml"));
        Scene classroomsScene = new Scene(classroomsParent);
        mainWindow.setScene(classroomsScene);
    }
    
}
