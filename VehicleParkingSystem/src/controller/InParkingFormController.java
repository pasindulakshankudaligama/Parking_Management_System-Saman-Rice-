package controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import view.tm.InParkingTM;
import view.tm.OnDeliveryTM;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class InParkingFormController {

    public ComboBox cmbInParkingAndOnDelivery;
    public AnchorPane tblOneContext;
    public AnchorPane inParkingFormContext;
    public Button btnLogOut;
    public TableColumn parkingVNContext;
    public TableColumn parkingVTContext;
    public TableColumn ParkingSlotContext;
    public TableColumn parkedTimeContext;
    public TableColumn onDeliveryPVContext;
    public TableColumn onDeliveryVTContext;
    public TableColumn OnDeliveryDriverNameContext;
    public TableColumn OnDeliveryParkedTimeContext;
    public TableView<InParkingTM> tblInParking;
    public TableView<OnDeliveryTM> tblOnDelivery;
    private String no;
    private String type;
    private String dateTime;
    private String slotNo;
    private String name;

    public void  clearWindow(Stage stage)  {
        stage.close();
    }

    static ArrayList<InParkingTM> tmArrayList = new ArrayList<>();
    static ArrayList<OnDeliveryTM>  tmArrayListDelivery = new ArrayList<>();

    public void initialize(){

        cmbInParkingAndOnDelivery.getItems().addAll(
                "In Parking","On Delivery"
        );
        cmbInParkingAndOnDelivery.setValue("In Parking");

        tblInParking.setItems(FXCollections.observableArrayList(tmArrayList));

        parkingVNContext.setCellValueFactory(new PropertyValueFactory<>("vehicleNumber"));
        parkingVTContext.setCellValueFactory(new PropertyValueFactory<>("vehicleType"));
        ParkingSlotContext.setCellValueFactory(new PropertyValueFactory<>("parkingSlotNo"));
        parkedTimeContext.setCellValueFactory(new PropertyValueFactory<>("parkingTime"));

        tblOnDelivery.setItems(FXCollections.observableArrayList(tmArrayListDelivery));

        onDeliveryPVContext.setCellValueFactory(new PropertyValueFactory<>("vehicleNumber"));
        onDeliveryVTContext.setCellValueFactory(new PropertyValueFactory<>("vehicleType"));
        OnDeliveryDriverNameContext.setCellValueFactory(new PropertyValueFactory<>("driverName"));
        OnDeliveryParkedTimeContext.setCellValueFactory(new PropertyValueFactory<>("leftTime"));

    }

    public void openAddDriverOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/AddDriverForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Scene scene = new Scene(load);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Vehicle Parking System(v-1.0.0)");
        stage.show();
    }

    public void openAddVehicleOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/AddVehicleForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Scene scene = new Scene(load);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Vehicle Parking System(v-1.0.0)");
        stage.show();
    }

    public void backToParkingSystemOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/ParkingSystemForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) inParkingFormContext.getScene().getWindow();
        window.setScene(new Scene(load));
    }

    public void cmbOnAction(ActionEvent actionEvent) {
        if (cmbInParkingAndOnDelivery.getValue().equals("On Delivery")) {
            btnLogOut.setVisible(false);
            tblInParking.setVisible(false);
            tblOnDelivery.setVisible(true);

        } else {
            btnLogOut.setVisible(true);
            tblInParking.setVisible(true);
            tblOnDelivery.setVisible(false);
        }
    }

    public void setData(String no, String type, String slotNo, String dateTime){
        this.no=no;
        this.type=type;
        this.slotNo=slotNo;
        this.dateTime=dateTime;
    }

    public void setDataDelivery(String no, String type, String name, String dateTime){
        this.no=no;
        this.type=type;
        this.name=name;
        this.dateTime=dateTime;
    }

    public void trigger(){
        tmArrayList.add(new InParkingTM(no,type,slotNo,dateTime)); }

    public void triggerDelivery(){
        tmArrayListDelivery.add(new OnDeliveryTM(no,type,name,dateTime)); }

}
