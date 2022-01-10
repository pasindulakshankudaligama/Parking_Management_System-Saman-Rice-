package controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import model.Driver;
import java.util.ArrayList;

public class AddDriverFormController  {

    public static int i = 0;
    public static ObservableList driverList = FXCollections.observableArrayList();
    public TextField txtName;
    public TextField txtNIC;
    public TextField txtDrivingLicenseNo;
    public TextField txtAddress;
    public TextField txtContactNo;
    public Button btnAddDriver;
    public String name;
    public String driverNIC;
    public String licenseNo;
    public String driverAddress;
    public int driverContactNo;

    public static ObservableList getDriverList(){
        return driverList;
    }

    public void btnAddDriverOnAction(ActionEvent actionEvent) {
        name = txtName.getText();
        driverNIC = txtNIC.getText();
        licenseNo = txtDrivingLicenseNo.getText();
        driverAddress = txtAddress.getText();

        try {
            driverContactNo = Integer.parseInt(txtContactNo.getText());
        } catch (NumberFormatException e) {
            System.out.println("contact number not string");
        }
        ArrayList<Driver> driver = new ArrayList();

        Driver driver1 = new Driver(name,driverNIC,licenseNo,driverAddress,driverContactNo);
        driver.add(driver1);
        clearTextField();

        for (Driver temp:driver
             ) {
            driverList.add(temp);

            new Alert(Alert.AlertType.CONFIRMATION,
                    "Saved..", ButtonType.CLOSE).show();
        }

    }


    public void moveNIC(ActionEvent actionEvent) {
        txtNIC.requestFocus();
    }

    public void moveDrivingLicenseNo(ActionEvent actionEvent) {
        txtDrivingLicenseNo.requestFocus();
    }

    public void moveAddress(ActionEvent actionEvent) {
        txtAddress.requestFocus();
    }

    public void moveContactNo(ActionEvent actionEvent) {
        txtContactNo.requestFocus();
    }

    public void clearTextField(){
        txtName.clear();
        txtNIC.clear();
        txtDrivingLicenseNo.clear();
        txtAddress.clear();
        txtContactNo.clear();
    }
}
