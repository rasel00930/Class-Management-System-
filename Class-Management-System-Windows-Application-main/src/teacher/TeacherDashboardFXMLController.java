package teacher;

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


public class TeacherDashboardFXMLController implements Initializable {
    
    @FXML
    private AnchorPane titlePane;
    @FXML
    private AnchorPane profilePane;
    @FXML
    private AnchorPane classroomsPane;
    @FXML
    private AnchorPane createClassroomPane;
    @FXML
    private JFXButton logoutButton;
    
    static String userEmail;
    
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        logoutButton.setFocusTraversable(false);
        
        profilePane.setOnMouseClicked(e->{
            
            TeacherProfileFXMLController.getUserEmail(userEmail);
           
            Parent profileParent = null;
            try {
                profileParent = FXMLLoader.load(getClass().getResource("TeacherProfileFXML.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(TeacherDashboardFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Scene profileScene = new Scene(profileParent);
            Stage mainWindow = (Stage) profilePane.getScene().getWindow();
            mainWindow.setScene(profileScene);
        });
        
        classroomsPane.setOnMouseClicked(e->{
            
            TeacherClassroomsFXMLController.getUserEmail(userEmail);
            TeacherClassroomsFXMLController.getClassroomIndex(-1);
           
            Parent classroomsParent = null;
            try {
                classroomsParent = FXMLLoader.load(getClass().getResource("TeacherClassroomsFXML.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(TeacherDashboardFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Scene classroomsScene = new Scene(classroomsParent);
            Stage mainWindow = (Stage) classroomsPane.getScene().getWindow();
            mainWindow.setScene(classroomsScene);
        });
        
        createClassroomPane.setOnMouseClicked(e->{
            
            CreateClassroomFXMLController.getUserEmail(userEmail, (Stage) createClassroomPane.getScene().getWindow());
            
            Parent CCParent =null;
            try {
                CCParent = FXMLLoader.load(getClass().getResource("CreateClassroomFXML.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(TeacherDashboardFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Scene CCScene = new Scene(CCParent);
            Stage CCWindow = new Stage();
            CCWindow.setScene(CCScene);
            CCWindow.setResizable(false);
            CCWindow.initModality(Modality.APPLICATION_MODAL);
            CCWindow.setTitle("Create Classroom");
            CCWindow.show();
        });

    }
    
    public static void getUserEmail(String email){
        TeacherDashboardFXMLController.userEmail = email;
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
