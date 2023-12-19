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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


public class StudentsFXMLController implements Initializable {

    @FXML
    private TableView<StudentDetails> studentTable;
    @FXML
    private TableColumn<StudentDetails, String> idColumn;
    @FXML
    private TableColumn<StudentDetails, String> nameColumn;
    @FXML
    private JFXButton backButton;
    @FXML
    private JFXButton profileButton;
    @FXML
    private JFXButton attendanceButton;
    @FXML
    private JFXButton examMarksButton;
    @FXML
    private JFXButton removeButton;
    
    ObservableList<StudentDetails> studentList = FXCollections.observableArrayList();
    ArrayList<StudentDetails> studentListDB;
    static String classroomCode;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        backButton.setFocusTraversable(false);
        profileButton.setFocusTraversable(false);
        attendanceButton.setFocusTraversable(false);
        examMarksButton.setFocusTraversable(false);
        removeButton.setFocusTraversable(false);
        
        try {
            Database db = new Database();
            studentListDB = db.getAllStudentDetailsOfaClassroom(classroomCode);
        } catch (SQLException ex) {
            Logger.getLogger(StudentsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        studentListDB.forEach(sd -> {
            studentList.add(sd);
        });
        
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        
        idColumn.setSortType(TableColumn.SortType.ASCENDING);
        
        studentTable.setItems(studentList);
        studentTable.getSortOrder().add(idColumn);
    }
    
    public static void getClassroomCode(String classroomCode){
        StudentsFXMLController.classroomCode = classroomCode;
    }

    @FXML
    private void backButtonPushed(ActionEvent event) throws IOException {
        Parent classroomsParent = FXMLLoader.load(getClass().getResource("/teacher/TeacherClassroomsFXML.fxml"));
        Scene classroomsScene = new Scene(classroomsParent);
        Stage mainWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
        mainWindow.setScene(classroomsScene);
    }

    @FXML
    private void profileButtonPushed(ActionEvent event) throws IOException {
        StudentDetails sDetails = studentTable.getSelectionModel().getSelectedItem();
        if(sDetails == null){
            return;
        }
        VisitStudentProfileFXMLController.getUserEmail(sDetails.getEmail());
        
        Parent profileParent = FXMLLoader.load(getClass().getResource("/teacher/VisitStudentProfileFXML.fxml"));
        Scene profileScene = new Scene(profileParent);
        Stage mainWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
        mainWindow.setScene(profileScene);
    }

    @FXML
    private void attendanceButtonPushed(ActionEvent event) throws IOException {
        StudentDetails sDetails = studentTable.getSelectionModel().getSelectedItem();
        if(sDetails == null){
            return;
        }
        VisitStudentAttendanceFXMLController.getEmailAndCode(sDetails.getEmail(), classroomCode);
        
        Parent attendanceParent = FXMLLoader.load(getClass().getResource("/teacher/VisitStudentAttendanceFXML.fxml"));
        Scene attendanceScene = new Scene(attendanceParent);
        Stage mainWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
        mainWindow.setScene(attendanceScene);
    }

    @FXML
    private void examMarksButtonPushed(ActionEvent event) throws IOException {
        StudentDetails sDetails = studentTable.getSelectionModel().getSelectedItem();
        if(sDetails == null){
            return;
        }
        VisitStudentExamMarksFXMLController.getEmailAndCode(sDetails.getEmail(), classroomCode);
        
        Parent examMarksParent = FXMLLoader.load(getClass().getResource("/teacher/VisitStudentExamMarksFXML.fxml"));
        Scene examMarksScene = new Scene(examMarksParent);
        Stage mainWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
        mainWindow.setScene(examMarksScene);
    }

    @FXML
    private void removeButtonPushed(ActionEvent event) throws SQLException {
        StudentDetails sDetails = studentTable.getSelectionModel().getSelectedItem();
        if(sDetails == null){
            return;
        }
        studentList.remove(sDetails);
        Database db = new Database();
        db.removeStudent(classroomCode, sDetails.getEmail());
    }
    
}
