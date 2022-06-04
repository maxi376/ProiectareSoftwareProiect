package utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Angajat;

import java.io.File;
import java.net.URL;

public class Utils {
    private final static String route = "src/main/java/view/"    ;




    /*public static void buildStage2(String name, Angajat angajat) throws Exception{
        Stage primaryStage =new Stage();

        URL url = new File(route+name+".fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        primaryStage.setTitle(name);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        //stageToCLose.close();
    }*/
    public static void buildStage(String name) throws Exception{
        Stage primaryStage =new Stage();

        URL url = new File(route+name+".fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        primaryStage.setTitle(name);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        //stageToCLose.close();
    }





}
