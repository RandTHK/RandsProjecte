package com.example.docsign;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.LosslessFactory;
import org.apache.pdfbox.rendering.PDFRenderer;

import java.io.File;
import java.io.IOException;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;


public class PdfViewer {

    private static List<StackPane> pageStacks = new ArrayList<>();
    private static File currentPdfFile;

    public static void showPdfContent(File pdfFile) {
        Stage pdfStage = new Stage();
        pdfStage.setTitle("PDF Viewer with Signature");
        pdfStage.initModality(Modality.APPLICATION_MODAL);

        VBox imageContainer = new VBox(20);
        ScrollPane scrollPane = new ScrollPane(imageContainer);
        scrollPane.setFitToWidth(true);

        pageStacks.clear();
        currentPdfFile = pdfFile;

        // Store signature image and mode flag
        final Image[] signatureToAdd = new Image[1];  // holds currently chosen signature image
        final boolean[] isAddingSignature = {false};

        try (PDDocument document = PDDocument.load(pdfFile)) {
            PDFRenderer renderer = new PDFRenderer(document);

            for (int i = 0; i < document.getNumberOfPages(); i++) {
                BufferedImage bim = renderer.renderImageWithDPI(i, 150);
                Image fxImage = SwingFXUtils.toFXImage(bim, null);

                ImageView pdfPageView = new ImageView(fxImage);
                pdfPageView.setFitWidth(800);
                pdfPageView.setPreserveRatio(true);

                Pane overlay = new Pane();
                overlay.setPrefSize(800, fxImage.getHeight() * (800.0 / fxImage.getWidth()));

                // Add mouse click handler for placing signature only if isAddingSignature is true
                overlay.setOnMouseClicked(e -> {
                    if (isAddingSignature[0] && signatureToAdd[0] != null) {
                        ImageView sigView = new ImageView(signatureToAdd[0]);
                        sigView.setFitWidth(120);
                        sigView.setPreserveRatio(true);
                        sigView.setLayoutX(e.getX());
                        sigView.setLayoutY(e.getY());

                        overlay.getChildren().add(sigView);
                        addResizeHandles(sigView, overlay);
                        makeNodeDraggable(sigView);
                        // Reset after placing one image
                        isAddingSignature[0] = false;
                        signatureToAdd[0] = null;
                    }
                });

                StackPane pageStack = new StackPane(pdfPageView, overlay);
                imageContainer.getChildren().add(pageStack);
                pageStacks.add(pageStack);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Buttons
        Button addSignatureBtn = new Button("Add Signature");
        Button saveButton = new Button("Save Signed PDF");

        addSignatureBtn.setOnAction(event -> {
            FileChooser chooser = new FileChooser();
            chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG Signature", "*.png"));
            File signature = chooser.showOpenDialog(pdfStage);
            if (signature != null) {
                signatureToAdd[0] = new Image(signature.toURI().toString());
                isAddingSignature[0] = true;
            }
        });

        saveButton.setOnAction(event -> {
            if (currentPdfFile == null) {
                System.out.println("No PDF loaded!");
                return;
            }
            FileChooser fileChooser = new FileChooser();
            fileChooser.setInitialFileName("signed_output.pdf");
            File outputFile = fileChooser.showSaveDialog(pdfStage);
            if (outputFile != null) {
                try {
                    // currentPdfFile is the original PDF file
                    // outputFile is the destination to save the signed PDF
                    // pageStacks contain StackPanes with signature overlays
                    saveSignedPdf(currentPdfFile, outputFile, pageStacks);
                    System.out.println("Signed PDF saved to " + outputFile.getAbsolutePath());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        HBox buttonBox = new HBox(10, addSignatureBtn, saveButton);
        VBox root = new VBox(10, scrollPane, buttonBox);
        root.setPrefSize(850, 1000);

        Scene scene = new Scene(root);
        pdfStage.setScene(scene);
        pdfStage.show();
    }
    private static void addResizeHandles(ImageView imageView, Pane parent) {
        double handleRadius = 6;

        Circle topLeft = new Circle(handleRadius, Color.RED);
        Circle topRight = new Circle(handleRadius, Color.RED);
        Circle bottomLeft = new Circle(handleRadius, Color.RED);
        Circle bottomRight = new Circle(handleRadius, Color.RED);

        parent.getChildren().addAll(topLeft, topRight, bottomLeft, bottomRight);

        // Position handles initially
        positionHandles(imageView, topLeft, topRight, bottomLeft, bottomRight);

        // Variables to store drag offsets
        final double[] dragStartX = new double[1];
        final double[] dragStartY = new double[1];
        final double[] imageStartWidth = new double[1];
        final double[] imageStartHeight = new double[1];

        // Helper lambda for DRAGGED event (for resizing)
        // We pass a function to calculate new width and height based on mouse coords
        // For each corner, logic differs slightly.

        // --- TOP LEFT ---
        topLeft.setOnMousePressed(e -> {
            e.consume();
            dragStartX[0] = e.getSceneX();
            dragStartY[0] = e.getSceneY();
            imageStartWidth[0] = imageView.getFitWidth();
            imageStartHeight[0] = imageView.getFitHeight();
        });
        topLeft.setOnMouseDragged(e -> {
            e.consume();
            double deltaX = e.getSceneX() - dragStartX[0];
            double deltaY = e.getSceneY() - dragStartY[0];

            double newWidth = imageStartWidth[0] - deltaX;
            double newHeight = imageStartHeight[0] - deltaY;

            if (newWidth >= 20 && newHeight >= 20) {
                imageView.setFitWidth(newWidth);
                imageView.setFitHeight(newHeight);

                // Move imageView to follow top-left corner resize
                imageView.setLayoutX(imageView.getLayoutX() + deltaX);
                imageView.setLayoutY(imageView.getLayoutY() + deltaY);

                positionHandles(imageView, topLeft, topRight, bottomLeft, bottomRight);
                dragStartX[0] = e.getSceneX();
                dragStartY[0] = e.getSceneY();
                imageStartWidth[0] = newWidth;
                imageStartHeight[0] = newHeight;
            }
        });
        topLeft.setCursor(Cursor.NW_RESIZE);

        // --- TOP RIGHT ---
        topRight.setOnMousePressed(e -> {
            e.consume();
            dragStartX[0] = e.getSceneX();
            dragStartY[0] = e.getSceneY();
            imageStartWidth[0] = imageView.getFitWidth();
            imageStartHeight[0] = imageView.getFitHeight();
        });
        topRight.setOnMouseDragged(e -> {
            e.consume();
            double deltaX = e.getSceneX() - dragStartX[0];
            double deltaY = e.getSceneY() - dragStartY[0];

            double newWidth = imageStartWidth[0] + deltaX;
            double newHeight = imageStartHeight[0] - deltaY;

            if (newWidth >= 20 && newHeight >= 20) {
                imageView.setFitWidth(newWidth);
                imageView.setFitHeight(newHeight);

                // Move imageView layoutY for top edge resize
                imageView.setLayoutY(imageView.getLayoutY() + deltaY);

                positionHandles(imageView, topLeft, topRight, bottomLeft, bottomRight);
                dragStartX[0] = e.getSceneX();
                dragStartY[0] = e.getSceneY();
                imageStartWidth[0] = newWidth;
                imageStartHeight[0] = newHeight;
            }
        });
        topRight.setCursor(Cursor.NE_RESIZE);

        // --- BOTTOM LEFT ---
        bottomLeft.setOnMousePressed(e -> {
            e.consume();
            dragStartX[0] = e.getSceneX();
            dragStartY[0] = e.getSceneY();
            imageStartWidth[0] = imageView.getFitWidth();
            imageStartHeight[0] = imageView.getFitHeight();
        });
        bottomLeft.setOnMouseDragged(e -> {
            e.consume();
            double deltaX = e.getSceneX() - dragStartX[0];
            double deltaY = e.getSceneY() - dragStartY[0];

            double newWidth = imageStartWidth[0] - deltaX;
            double newHeight = imageStartHeight[0] + deltaY;

            if (newWidth >= 20 && newHeight >= 20) {
                imageView.setFitWidth(newWidth);
                imageView.setFitHeight(newHeight);

                // Move imageView layoutX for left edge resize
                imageView.setLayoutX(imageView.getLayoutX() + deltaX);

                positionHandles(imageView, topLeft, topRight, bottomLeft, bottomRight);
                dragStartX[0] = e.getSceneX();
                dragStartY[0] = e.getSceneY();
                imageStartWidth[0] = newWidth;
                imageStartHeight[0] = newHeight;
            }
        });
        bottomLeft.setCursor(Cursor.SW_RESIZE);

        // --- BOTTOM RIGHT ---
        bottomRight.setOnMousePressed(e -> {
            e.consume();
            dragStartX[0] = e.getSceneX();
            dragStartY[0] = e.getSceneY();
            imageStartWidth[0] = imageView.getFitWidth();
            imageStartHeight[0] = imageView.getFitHeight();
        });
        bottomRight.setOnMouseDragged(e -> {
            e.consume();
            double deltaX = e.getSceneX() - dragStartX[0];
            double deltaY = e.getSceneY() - dragStartY[0];

            double newWidth = imageStartWidth[0] + deltaX;
            double newHeight = imageStartHeight[0] + deltaY;

            if (newWidth >= 20 && newHeight >= 20) {
                imageView.setFitWidth(newWidth);
                imageView.setFitHeight(newHeight);

                positionHandles(imageView, topLeft, topRight, bottomLeft, bottomRight);
                dragStartX[0] = e.getSceneX();
                dragStartY[0] = e.getSceneY();
                imageStartWidth[0] = newWidth;
                imageStartHeight[0] = newHeight;
            }
        });
        // Update handles when image moves
        imageView.layoutXProperty().addListener((obs, oldVal, newVal) -> {
            positionHandles(imageView, topLeft, topRight, bottomLeft, bottomRight);
        });
        imageView.layoutYProperty().addListener((obs, oldVal, newVal) -> {
            positionHandles(imageView, topLeft, topRight, bottomLeft, bottomRight);
        });

// Also update handles when size changes
        imageView.fitWidthProperty().addListener((obs, oldVal, newVal) -> {
            positionHandles(imageView, topLeft, topRight, bottomLeft, bottomRight);
        });
        imageView.fitHeightProperty().addListener((obs, oldVal, newVal) -> {
            positionHandles(imageView, topLeft, topRight, bottomLeft, bottomRight);
        });
        bottomRight.setCursor(Cursor.SE_RESIZE);
    }



    private static void makeNodeDraggable(ImageView node) {
        final double[] dragDelta = new double[2];
        node.setOnMousePressed(e -> {
            dragDelta[0] = node.getLayoutX() - e.getSceneX();
            dragDelta[1] = node.getLayoutY() - e.getSceneY();
            e.consume();
        });
        node.setOnMouseDragged(e -> {
            node.setLayoutX(e.getSceneX() + dragDelta[0]);
            node.setLayoutY(e.getSceneY() + dragDelta[1]);
            e.consume();
        });
    }
    private static void positionHandles(ImageView imageView, Circle topLeft, Circle topRight, Circle bottomLeft, Circle bottomRight) {
        topLeft.setCenterX(imageView.getLayoutX());
        topLeft.setCenterY(imageView.getLayoutY());

        topRight.setCenterX(imageView.getLayoutX() + imageView.getFitWidth());
        topRight.setCenterY(imageView.getLayoutY());

        bottomLeft.setCenterX(imageView.getLayoutX());
        bottomLeft.setCenterY(imageView.getLayoutY() + imageView.getFitHeight());

        bottomRight.setCenterX(imageView.getLayoutX() + imageView.getFitWidth());
        bottomRight.setCenterY(imageView.getLayoutY() + imageView.getFitHeight());
    }
    private static void saveSignedPdf(File inputPdf, File outputPdf, List<StackPane> pageStacks) throws IOException {
        try (PDDocument document = PDDocument.load(inputPdf)) {
            PDFRenderer renderer = new PDFRenderer(document);

            for (int i = 0; i < document.getNumberOfPages(); i++) {
                PDPage page = document.getPage(i);

                StackPane pageStack = pageStacks.get(i);
                // Assume pageStack.getChildren().get(1) is the overlay Pane containing signature ImageViews
                Pane overlay = (Pane) pageStack.getChildren().get(1);

                try (PDPageContentStream contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true, true)) {

                    for (Node node : overlay.getChildren()) {
                        if (node instanceof ImageView) {
                            ImageView sigView = (ImageView) node;

                            // Convert JavaFX Image to BufferedImage
                            javafx.scene.image.Image fxImage = sigView.getImage();
                            BufferedImage sigBuffered = SwingFXUtils.fromFXImage(fxImage, null);

                            // Create PDImageXObject from BufferedImage
                            PDImageXObject pdImage = LosslessFactory.createFromImage(document, sigBuffered);

                            // Calculate scale and position:
                            // JavaFX overlay coords to PDF coords (PDF origin is bottom-left)
                            // PDF units are in points (1/72 inch), JavaFX in pixels
                            // You need to scale positions accordingly based on your rendering DPI and page size

                            PDRectangle mediaBox = page.getMediaBox();

                            double pageWidth = mediaBox.getWidth();
                            double pageHeight = mediaBox.getHeight();

                            // Get overlay Pane size (in JavaFX pixels)
                            double overlayWidth = overlay.getWidth();
                            double overlayHeight = overlay.getHeight();

                            // Get signature position in overlay coordinates
                            double sigX = sigView.getLayoutX();
                            double sigY = sigView.getLayoutY();

                            // Scale signature dimensions
                            double sigWidth = sigView.getBoundsInParent().getWidth();
                            double sigHeight = sigView.getBoundsInParent().getHeight();

                            // Convert JavaFX coordinates to PDF coordinates
                            // JavaFX origin is top-left, PDF origin is bottom-left
                            float x = (float) (sigX * pageWidth / overlayWidth);
                            float y = (float) (pageHeight - (sigY + sigHeight) * pageHeight / overlayHeight);

                            // Scale signature size from overlay pixels to PDF points
                            float width = (float) (sigWidth * pageWidth / overlayWidth);
                            float height = (float) (sigHeight * pageHeight / overlayHeight);

                            // Draw image on PDF page
                            contentStream.drawImage(pdImage, x, y, width, height);
                        }
                    }

                    contentStream.close();
                }
            }

            document.save(outputPdf);
        }
    }


    private static boolean isFinite(float value) {
        return !Float.isNaN(value) && !Float.isInfinite(value);
    }

}
