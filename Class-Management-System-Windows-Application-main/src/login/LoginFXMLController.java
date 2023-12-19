package login;

import database.*;
import security.*;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import student.StudentDashboardFXMLController;
import teacher.TeacherDashboardFXMLController;


public class LoginFXMLController implements Initializable {

    @FXML
    private AnchorPane runningBar;
    @FXML
    private Label welcomeText;
    @FXML
    private Button slideSignInButton;
    @FXML
    private Button slideSignUpButton;
    @FXML
    private Label joinText;
    @FXML
    private TextField emailSignUpField;
    @FXML
    private TextField passSignUpField;
    @FXML
    private JFXButton signUpButton;
    @FXML
    private JFXButton signInButton;
    @FXML
    private TextField emailSignInField;
    @FXML
    private TextField passSignInField;
    @FXML
    private TextField confirmPassField;
    @FXML
    private Hyperlink forgotPassword;
    @FXML
    private AnchorPane rightPane;
    @FXML
    private Hyperlink aboutUs;
    @FXML
    private Label signUpText;
    @FXML
    private Label signInText;
    @FXML
    private Label passConditionText1;
    @FXML
    private Label passConditionText2;
    @FXML
    private Label passConditionText3;
    @FXML
    private RadioButton isTeacherRB;
    @FXML
    private RadioButton isStudentRB;
    @FXML
    private Label signInError;
    @FXML
    private AnchorPane mainPane;
    @FXML
    private ImageView appIcon;
    @FXML
    private ToggleGroup toggleTS;
    @FXML
    private Label emailErrorText;
    @FXML
    private Label accTypeText;
    
    ArrayList<Account> acList;
    
    boolean emailError;
    boolean lenthError;
    boolean charError;
    boolean matchError;
    
   
    @Override
    public void initialize(URL url, ResourceBundle rb){
        
        forgotPassword.setUnderline(false);
        aboutUs.setUnderline(false);
        emailSignUpField.setFocusTraversable(false);
        passSignUpField.setFocusTraversable(false);
        confirmPassField.setFocusTraversable(false);
        emailSignInField.setFocusTraversable(false);
        passSignInField.setFocusTraversable(false);
        signInButton.setFocusTraversable(false);
        signUpButton.setFocusTraversable(false);
        isTeacherRB.setFocusTraversable(false);
        isStudentRB.setFocusTraversable(false);
        
        
        signUpButton.setVisible(false);
        emailSignUpField.setVisible(false);
        passSignUpField.setVisible(false);
        slideSignInButton.setVisible(false);
        welcomeText.setVisible(false);
        confirmPassField.setVisible(false);
        signUpText.setVisible(false);
        passConditionText1.setVisible(false);
        passConditionText2.setVisible(false);
        passConditionText3.setVisible(false);
        isTeacherRB.setVisible(false);
        isStudentRB.setVisible(false);
        accTypeText.setVisible(false);
        emailErrorText.setText("");
        
        signInButton.setVisible(true);
        emailSignInField.setVisible(true);
        passSignInField.setVisible(true);
        slideSignUpButton.setVisible(true);
        joinText.setVisible(true);
        signInText.setVisible(true);
        forgotPassword.setVisible(true);
        signInError.setText("");
        
        passConditionText1.setTextFill(Paint.valueOf("Red"));
        passConditionText2.setTextFill(Paint.valueOf("Red"));
        passConditionText3.setTextFill(Paint.valueOf("Red"));
        
        emailError = false;
        lenthError = true;
        charError = true;
        matchError = true;
        
        try {
            Database db = new Database();
            acList = db.getAllAccounts();
        } catch (SQLException ex) {
            Logger.getLogger(LoginFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @FXML
    private void slideSignUp(ActionEvent event)
    {
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.5));
        slide.setNode(runningBar);
        
        
        TranslateTransition slide1 = new TranslateTransition();
        slide1.setDuration(Duration.seconds(0.5));
        slide1.setNode(rightPane);
        
        slide.setToX(600);
        slide1.setToX(-360);
        
        slide.play();  
        slide1.play();
        
        signUpButton.setVisible(true);
        emailSignUpField.setVisible(true);
        passSignUpField.setVisible(true);
        slideSignInButton.setVisible(true);
        welcomeText.setVisible(true);
        confirmPassField.setVisible(true);
        signUpText.setVisible(true);
        passConditionText1.setVisible(true);
        passConditionText2.setVisible(true);
        passConditionText3.setVisible(true);
        isTeacherRB.setVisible(true);
        isStudentRB.setVisible(true);
        
        signInButton.setVisible(false);
        emailSignInField.setVisible(false);
        passSignInField.setVisible(false);
        slideSignUpButton.setVisible(false);
        joinText.setVisible(false);
        signInText.setVisible(false);
        forgotPassword.setVisible(false);
        signInError.setText("");
        emailSignInField.setText("");
        passSignInField.setText("");
        
        passConditionText1.setTextFill(Paint.valueOf("Red"));
        passConditionText2.setTextFill(Paint.valueOf("Red"));
        passConditionText3.setTextFill(Paint.valueOf("Red"));
        
        emailError = false;
        lenthError = true;
        charError = true;
        matchError = true;
        
    }
    
    @FXML
    private void slideSignIn(ActionEvent event)
    {
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.5));
        slide.setNode(runningBar);
        
        
        TranslateTransition slide1 = new TranslateTransition();
        slide1.setDuration(Duration.seconds(0.5));
        slide1.setNode(rightPane);
        
