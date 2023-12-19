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
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;


public class TeacherAnnouncementFXMLController implements Initializable {

    @FXML
    private JFXButton editButton;
    @FXML
    private Label savedAlert;
    @FXML
    private JFXButton backButton;
    @FXML
    private TextArea announcementArea;
    
    static String classroomCode;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        announcementArea.setFocusTraversable(false);
        backButton.setFocusTraversable(false);
        editButton.setFocusTraversable(false);
        
        editButton.setText("Edit");
        savedAlert.setVisible(false);
        
        try {
            Database db = new Database();
            String announcement = db.getAnnouncement(classroomCode);
            announcementArea.setText(announcement);
        } catch (SQLException ex) {
            Logger.getLogger(TeacherAnnouncementFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public static void getClassroomCode(String classroomCode){
        TeacherAnnouncementFXMLController.classroomCode = classroomCode;
    }
    
    @FXML
    private void backButtonPushed(ActionEvent event) throws IOException {
        
        Parent classroomsParent = FXMLLoader.load(getClass().getResource("/teacher/TeacherClassroomsFXML.fxml"));
        Scene classroomsScene = new Scene(classroomsParent);
        Stage mainWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
        mainWindow.setScene(classroomsScene);
    }

    @FXML
    private void editButtonPushed(ActionEvent event) throws SQLException {
        
        if(editButton.getText().equals("Edit")){
            
            editButton.setText("Save");
            savedAlert.setVisible(false);
            
            announcementArea.setEditable(true);
        }
        else{
            editButton.setText("Edit");
            savedAlert.setVisible(true);
            
            announcementArea.setEditable(false);
            
            String announcement = announcementArea.getText();
            Database db = new Database();
            db.editAnnouncement(classroomCode, announcement);
        }
    }
    
}
