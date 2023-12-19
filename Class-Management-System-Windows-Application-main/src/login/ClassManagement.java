package login;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;


public class ClassManagement extends Application {
    
    @Override
    public void start(Stage primarystage) throws Exception {
        
        Parent loginParent = FXMLLoader.load(getClass().getResource("LoginFXML.fxml"));
        Scene loginScene = new Scene(loginParent);
        Stage mainWindow = primarystage;
        
        Image icon = new Image("Images/appicon.png");
        mainWindow.getIcons().add(icon);
        mainWindow.setScene(loginScene); 
        mainWindow.setResizable(false);
        mainWindow.setTitle("My Classroom");
        mainWindow.show();
        
    }
        
    public static void main(String[] args) {
        launch(args);
    }
    
}

