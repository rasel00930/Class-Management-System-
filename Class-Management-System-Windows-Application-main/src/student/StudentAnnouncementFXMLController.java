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
import javafx.scene.control.TextArea;
import javafx.stage.Stage;


public class StudentAnnouncementFXMLController implements Initializable {

    @FXML
    private JFXButton backButton;
    @FXML
    private TextArea announcementArea;
    
    static String classroomCode;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        announcementArea.setFocusTraversable(false);
        backButton.setFocusTraversable(false);
        
        try {
            Database db = new Database();
            String announcement = db.getAnnouncement(classroomCode);
            announcementArea.setText(announcement);
        } catch (SQLException ex) {
            Logger.getLogger(StudentAnnouncementFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public static void getClassroomCode(String classroomCode){
        StudentAnnouncementFXMLController.classroomCode = classroomCode;
    }
    
    @FXML
    private void backButtonPushed(ActionEvent event) throws IOException {
        
        Parent classroomsParent = FXMLLoader.load(getClass().getResource("/student/StudentClassroomsFXML.fxml"));
        Scene classroomsScene = new Scene(classroomsParent);
        Stage mainWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
        mainWindow.setScene(classroomsScene);
    }

}
