package testInterface;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import test.testjpa.Service;

import java.sql.Date;
import java.time.Instant;


public class TestInterface extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        HBox root = new HBox();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();

        Service service = new Service();


        /*** FIELDS ***/


        TextField nameF = new TextField();
        Label nameL = new Label("Name");
        VBox nameBox = new VBox(nameL, nameF);
        root.getChildren().add(nameBox);

        TextField userIdF = new TextField();
        Label userIdL = new Label("User ID");
        VBox userIdBox = new VBox(userIdL, userIdF);
        root.getChildren().add(userIdBox);

        TextField proIdF = new TextField();
        Label proIdL = new Label("Pro ID");
        VBox proIdBox = new VBox(proIdL, proIdF);
        root.getChildren().add(proIdBox);

        TextField meetingIdF = new TextField();
        Label meetingIdL = new Label("Meeting ID");
        VBox meetingIdBox = new VBox(meetingIdL, meetingIdF);
        root.getChildren().add(meetingIdBox);

        /*** BUTTONS ***/

        createButton(root,"Add User",new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                service.userRegister(nameF.getText());
            }
        });

        createButton(root,"Add Pro",new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                service.proRegister(nameF.getText());
            }
        });

        createButton(root,"Create Slot",new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                service.addTimeSlot(Long.parseLong(proIdF.getText()), Date.from(Instant.now()), 15);
            }
        });

        createButton(root,"Remove Slot", new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                service.removeTimeSlot(Long.parseLong(meetingIdF.getText()));
            }

        });

        createButton(root,"Request meeting",new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                service.requestMeeting(Long.parseLong(userIdF.getText()), Long.parseLong(meetingIdF.getText()));
            }
        });

        createButton(root,"Cancel meeting",new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                service.cancelMeeting(Long.parseLong(meetingIdF.getText()));
            }
        });





    }

    public void createButton (HBox root,String name, EventHandler < ActionEvent > handler)
    {
        Button button = new Button(name);
        button.setOnAction(handler);
        root.getChildren().add(button);
    }

    public void refresh (Service service, VBox root){

    }
}

