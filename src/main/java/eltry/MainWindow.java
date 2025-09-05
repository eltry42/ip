package eltry;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Eltry eltry;

    private Image eltryImage = new Image(this.getClass().getResourceAsStream("/images/Trump.jpg"));
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Elon.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setEltry(Eltry j) {
        eltry = j;
        dialogContainer.getChildren().addAll(
                DialogBox.getEltryDialog("Hi! I am Eltry", eltryImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = eltry.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getEltryDialog(response, eltryImage)
        );
        if (input.equals("bye")) {
            Platform.exit();
        }
        userInput.clear();
    }
}
