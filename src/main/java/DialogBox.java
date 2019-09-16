import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private final Circle clip = new Circle(49, 40, 40);

    // Colour palette
    // https://coolors.co/424b54-93a8ac-ffffff-e2b4bd-9b6a6c

    /**
     * Constructor of DialogBox class.
     * @param text Text of DialogBox
     * @param img Image of DialogBox
     */
    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        dialog.setMinHeight(Label.USE_PREF_SIZE);

        displayPicture.setImage(img);
        displayPicture.setClip(clip);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Gets the User DialogBox.
     * @param text Text of DialogBox
     * @param img Image of DialogBox
     * @return DialogBox object of the User
     */
    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.setSpacing(5);
        db.setStyle("-fx-padding: 5;");
        db.dialog.setStyle("-fx-background-color: #e2b4bd;"
                + "-fx-background-radius: 10;"
                + "-fx-padding: 10;"
                + "-fx-text-fill: #424b54;");
        return db;
    }

    /**
     * Gets the Duke DialogBox.
     * @param text Text of DialogBox
     * @param img Image of DialogBox
     * @return DialogBox object of Duke
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        db.setSpacing(5);
        db.setStyle("-fx-padding: 5;");
        db.dialog.setStyle("-fx-background-color: #ffffff;"
                + "-fx-background-radius: 10;"
                + "-fx-padding: 10;"
                + "-fx-text-fill: #9b6a6c;");
        return db;
    }
}