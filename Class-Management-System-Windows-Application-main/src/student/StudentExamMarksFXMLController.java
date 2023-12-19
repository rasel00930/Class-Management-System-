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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


public class StudentExamMarksFXMLController implements Initializable {

    @FXML
    private JFXButton backButton;
    @FXML
    private TableView<ExamMarks> marksTable;
    @FXML
    private TableColumn<ExamMarks, String> examNameColumn;
    @FXML
    private TableColumn<ExamMarks, Double> marksColumn;
    @FXML
    private TableColumn<ExamMarks, Double> totalMarksColumn;
    @FXML
    private TableColumn<ExamMarks, Double> convertedMarksColumn;
    @FXML
    private TableColumn<ExamMarks, Double> convertedTotalMarksColumn;
    
    static String studentEmail;
    static String classroomCode;
    ObservableList<ExamMarks> examMarksList = FXCollections.observableArrayList();
    ArrayList<ExamMarks> examMarksListDB;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        backButton.setFocusTraversable(false);
        
        try {
            Database db = new Database();
            examMarksListDB = db.getExamMarks(studentEmail, classroomCode);
        } catch (SQLException ex) {
            Logger.getLogger(StudentExamMarksFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        examMarksListDB.forEach(em -> {
            examMarksList.add(em);
        });
        
        examNameColumn.setCellValueFactory(new PropertyValueFactory<>("examName"));
        marksColumn.setCellValueFactory(new PropertyValueFactory<>("marks"));
        totalMarksColumn.setCellValueFactory(new PropertyValueFactory<>("totalMarks"));
        convertedMarksColumn.setCellValueFactory(new PropertyValueFactory<>("convertedMarks"));
        convertedTotalMarksColumn.setCellValueFactory(new PropertyValueFactory<>("convertedTotalMarks"));
        
        marksTable.setItems(examMarksList);
    }
    
    public static void getEmailAndCode(String studentEmail, String classroomCode){
        StudentExamMarksFXMLController.studentEmail = studentEmail;
        StudentExamMarksFXMLController.classroomCode = classroomCode;
    }

    @FXML
    private void backButtonPushed(ActionEvent event) throws IOException {
        Parent classroomsParent = FXMLLoader.load(getClass().getResource("/student/StudentClassroomsFXML.fxml"));
        Scene classroomsScene = new Scene(classroomsParent);
        Stage mainWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
        mainWindow.setScene(classroomsScene);
    }
    
}
