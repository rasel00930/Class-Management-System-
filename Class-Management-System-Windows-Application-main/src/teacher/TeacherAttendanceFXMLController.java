package teacher;

import database.*;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.StringConverter;


public class TeacherAttendanceFXMLController implements Initializable {

    @FXML
    private JFXButton backButton;
    @FXML
    private JFXButton submitButton;
    @FXML
    private Label submitedText;
    @FXML
    private TableView<AttendanceSubmit> attendanceTable;
    @FXML
    private TableColumn<AttendanceSubmit, String> idColumn;
    @FXML
    private TableColumn<AttendanceSubmit, String> nameColumn;
    @FXML
    private TableColumn<AttendanceSubmit, String> attendanceColumn;
    @FXML
    private JFXButton presentButton;
    @FXML
    private JFXDatePicker datePicker;
    @FXML
    private Label errorText;

    static String classroomCode;
    ObservableList<AttendanceSubmit> attendanceSubmitList = FXCollections.observableArrayList();
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        backButton.setFocusTraversable(false);
        submitButton.setFocusTraversable(false);
        presentButton.setFocusTraversable(false);
        datePicker.setFocusTraversable(false);
        
        submitedText.setVisible(false);
        errorText.setText("");
        
        datePicker.setConverter(new StringConverter<LocalDate>(){
            private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            @Override
            public String toString(LocalDate localDate)
            {
                if(localDate == null)
                    return "";
                return dateTimeFormatter.format(localDate);
            }

            @Override
            public LocalDate fromString(String dateString)
            {
                if(dateString == null || dateString.trim().isEmpty())
                {
                    return null;
                }
                return LocalDate.parse(dateString, dateTimeFormatter);
            }
        });
        
        try {
            Database db = new Database();
            attendanceSubmitList = db.getAllStudentsAttendanceSubmitOfaClassroom(classroomCode);
        } catch (SQLException ex) {
            Logger.getLogger(TeacherAttendanceFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        idColumn.setCellValueFactory(new PropertyValueFactory<>("studentID"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        attendanceColumn.setCellValueFactory(new PropertyValueFactory<>("attendance"));
        
        idColumn.setSortType(TableColumn.SortType.ASCENDING);
        
        attendanceTable.setItems(attendanceSubmitList);
        attendanceTable.getSortOrder().add(idColumn);
    }    
    
    public static void getClassroomCode(String classroomCode){
        TeacherAttendanceFXMLController.classroomCode = classroomCode;
    }
    
    @FXML
    private void backButtonPushed(ActionEvent event) throws IOException {
        Parent classroomsParent = FXMLLoader.load(getClass().getResource("/teacher/TeacherClassroomsFXML.fxml"));
        Scene classroomsScene = new Scene(classroomsParent);
        Stage mainWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
        mainWindow.setScene(classroomsScene);
    }

    @FXML
    private void submitButtonPushed(ActionEvent event) {
        if(submitedText.isVisible()){
            return;
        }
        if(datePicker.getValue() == null){
            errorText.setText("Date not picked");
            return;
        }
        LocalDate ld = datePicker.getValue();
        String date = ld.toString();
        attendanceSubmitList.forEach(as -> {
            as.setDate(date);
        });
        try{
            Database db = new Database();
            db.setAllStudentsAttendanceOfaClassroom(attendanceSubmitList);
            submitedText.setVisible(true);
            errorText.setText("");
        }catch(SQLException e){
            errorText.setText("Date already exists!");
        }
    }

    @FXML
    private void presentButtonPushed(ActionEvent event) {
        if(datePicker.getValue() == null){
            errorText.setText("Date not picked");
            return;
        }
        AttendanceSubmit as = attendanceTable.getSelectionModel().getSelectedItem();
        if(as == null){
            errorText.setText("No student selected");
            return;
        }
        
        errorText.setText("");
        
        String attendance;
        
        if(as.getAttendance().equals("")){
            attendance = "P";
            AttendanceSubmit asNew = new AttendanceSubmit(as.getName(), as.getStudentID(), "", attendance, as.getEmail(), classroomCode, as.getTotalClasses(), as.getTotalAttendance(), as.getAttendanceMarks(), as.getTotalAttendanceMarks());
            
            attendanceSubmitList.remove(as);
            attendanceSubmitList.add(asNew);
            attendanceTable.getSortOrder().add(idColumn);
        }
        else{
            attendance = "";
            AttendanceSubmit asNew = new AttendanceSubmit(as.getName(), as.getStudentID(), "", attendance, as.getEmail(), classroomCode, as.getTotalClasses(), as.getTotalAttendance(), as.getAttendanceMarks(), as.getTotalAttendanceMarks());
            
            attendanceSubmitList.remove(as);
            attendanceSubmitList.add(asNew);
            attendanceTable.getSortOrder().add(idColumn);
        }
    }
    
}
