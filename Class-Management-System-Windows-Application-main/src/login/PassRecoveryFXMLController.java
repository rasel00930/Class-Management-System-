package login;

import database.*;
import security.*;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;


public class PassRecoveryFXMLController implements Initializable {

    @FXML
    private TextField recoveryEmailField;
    @FXML
    private TextField OTPField;
    @FXML
    private JFXButton sendOTPButton;
    @FXML
    private JFXButton verifyButton;
    @FXML
    private Label emailError;
    @FXML
    private Label codeError;
   
    static Stage mainWindow;
    static ArrayList<Account> acList;
    Account userAC;
    String verificationCode;
    String recipientEmail;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        recoveryEmailField.setFocusTraversable(false);
        OTPField.setFocusTraversable(false);
        sendOTPButton.setFocusTraversable(false);
        verifyButton.setFocusTraversable(false);
        
        OTPField.setVisible(false);
        verifyButton.setVisible(false);
        emailError.setText("");
        codeError.setText("");
    }
    
    public static void getAcList(ArrayList<Account> acList, Stage mainWindow){
        PassRecoveryFXMLController.acList = acList;
        PassRecoveryFXMLController.mainWindow = mainWindow;
    }
    
    @FXML
    private void sendOTPButtonPushed(ActionEvent event) {
        String email = recoveryEmailField.getText();
        
        if(email.equals("")){
            emailError.setText("Email field empty!");
            emailError.setTextFill(Paint.valueOf("Red"));
            return;
        }
        
        for(Account ac:acList){
            if(ac.getEmail().equals(email)){
                
                userAC = ac;
                int code = RandomCode.getVerificationCode();
                verificationCode = String.valueOf(code);
                recipientEmail = email;
                String emailSubject = "Verification Code";
                String emailMessage = "<h1>" + verificationCode + "</h1>";
                
                new Thread(new MailingThread(recipientEmail, emailSubject, emailMessage)).start();
                
                emailError.setText("Verification code sent!");
                emailError.setTextFill(Paint.valueOf("Green"));
                OTPField.setVisible(true);
                verifyButton.setVisible(true);
                return;
            }
        }
        emailError.setText("Account doesn't exist!");
        emailError.setTextFill(Paint.valueOf("Red"));
        
    }
    
    @FXML
    private void verifyButtonPushed(ActionEvent event) throws IOException {
        String userInput = OTPField.getText();
        
        if(userInput.equals("")){
            codeError.setText("Code field empty!");
            return;
        }
        
        if(!userInput.equals(verificationCode)){
            codeError.setText("Invalid Code!");
            return;
        }
        
        PassRecoveryFXML2Controller.getAccount(userAC, mainWindow);
        
        Parent passRecoveryParent2 = FXMLLoader.load(getClass().getResource("PassRecoveryFXML2.fxml"));
        Scene passRecoveryScene2 = new Scene(passRecoveryParent2);
        Stage passRecoveryWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
        passRecoveryWindow.setScene(passRecoveryScene2);
        passRecoveryWindow.show();
    }
    
}
