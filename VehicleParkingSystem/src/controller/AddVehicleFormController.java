package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import model.VehicleData;
import java.util.ArrayList;

public class AddVehicleFormController {
    public TextField maxWeightContext;
    public TextField noOfPassengersContext;
    public ComboBox vehicleTypeContext;
    public TextField addVehicleNumContext;
    public Button btnAddVehicleContext;

    public static ObservableList vehicleList = FXCollections.observableArrayList();

    public String number;
    public String type;
    public int weight;
    public int noPassenger;

    public static ObservableList getVehicleList(){
        return vehicleList;
    }

    public void initialize(){

        vehicleTypeContext.getItems().addAll(
                "Van","Cargo Lorry","Bus"
        );
    }

    public void moveMaxWeight(ActionEvent actionEvent) {
        maxWeightContext.requestFocus();
    }

    public void moveNoOfPassengers(ActionEvent actionEvent) {
        noOfPassengersContext.requestFocus();
    }

    public void btnAddVehicleOnAction(ActionEvent actionEvent) {
        number = addVehicleNumContext.getText();
        type =(String) vehicleTypeContext.getValue();
        weight = Integer.parseInt(maxWeightContext.getText());
        noPassenger = Integer.parseInt(noOfPassengersContext.getText());

        ArrayList<VehicleData> dataArrayList  = new ArrayList();

        VehicleData vehicleData = new VehicleData(number,type,weight,noPassenger);
        dataArrayList.add(vehicleData);
        clearTextField();
        for (VehicleData temp : dataArrayList) {
            vehicleList.add(temp);

            new Alert(Alert.AlertType.CONFIRMATION,
                    "Saved..", ButtonType.CLOSE).show();
        }

    }

    public void clearTextField(){
        addVehicleNumContext.clear();
        maxWeightContext.clear();
        noOfPassengersContext.clear();
        vehicleTypeContext.setValue(" ");

    }

}

