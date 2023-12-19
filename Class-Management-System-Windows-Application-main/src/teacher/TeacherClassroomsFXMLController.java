package teacher;

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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class TeacherClassroomsFXMLController implements Initializable {

    @FXML
    private ChoiceBox<String> classroomsDropdown;
    @FXML
    private AnchorPane attendanceIcon;
    @FXML
    private AnchorPane examMarksIcon;
    @FXML
    private AnchorPane announcementIcon;
    @FXML
    private AnchorPane studentsIcon;
    @FXML
    private TextField classroomCodeField;
    @FXML
    private TextField courseNameField;
    @FXML
    private JFXButton backButton;
    
    static String userEmail;
    static int classroomIndex;
    ArrayList<Classroom> classroomList;
    String classroomCode = "";
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        backButton.setFocusTraversable(false);
        classroomsDropdown.setFocusTraversable(false);
        classroomCodeField.setFocusTraversable(false);
        courseNameField.setFocusTraversable(false);
        
        announcementIcon.setOnMouseClicked(e->{
            
            if(!classroomCode.equals("")){
                TeacherAnnouncementFXMLController.getClassroomCode(classroomCode);
                
                Parent announcementParent =null;
                try {
                    announcementParent = FXMLLoader.load(getClass().getResource("TeacherAnnouncementFXML.fxml"));
                } catch (IOException ex) {
                    Logger.getLogger(TeacherClassroomsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
                Scene announcementScene = new Scene(announcementParent);
                Stage mainWindow = (Stage) announcementIcon.getScene().getWindow();
                mainWindow.setScene(announcementScene);
            }
        });
        
        attendanceIcon.setOnMouseClicked(e->{
            
            if(!classroomCode.equals("")){
                TeacherAttendanceFXMLController.getClassroomCode(classroomCode);
                
                Parent attendanceParent =null;
                try {
                    attendanceParent = FXMLLoader.load(getClass().getResource("TeacherAttendanceFXML.fxml"));
                } catch (IOException ex) {
                    Logger.getLogger(TeacherClassroomsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
                Scene attendanceScene = new Scene(attendanceParent);
                Stage mainWindow = (Stage) attendanceIcon.getScene().getWindow();
                mainWindow.setScene(attendanceScene);
            }
        });
        
        examMarksIcon.setOnMouseClicked(e->{
            
            if(!classroomCode.equals("")){
                TeacherExamMarksFXMLController.getClassroomCode(classroomCode);
                
                Parent examMarksParent =null;
                try {
                    examMarksParent = FXMLLoader.load(getClass().getResource("TeacherExamMarksFXML.fxml"));
                } catch (IOException ex) {
                    Logger.getLogger(TeacherClassroomsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
                Scene examMarksScene = new Scene(examMarksParent);
                Stage mainWindow = (Stage) examMarksIcon.getScene().getWindow();
                mainWindow.setScene(examMarksScene);
            }
        });
        
        studentsIcon.setOnMouseClicked(e->{
            
            if(!classroomCode.equals("")){
                StudentsFXMLController.getClassroomCode(classroomCode);
        
                Parent studentsParent =null;
                try {
                    studentsParent = FXMLLoader.load(getClass().getResource("StudentsFXML.fxml"));
                } catch (IOException ex) {
                    Logger.getLogger(TeacherClassroomsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
                Scene studentsScene = new Scene(studentsParent);
                Stage mainWindow = (Stage) studentsIcon.getScene().getWindow();
                mainWindow.setScene(studentsScene);
            }
        });
        
        try {
            Database db = new Database();
            classroomList = db.getAllClassroomsOfaTeacher(userEmail);
            
            if(classroomList.isEmpty()){
                return;
            }
            
            int i = 1;
            for(Classroom c:classroomList){
                classroomsDropdown.getItems().add(i + ". " + c.getClassroomName());
                i++;
            }
            
            if(classroomIndex == -1){
                classroomsDropdown.getSelectionModel().selectFirst();
            }
            else{
                classroomsDropdown.getSelectionModel().select(classroomIndex);
            }
            
            classroomIndex = classroomsDropdown.getSelectionModel().getSelectedIndex();
            classroomCode = classroomList.get(classroomIndex).getClassroomCode();
            String courseName = classroomList.get(classroomIndex).getCourseName();
            classroomCodeField.setText(classroomCode);
            courseNameField.setText(courseName);
            
        } catch (SQLException ex) {
            Logger.getLogger(TeacherClassroomsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        classroomsDropdown.setOnAction(e->{
            classroomIndex = classroomsDropdown.getSelectionModel().getSelectedIndex();
            classroomCode = classroomList.get(classroomIndex).getClassroomCode();
            String courseName = classroomList.get(classroomIndex).getCourseName();
            classroomCodeField.setText(classroomCode);
            courseNameField.setText(courseName);
        });
    
    }
    
    public static void getUserEmail(String email){
        TeacherClassroomsFXMLController.userEmail = email;
    }
    
    public static void getClassroomIndex(int index){
        TeacherClassroomsFXMLController.classroomIndex = index;
    }
    
    @FXML
    private void backButtonPushed(ActionEvent event) throws IOException {
        Parent teacherDashboard = FXMLLoader.load(getClass().getResource("/teacher/TeacherDashboardFXML.fxml"));
        Scene dashboardScene = new Scene(teacherDashboard);
        Stage mainWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
        mainWindow.setScene(dashboardScene);
    }
    
}
