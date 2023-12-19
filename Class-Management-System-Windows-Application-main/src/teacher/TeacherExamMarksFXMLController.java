package teacher;

import database.*;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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


public class TeacherExamMarksFXMLController implements Initializable {

    @FXML
    private JFXButton backButton;
    @FXML
    private TableView<ExamMarksSubmit> marksTable;
    @FXML
    private TableColumn<ExamMarksSubmit, String> idColumn;
    @FXML
    private TableColumn<ExamMarksSubmit, String> nameColumn;
    @FXML
    private TableColumn<ExamMarksSubmit, Double> marksColumn;
    @FXML
    private TableColumn<ExamMarksSubmit, Double> convertedMarksColumn;
    @FXML
    private TextField examNameField;
    @FXML
    private TextField marksField;
    @FXML
    private TextField totalMarksField;
    @FXML
    private TextField convertedTotalMarksField;
    @FXML
    private JFXButton submitButton;
    @FXML
    private JFXButton doneButton;
    @FXML
    private Label submitedText;
    @FXML
    private Label errorText;
    
    static String classroomCode;
    ObservableList<ExamMarksSubmit> examMarksSubmitList = FXCollections.observableArrayList();
    String examName = "";
    double totalMarks, convertedTotalMarks;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        backButton.setFocusTraversable(false);
        submitButton.setFocusTraversable(false);
        doneButton.setFocusTraversable(false);
        examNameField.setFocusTraversable(false);
        marksField.setFocusTraversable(false);
        totalMarksField.setFocusTraversable(false);
        convertedTotalMarksField.setFocusTraversable(false);
        
        examNameField.setText("");
        marksField.setText("");
        totalMarksField.setText("");
        convertedTotalMarksField.setText("");
        
        submitedText.setVisible(false);
        errorText.setText("");
        
        try {
            Database db = new Database();
            examMarksSubmitList = db.getAllStudentsExamMarksSubmitOfaClassroom(classroomCode);
        } catch (SQLException ex) {
            Logger.getLogger(TeacherExamMarksFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        idColumn.setCellValueFactory(new PropertyValueFactory<>("studentID"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        marksColumn.setCellValueFactory(new PropertyValueFactory<>("marks"));
        convertedMarksColumn.setCellValueFactory(new PropertyValueFactory<>("convertedMarks"));
        
        idColumn.setSortType(TableColumn.SortType.ASCENDING);
        
        marksTable.setItems(examMarksSubmitList);
        marksTable.getSortOrder().add(idColumn);
    }    
    
    public static void getClassroomCode(String classroomCode){
        TeacherExamMarksFXMLController.classroomCode = classroomCode;
    }
    
    @FXML
    private void backButtonPushed(ActionEvent event) throws IOException {
        Parent classroomsParent = FXMLLoader.load(getClass().getResource("/teacher/TeacherClassroomsFXML.fxml"));
        Scene classroomsScene = new Scene(classroomsParent);
        Stage mainWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
        mainWindow.setScene(classroomsScene);
    }

    @FXML
    private void submitButtonPushed(ActionEvent event){
        if(examName.equals("")){
            return;
        }
        String examName1 = examNameField.getText();
        if(!examName1.equals(examName)){
            examName = examName1;
        }
        try{
            Database db = new Database();
            db.setAllStudentsExamMarksOfaClassroom(classroomCode, examMarksSubmitList, examName, totalMarks, convertedTotalMarks);
            submitedText.setVisible(true);
        }catch(SQLException e){
            errorText.setText(examName + " already exists!");
        }
    }

    @FXML
    private void doneButtonPushed(ActionEvent event) {
        String examName1 = examNameField.getText();
        String totalMarksText = totalMarksField.getText();
        String convertedTotalMarksText = convertedTotalMarksField.getText();
        String marksText = marksField.getText();
        
        if(examName1.equals("") || totalMarksText.equals("") || convertedTotalMarksText.equals("") || marksText.equals("")){
            errorText.setText("Empty field");
            return;
        }
        
        double marks, totalMarks1, convertedTotalMarks1;
        try{
            totalMarks1 = Double.parseDouble(totalMarksText);
            convertedTotalMarks1 = Double.parseDouble(convertedTotalMarksText);
            marks = Double.parseDouble(marksText);
        }catch(NumberFormatException e){
            errorText.setText("Marks must be numbers");
            return;
        }
        
        ExamMarksSubmit ems = marksTable.getSelectionModel().getSelectedItem();
        if(ems == null){
            errorText.setText("No student selected");
            return;
        }
        
        double convertedMarks = (marks / totalMarks1) * convertedTotalMarks1;
        ExamMarksSubmit emsNew = new ExamMarksSubmit(ems.getEmail(), examName1, marks, totalMarks1, convertedMarks, convertedTotalMarks1, ems.getName(), ems.getStudentID());
        
        examMarksSubmitList.remove(ems);
        examMarksSubmitList.add(emsNew);
        marksTable.getSortOrder().add(idColumn);
        
        examName = examName1;
        totalMarks = totalMarks1;
        convertedTotalMarks = convertedTotalMarks1;
        
        errorText.setText("");
        marksField.setText("");
    }
    
}
