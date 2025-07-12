package com.example.docsign;

import javafx.application.Application;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import java.io.File;


public class DocSign extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("PDF Selector");
        System.out.println("App started");

        Button selectButton = new Button("Select PDF File");
        selectButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
            File selectedFile = fileChooser.showOpenDialog(primaryStage);

            if (selectedFile != null) {
                PdfViewer.showPdfContent(selectedFile); // This opens the new window
            }
        });

        VBox root = new VBox(10, selectButton);
        root.setPadding(new javafx.geometry.Insets(20));
        Scene scene = new Scene(root, 300, 100);
        primaryStage.setScene(scene);
        primaryStage.show();
        System.out.println("Stage shown");
    }

    public static void main(String[] args) {
        launch(args);
    }
}