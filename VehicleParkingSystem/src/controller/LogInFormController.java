package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;

public class LogInFormController {

    public AnchorPane logInFormContext;
    public Button cancelBtnContext;
    public TextField userNameContext;
    public PasswordField passwordContext;
    public Label attemptsLbl;

    public void openInParkingFormOnAction(ActionEvent actionEvent) throws IOException {
        if (userNameContext.getText().equals("user") & passwordContext.getText().equals("1234")){

            URL resource = getClass().getResource("../view/InParkingForm.fxml");
            Parent load = FXMLLoader.load(resource);
            Stage window   = (Stage) logInFormContext.getScene().getWindow();
            window.setScene(new Scene(load));
            closeWindow();

        }else{
            attemptsLbl.setVisible(true);
        }

    }

    private Stage stage=null;

    void getStage(Stage stage){
        this.stage=stage;
    }

    public void closeWindow() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/InParkingForm.fxml"));
        Parent parent = loader.load();
        InParkingFormController controller =(InParkingFormController) loader.getController();
        controller.clearWindow(stage);
    }

    public void backToHomeOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) cancelBtnContext.getScene().getWindow();
        stage.close();
    }

    public void movePassword(ActionEvent actionEvent) {
        passwordContext.requestFocus();
    }
}
