import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.event.*;
import javafx.collections.FXCollections;

import java.util.Scanner;

public class DVDCollectionApp1  extends Application {
    private DVDCollection model;

    public DVDCollectionApp1() {
        model = DVDCollection.example1();
      
    }

    public void start(Stage primaryStage) {
        Pane  aPane = new Pane();

        // Create the view
        DVDCollectionAppView1  view = new DVDCollectionAppView1();
        aPane.getChildren().add(view);


        primaryStage.setTitle("My DVD Collection");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(aPane));
        primaryStage.show();
        view.update(model, 0);

        view.getButtonPane().getAddButton().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                Scanner s = new Scanner(System.in);
                handleAdd(s); // method created to reduce duplicates

                view.update(model, 0);


            }
        });

        view.getButtonPane().getDeleteButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                int selectedTitle = view.getTitleList().getSelectionModel().getSelectedIndex();
                String selectedItem = view.getTitleList().getSelectionModel().getSelectedItem();
                if(selectedTitle >= 0){
                    model.remove(selectedItem);
                }
                view.update(model, 0);
            }
        });

        view.getTitleList().setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                int selectedTitle = view.getTitleList().getSelectionModel().getSelectedIndex();
               // view.getTitleList().getSelectionModel().

                if(selectedTitle >= 0){
                    view.getYearList().getSelectionModel().select(selectedTitle);
                    view.getLengthList().getSelectionModel().select(selectedTitle);
                }
                view.update(model, selectedTitle);
            }
        });
        view.getYearList().setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                int selectedYear = view.getYearList().getSelectionModel().getSelectedIndex();
                // view.getTitleList().getSelectionModel().

                if(selectedYear >= 0){
                    view.getTitleList().getSelectionModel().select(selectedYear);
                    view.getLengthList().getSelectionModel().select(selectedYear);
                }
                view.update(model, selectedYear);
            }
        });
        view.getLengthList().setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                int selectedLength = view.getLengthList().getSelectionModel().getSelectedIndex();
                // view.getTitleList().getSelectionModel().

                if(selectedLength >= 0){
                    view.getYearList().getSelectionModel().select(selectedLength);
                    view.getTitleList().getSelectionModel().select(selectedLength);
                }
                view.update(model, selectedLength);
            }
        });


    }
    public void handleAdd(Scanner s){
        System.out.print("Please enter your DVD title: ");
        String name = s.nextLine();
        System.out.print("Please enter your DVD year: ");
        int year = s.nextInt();
        System.out.print("Please enter your DVD length: ");
        int length = s.nextInt();

        if(name.length() > 0 && String.valueOf(year).length() > 0 && String.valueOf(length).length() > 0){
            DVD temp = new DVD(name, year, length);
            model.add(temp);
        }


    }



    public static void main(String[] args) {
        launch(args);
    }
}