        slide.setToX(0);
        slide1.setToX(0);
        
        
        slide.play();  
        slide1.play();
        
        signUpButton.setVisible(false);
        emailSignUpField.setVisible(false);
        passSignUpField.setVisible(false);
        slideSignInButton.setVisible(false);
        welcomeText.setVisible(false);
        confirmPassField.setVisible(false);
        signUpText.setVisible(false);
        passConditionText1.setVisible(false);
        passConditionText2.setVisible(false);
        passConditionText3.setVisible(false);
        isTeacherRB.setVisible(false);
        isStudentRB.setVisible(false);
        accTypeText.setVisible(false);
        emailErrorText.setText("");
        emailSignUpField.setText("");
        passSignUpField.setText("");
        confirmPassField.setText("");
        
        signInButton.setVisible(true);
        emailSignInField.setVisible(true);
        passSignInField.setVisible(true);
        slideSignUpButton.setVisible(true);
        joinText.setVisible(true);
        signInText.setVisible(true);
        forgotPassword.setVisible(true);
        
    }
    
    @FXML
    private void forgotPasswordClicked(ActionEvent event) throws IOException
    {
        PassRecoveryFXMLController.getAcList(acList, (Stage)((Node)event.getSource()).getScene().getWindow());
        
        Parent passRecoveryParent = FXMLLoader.load(getClass().getResource("PassRecoveryFXML.fxml"));
        Scene passRecoveryScene = new Scene(passRecoveryParent);
        Stage passRecoveryWindow = new Stage();
        passRecoveryWindow.setScene(passRecoveryScene);
        passRecoveryWindow.setResizable(false);
        passRecoveryWindow.initModality(Modality.APPLICATION_MODAL);
        passRecoveryWindow.setTitle("Recover Your Password");
        passRecoveryWindow.show();  
    }
    
    @FXML
    private void signUpButtonPushed(ActionEvent event) throws IOException, InterruptedException
    {
        String email = emailSignUpField.getText();
        String password = passSignUpField.getText();
        String accountType;
        
        if(emailError || lenthError || charError || matchError){
            return;
        }
        if(email.equals("")){
            emailErrorText.setText("Email field empty!");
            return;
        }
        if(!isTeacherRB.isSelected() && !isStudentRB.isSelected()){
            accTypeText.setVisible(true);
            return;
        }
        
        int code = RandomCode.getVerificationCode();
        String verificationCode = String.valueOf(code);
        String recipientEmail = email;
        String emailSubject = "Verification Code";
        String emailMessage = "<h1>" + verificationCode + "</h1>";

        new Thread(new MailingThread(recipientEmail, emailSubject, emailMessage)).start();
        
        if(isTeacherRB.isSelected()){
            accountType = "teacher";
        }
        else{
            accountType = "student";
        }
        
        Account ac = new Account(email, password, accountType);
        
        VerificationFXMLController.getVerificationCode(verificationCode, (Stage)((Node)event.getSource()).getScene().getWindow());
        VerificationFXMLController.getAccountInfo(ac);
        
        Parent verificationParent = FXMLLoader.load(getClass().getResource("VerificationFXML.fxml"));
        Scene verificationScene = new Scene(verificationParent);
        Stage verificationWindow = new Stage();
        verificationWindow.setScene(verificationScene);
        verificationWindow.setResizable(false);
        verificationWindow.initModality(Modality.APPLICATION_MODAL);
        verificationWindow.setTitle("Verify Your Email");
        verificationWindow.show();
    }

    @FXML
    private void signInButtonPushed(ActionEvent event) throws IOException {
        String email = emailSignInField.getText();
        String password = passSignInField.getText();
        
        if(email.equals("")){
            signInError.setText("Email field empty!");
            return;
        }
        if(password.equals("")){
            signInError.setText("Password field empty!");
            return;
        }
        
        for(Account ac:acList){
            if(ac.getEmail().equals(email) && HashCode.checkPassword(password, ac.getPassword())){
                if(ac.getAccountType().equals("teacher")){
                    
                    TeacherDashboardFXMLController.getUserEmail(ac.getEmail());
                    
                    Parent dashboardParent = FXMLLoader.load(getClass().getResource("/teacher/TeacherDashboardFXML.fxml"));
                    Scene teacherDashboardScene = new Scene(dashboardParent);
                    Stage mainWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
                    mainWindow.setScene(teacherDashboardScene);
                    
                }
                else if(ac.getAccountType().equals("student")){
                    
                    StudentDashboardFXMLController.getUserEmail(ac.getEmail());
                    
                    Parent dashboardParent = FXMLLoader.load(getClass().getResource("/student/StudentDashboardFXML.fxml"));
                    Scene studentDashboardScene = new Scene(dashboardParent);
                    Stage mainWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
                    mainWindow.setScene(studentDashboardScene);
                }
                return;
            }
        }
        signInError.setText("Email or Password didn't match!");
    }

    @FXML
    private void emailSignUpFieldAction(KeyEvent event) {
        String email = emailSignUpField.getText();
        
        for(Account ac:acList){
            if(ac.getEmail().equals(email)){
                emailErrorText.setText("Account already exists!");
                emailError = true;
                return;
            }
        }
        emailErrorText.setText("");
        emailError = false;
        
    }

    @FXML
    private void passSignUpFieldAction(KeyEvent event) {
        String password = passSignUpField.getText();
        String confirmPass = confirmPassField.getText();
        
        if(password.length() >= 6){
            passConditionText1.setTextFill(Paint.valueOf("Green"));
            lenthError = false;
        }
        else{
            passConditionText1.setTextFill(Paint.valueOf("Red"));
            lenthError = true;
        }
        if(password.toLowerCase().matches("(.*[a-z].*)") && password.matches("(.*[0-9].*)")){
            passConditionText2.setTextFill(Paint.valueOf("Green"));
            charError = false;
        }
        else{
            passConditionText2.setTextFill(Paint.valueOf("Red"));
            charError = true;
        }
        if(confirmPass.equals(password) && !password.equals("")){
            passConditionText3.setTextFill(Paint.valueOf("Green"));
            matchError = false;
        }
        else{
            passConditionText3.setTextFill(Paint.valueOf("Red"));
            matchError = true;
        }
    }

    @FXML
    private void confirmPassFieldAction(KeyEvent event) {
        String password = passSignUpField.getText();
        String confirmPass = confirmPassField.getText();
        
        if(confirmPass.equals(password) && !password.equals("")){
            passConditionText3.setTextFill(Paint.valueOf("Green"));
            matchError = false;
        }
        else{
            passConditionText3.setTextFill(Paint.valueOf("Red"));
            matchError = true;
        }
    }

    @FXML
    private void isTeacherRBSelected(ActionEvent event) {
        accTypeText.setVisible(false);
    }

    @FXML
    private void isStudentRBSelected(ActionEvent event) {
        accTypeText.setVisible(false);
    }

    @FXML
    private void aboutUsClicked(ActionEvent event) throws IOException {
        Parent AboutUsParent = FXMLLoader.load(getClass().getResource("/login/AboutUsFXML.fxml"));
        Scene AboutUsScene = new Scene(AboutUsParent);
        Stage mainWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
        mainWindow.setScene(AboutUsScene);
    }
    
}
