package com.example.a7;

import com.example.a7.ProgramsList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader listLoader = new FXMLLoader();
        listLoader.setLocation(getClass().getResource("programsList.fxml"));
        Parent root = listLoader.load();
        ProgramsList programsList = listLoader.getController();
        stage.setTitle("Select");
        stage.setScene(new Scene(root, 500, 550));
        stage.show();

        FXMLLoader programLoader = new FXMLLoader();
        programLoader.setLocation(getClass().getResource("program.fxml"));
        Parent programRoot = programLoader.load();
        Program program = programLoader.getController();
        programsList.setProgramsList(program);
        Stage programStage = new Stage();
        programStage.setTitle("Interpreter");
        programStage.setScene(new Scene(programRoot, 1000, 700));
        programStage.show();


    }
}
