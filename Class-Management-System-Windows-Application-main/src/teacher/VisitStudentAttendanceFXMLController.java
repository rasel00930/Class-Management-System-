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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


public class VisitStudentAttendanceFXMLController implements Initializable {

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
    @FXML
    private TextField marksField;
    @FXML
    private TextField totalMarksField;
    @FXML
    private JFXButton changeButton;
    @FXML
    private Label errorText;
    @FXML
    private Label changedText;
    
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
        marksField.setFocusTraversable(false);
        totalMarksField.setFocusTraversable(false);
        changeButton.setFocusTraversable(false);
        
        changedText.setVisible(false);
        errorText.setText("");
        
        try {
            Database db = new Database();
            attendanceListDB = db.getAttendance(studentEmail, classroomCode);
            attendanceMarks = db.getAttendanceMarks(studentEmail, classroomCode);
        } catch (SQLException ex) {
            Logger.getLogger(VisitStudentAttendanceFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        attendanceListDB.forEach(a -> {
            attendanceList.add(a);
        });
        
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        attendanceColumn.setCellValueFactory(new PropertyValueFactory<>("attendance"));
        
        attendanceTable.setItems(attendanceList);
        
        totalClassesField.setText(String.valueOf(attendanceMarks.getTotalClasses()));
        totalAttendanceField.setText(String.valueOf(attendanceMarks.getTotalAttendance()));
        marksField.setText(String.valueOf(attendanceMarks.getAttendanceMarks()));
        totalMarksField.setText(String.valueOf(attendanceMarks.getTotalAttendanceMarks()));
    }    
    
    public static void getEmailAndCode(String studentEmail, String classroomCode){
        VisitStudentAttendanceFXMLController.studentEmail = studentEmail;
        VisitStudentAttendanceFXMLController.classroomCode = classroomCode;
    }
    
    @FXML
    private void backButtonPushed(ActionEvent event) throws IOException {
        Parent studentsParent = FXMLLoader.load(getClass().getResource("StudentsFXML.fxml"));
        Scene studentsScene = new Scene(studentsParent);
        Stage mainWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
        mainWindow.setScene(studentsScene);
    }

    @FXML
    private void changeButtonPushed(ActionEvent event) throws SQLException {
        
        if(changeButton.getText().equals("Change")){
            changeButton.setText("Save");
            totalMarksField.setEditable(true);
            changedText.setVisible(false);
        }
        else{
            String totalMarksText = totalMarksField.getText();
        
            if(totalMarksText.equals("")){
                errorText.setText("Empty field");
                return;
            }

            double totalMarks; 
            try{
                totalMarks = Double.parseDouble(totalMarksText);
            }catch(NumberFormatException e){
                errorText.setText("Total marks must be number");
                return;
            }
            
            changeButton.setText("Change");
            totalMarksField.setEditable(false);
            changedText.setVisible(true);
            errorText.setText("");
            
            String totalClassesText = totalClassesField.getText();
            String totalAttendanceText = totalAttendanceField.getText();
            double totalClasses = Double.parseDouble(totalClassesText);
            double totalAttendance = Double.parseDouble(totalAttendanceText);
            
            double marks = Math.round((totalAttendance / totalClasses) * totalMarks);
            marksField.setText(String.valueOf(marks));
            
            Database db = new Database();
            db.changeAttendanceMarks(classroomCode, totalMarks);
        }
    }
    
}
