package controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class ParkingSystemFormController {
    public AnchorPane parkingSystemContext;
    public ComboBox<String> cmbSelectVehicle;
    public ComboBox<String> cmbDriver;
    public Label lblOnDate;
    public Label lblOnTime;
    public Label lblVehicleTypeContext;
    public Label parkingSlotNumConText;
    public Button btnParkVehicleContext;
    public Button btnOnDeliveryContext;

    public static String noOfVehicle;
    public static String typeOfVehicle;
    public String slotNo;
    public String dateTime;
    public String name;

    private ObservableList parentDriverList = FXCollections.observableArrayList();

    private ObservableList parentVehicleList = FXCollections.observableArrayList();

    String[][] vehicle = {{"NA-3434", "Bus"}, {"KA-4567", "Van"}, {"58-3567", "Van"}, {"KA-4563", "Van"}, {"GF-4358", "Van"}, {"CCB-3568", "Van"}, {"LM-6679", "Van"}, {"QA-3369", "Van"}, {"KB-3668", "Cargo Lorry"}, {"JJ-9878", "Cargo Lorry"}, {"GH-5772", "Cargo Lorry"}, {"XY-3536", "Cargo Lorry"}, {"YQ-3536", "Cargo Lorry"}, {"CBB-3566", "Cargo Lorry"}, {"QH-3444", "Cargo Lorry"}};

    static Van v1 = new Van();
    static Bus b1 = new Bus();
    static CargoLorry c1 = new CargoLorry();

    public void openLogInFormOnAction(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/LogInForm.fxml"));
        Parent parent = loader.load();
        LogInFormController controller = (LogInFormController) loader.getController();
        Stage stage = new Stage();
        controller.getStage((Stage) parkingSystemContext.getScene().getWindow());
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.setTitle("Vehicle Parking System(v-1.0.0)");
        stage.show();
    }

    public void parkVehicleOnAction(ActionEvent actionEvent) throws InterruptedException, IOException {
        if (typeOfVehicle.equals("Van")) {
            v1.park(noOfVehicle, typeOfVehicle);
        } else if (typeOfVehicle.equals("Bus")) {
            b1.park(noOfVehicle, typeOfVehicle);

        } else if (typeOfVehicle.equals("Cargo Lorry")) {
            c1.park(noOfVehicle, typeOfVehicle);
        }

        slotNo = parkingSlotNumConText.getText();
        dateTime = getDateAndTime();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/InParkingForm.fxml"));
        Parent parent = loader.load();
        InParkingFormController controller = loader.getController();
        controller.setData(noOfVehicle,typeOfVehicle,slotNo,dateTime);
        controller.trigger();


    }

    public void onDeliveryShiftOnAction(ActionEvent actionEvent) throws InterruptedException, IOException {

        v1.leavePark(noOfVehicle, typeOfVehicle);
        b1.leavePark(noOfVehicle, typeOfVehicle);
        c1.leavePark(noOfVehicle,typeOfVehicle);

        name = String.valueOf(cmbDriver.getValue());

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/InParkingForm.fxml"));
        Parent parent = loader.load();
        InParkingFormController controller= loader.getController();
        controller.setDataDelivery(noOfVehicle,typeOfVehicle,name,dateTime);
        controller.triggerDelivery();


    }

    public void initialize() throws InterruptedException {

        cmbAddValues();
        OnDate();
        OnTime();

        cmbDriver.setItems(parentDriverList);
        Bindings.bindContentBidirectional(parentDriverList, AddDriverFormController.getDriverList());

        cmbSelectVehicle.setItems(parentVehicleList);
        Bindings.bindContentBidirectional(parentVehicleList, AddVehicleFormController.getVehicleList());

    }

    public String getDateAndTime(){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date date = new Date();
        return formatter.format(date);

    }

    public void OnTime() throws InterruptedException {

        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm a");
            lblOnTime.setText(LocalDateTime.now().format(formatter));
        }),
                new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();

    }

    public void OnDate() throws InterruptedException {

        SimpleDateFormat DateFormatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        lblOnDate.setText(DateFormatter.format(date));
        Thread.sleep(10);

    }

    public void cmbVehicleOnAction(ActionEvent actionEvent) {
       for (int i = 0; i < vehicle.length; i++) {
            for (int j = 0; j < vehicle[1].length; j++) {
                if (vehicle[i][0].equals(String.valueOf(cmbSelectVehicle.getValue()))) {
                    lblVehicleTypeContext.setText(vehicle[i][1]);
                    break;
                }
                noOfVehicle = String.valueOf(cmbSelectVehicle.getValue());
                typeOfVehicle = lblVehicleTypeContext.getText();
                setParkingNo();
                break;
            }
        }
    }

    public void cmbAddValues(){
        for (int i =0; i< vehicle.length; i++){
            cmbSelectVehicle.getItems().addAll(vehicle[i][0]);
        }
    }

    private void setParkingNo() {

        if (typeOfVehicle.equals("Van")) {
            parkingSlotNumConText.setText(v1.setSlot(noOfVehicle, typeOfVehicle));


            if (v1.setSlot(noOfVehicle, typeOfVehicle).equals("Parked")) {
                btnParkVehicleContext.setDisable(true);
                btnOnDeliveryContext.setDisable(false);

            } else {
                btnOnDeliveryContext.setDisable(true);
                btnParkVehicleContext.setDisable(false);

            }

        } else if (typeOfVehicle.equals("Bus")) {
            parkingSlotNumConText.setText(b1.setSlot(noOfVehicle, typeOfVehicle));


            if (b1.setSlot(noOfVehicle, typeOfVehicle).equals("Parked")) {
                btnParkVehicleContext.setDisable(true);
                btnOnDeliveryContext.setDisable(false);

            } else {
                btnOnDeliveryContext.setDisable(true);
                btnParkVehicleContext.setDisable(false);

            }

        } else if (typeOfVehicle.equals("Cargo Lorry")) {
            parkingSlotNumConText.setText(c1.setSlot(noOfVehicle, typeOfVehicle));


            if (c1.setSlot(noOfVehicle, typeOfVehicle).equals("Parked")) {
                btnParkVehicleContext.setDisable(true);
                btnOnDeliveryContext.setDisable(false);

            } else {
                btnOnDeliveryContext.setDisable(true);
                btnParkVehicleContext.setDisable(false);

            }

        }

    }

    private void loadAllVehicles() {


    }

}