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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


public class StudentAttendanceFXMLController implements Initializable {

    @FXML
    private JFXButton backButton;
    @FXML
    private TableView<Attendance> attendanceTable;
    @FXML
    private TableColumn<Attendance, String> dateColumn;
    @FXML
    private TableColumn<Attendance, String> attendanceColumn;
    @FXML
    private TextField totalClassesField;
    @FXML
    private TextField totalAttendanceField;
    
    static String studentEmail;
    static String classroomCode;
    ObservableList<Attendance> attendanceList = FXCollections.observableArrayList();
    ArrayList<Attendance> attendanceListDB;
    AttendanceMarks attendanceMarks;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        backButton.setFocusTraversable(false);
        totalClassesField.setFocusTraversable(false);
        totalAttendanceField.setFocusTraversable(false);
        
        try {
            Database db = new Database();
            attendanceListDB = db.getAttendance(studentEmail, classroomCode);
            attendanceMarks = db.getAttendanceMarks(studentEmail, classroomCode);
        } catch (SQLException ex) {
            Logger.getLogger(StudentAttendanceFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        attendanceListDB.forEach(a -> {
            attendanceList.add(a);
        });
        
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        attendanceColumn.setCellValueFactory(new PropertyValueFactory<>("attendance"));
        
        attendanceTable.setItems(attendanceList);
        
        totalClassesField.setText(String.valueOf(attendanceMarks.getTotalClasses()));
        totalAttendanceField.setText(String.valueOf(attendanceMarks.getTotalAttendance()));
    }    
    
    public static void getEmailAndCode(String studentEmail, String classroomCode){
        StudentAttendanceFXMLController.studentEmail = studentEmail;
        StudentAttendanceFXMLController.classroomCode = classroomCode;
    }
    
    @FXML
    private void backButtonPushed(ActionEvent event) throws IOException {
        Parent classroomsParent = FXMLLoader.load(getClass().getResource("/student/StudentClassroomsFXML.fxml"));
        Scene classroomsScene = new Scene(classroomsParent);
        Stage mainWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
        mainWindow.setScene(classroomsScene);
    }
    
}
