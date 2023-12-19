package student;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import login.LogoutFXMLController;


public class StudentDashboardFXMLController implements Initializable {
    
    @FXML
    private AnchorPane titlePane;
    @FXML
    private AnchorPane profilePane;
    @FXML
    private AnchorPane classroomsPane;
    @FXML
    private AnchorPane joinClassroomPane;
    @FXML
    private JFXButton logoutButton;
    
    static String userEmail;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        logoutButton.setFocusTraversable(false);
        
        profilePane.setOnMouseClicked(e->{
            
            StudentProfileFXMLController.getUserEmail(userEmail);
           
            Parent profileParent = null;
               try {
                   profileParent = FXMLLoader.load(getClass().getResource("StudentProfileFXML.fxml"));
               } catch (IOException ex) {
                   Logger.getLogger(StudentDashboardFXMLController.class.getName()).log(Level.SEVERE, null, ex);
               }
            Scene profileScene = new Scene(profileParent);
            Stage mainWindow = (Stage) profilePane.getScene().getWindow();
            mainWindow.setScene(profileScene);
        });  
        
        classroomsPane.setOnMouseClicked(e->{
            
            StudentClassroomsFXMLController.getUserEmail(userEmail);
            StudentClassroomsFXMLController.getClassroomIndex(-1);
           
            Parent classroomsParent = null;
            try {
                classroomsParent = FXMLLoader.load(getClass().getResource("StudentClassroomsFXML.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(StudentDashboardFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Scene classroomsScene = new Scene(classroomsParent);
            Stage mainWindow = (Stage) classroomsPane.getScene().getWindow();
            mainWindow.setScene(classroomsScene);
        });
        
        joinClassroomPane.setOnMouseClicked(e->{
            
            JoinClassroomFXMLController.getUserEmail(userEmail, (Stage) joinClassroomPane.getScene().getWindow());
            
            Parent JCParent =null;
            try {
                JCParent = FXMLLoader.load(getClass().getResource("JoinClassroomFXML.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(StudentDashboardFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Scene JCScene = new Scene(JCParent);
            Stage JCWindow = new Stage();
            JCWindow.setScene(JCScene);
            JCWindow.setResizable(false);
            JCWindow.initModality(Modality.APPLICATION_MODAL);
            JCWindow.setTitle("Join Classroom");
            JCWindow.show();
        });
        
    }
    
    public static void getUserEmail(String email){
        StudentDashboardFXMLController.userEmail = email;
    }
    
    @FXML
    private void logoutButtonPushed(ActionEvent event) throws IOException {
        
        LogoutFXMLController.getMainWindow((Stage)((Node)event.getSource()).getScene().getWindow());
        
        Parent logoutParent = FXMLLoader.load(getClass().getResource("/login/LogoutFXML.fxml"));
        Scene logoutScene = new Scene(logoutParent);
        Stage logoutWindow = new Stage();
        logoutWindow.setScene(logoutScene);
        logoutWindow.setResizable(false);
        logoutWindow.initModality(Modality.APPLICATION_MODAL);
        logoutWindow.setTitle("Logout");
        logoutWindow.show();
    }
    
}
