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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class StudentClassroomsFXMLController implements Initializable {

    @FXML
    private ChoiceBox<String> classroomsDropdown;
    @FXML
    private AnchorPane attendanceIcon;
    @FXML
    private AnchorPane examMarksIcon;
    @FXML
    private AnchorPane announcementIcon;
    @FXML
    private TextField classroomCodeField;
    @FXML
    private TextField courseNameField;
    @FXML
    private Hyperlink teacherNameLink;
    @FXML
    private JFXButton backButton;
    
    static String userEmail;
    static int classroomIndex;
    ArrayList<Classroom> classroomList;
    String classroomCode = "";
    TeacherDetails tDetails;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        backButton.setFocusTraversable(false);
        classroomsDropdown.setFocusTraversable(false);
        classroomCodeField.setFocusTraversable(false);
        courseNameField.setFocusTraversable(false);
        teacherNameLink.setFocusTraversable(false);
        teacherNameLink.setUnderline(false);
        
        announcementIcon.setOnMouseClicked(e->{
            
            if(!classroomCode.equals("")){
                StudentAnnouncementFXMLController.getClassroomCode(classroomCode);
                
                Parent announcementParent =null;
                try {
                    announcementParent = FXMLLoader.load(getClass().getResource("StudentAnnouncementFXML.fxml"));
                } catch (IOException ex) {
                    Logger.getLogger(StudentClassroomsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
                Scene announcementScene = new Scene(announcementParent);
                Stage mainWindow = (Stage) announcementIcon.getScene().getWindow();
                mainWindow.setScene(announcementScene);
            }
        });
        
        examMarksIcon.setOnMouseClicked(e->{
            
            if(!classroomCode.equals("")){
                StudentExamMarksFXMLController.getEmailAndCode(userEmail, classroomCode);
                
                Parent examMarksParent =null;
                try {
                    examMarksParent = FXMLLoader.load(getClass().getResource("StudentExamMarksFXML.fxml"));
                } catch (IOException ex) {
                    Logger.getLogger(StudentClassroomsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
                Scene examMarksScene = new Scene(examMarksParent);
                Stage mainWindow = (Stage) examMarksIcon.getScene().getWindow();
                mainWindow.setScene(examMarksScene);
            }
        });
        
        attendanceIcon.setOnMouseClicked(e->{
            
            if(!classroomCode.equals("")){
                StudentAttendanceFXMLController.getEmailAndCode(userEmail, classroomCode);
                
                Parent attendanceParent =null;
                try {
                    attendanceParent = FXMLLoader.load(getClass().getResource("StudentAttendanceFXML.fxml"));
                } catch (IOException ex) {
                    Logger.getLogger(StudentClassroomsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
                Scene attendanceScene = new Scene(attendanceParent);
                Stage mainWindow = (Stage) attendanceIcon.getScene().getWindow();
                mainWindow.setScene(attendanceScene);
            }
        });
        
        try {
            Database db = new Database();
            classroomList = db.getAllClassroomsOfaStudent(userEmail);
            
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
            String teacherEmail = classroomList.get(classroomIndex).getTeacherEmail();
            
            tDetails = db.readTeacherDetails(teacherEmail);
            
            classroomCodeField.setText(classroomCode);
            courseNameField.setText(courseName);
            teacherNameLink.setText(tDetails.getName());
            
        } catch (SQLException ex) {
            Logger.getLogger(StudentClassroomsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        classroomsDropdown.setOnAction(e->{
            classroomIndex = classroomsDropdown.getSelectionModel().getSelectedIndex();
            classroomCode = classroomList.get(classroomIndex).getClassroomCode();
            String courseName = classroomList.get(classroomIndex).getCourseName();
            String teacherEmail = classroomList.get(classroomIndex).getTeacherEmail();
            
            try {
                Database db = new Database();
                tDetails = db.readTeacherDetails(teacherEmail);
                classroomCodeField.setText(classroomCode);
                courseNameField.setText(courseName);
                teacherNameLink.setText(tDetails.getName());
            } catch (SQLException ex) {
                Logger.getLogger(StudentClassroomsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        });
    
    }
    
    public static void getUserEmail(String email){
        StudentClassroomsFXMLController.userEmail = email;
    }
    
    public static void getClassroomIndex(int index){
        StudentClassroomsFXMLController.classroomIndex = index;
    }
    
    @FXML
    private void backButtonPushed(ActionEvent event) throws IOException {
        Parent studentDashboard = FXMLLoader.load(getClass().getResource("/student/StudentDashboardFXML.fxml"));
        Scene dashboardScene = new Scene(studentDashboard);
        Stage mainWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
        mainWindow.setScene(dashboardScene);
    }

    @FXML
    private void teacherNameClicked(ActionEvent event) throws IOException {
        if(tDetails == null){
            return;
        }
        VisitTeacherProfileFXMLController.getTeacherEmail(tDetails.getEmail());
        
        Parent ProfileParent = FXMLLoader.load(getClass().getResource("/student/VisitTeacherProfileFXML.fxml"));
        Scene ProfileScene = new Scene(ProfileParent);
        Stage mainWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
        mainWindow.setScene(ProfileScene);
    }
    
}
