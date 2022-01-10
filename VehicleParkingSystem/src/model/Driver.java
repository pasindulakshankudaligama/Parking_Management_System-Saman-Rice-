package model;

public class Driver {
    private String txtName;
    private String txtNIC;
    private String txtDrivingLicenseNo;
    private String txtAddress;
    private int txtContactNo;

    public Driver() {
    }

    public Driver(String txtName, String txtNIC, String txtDrivingLicenseNo, String txtAddress, int txtContactNo) {
        this.setTxtName(txtName);
        this.setTxtNIC(txtNIC);
        this.setTxtDrivingLicenseNo(txtDrivingLicenseNo);
        this.setTxtAddress(txtAddress);
        this.setTxtContactNo(txtContactNo);
    }


    public String getTxtName() {
        return txtName;
    }

    public void setTxtName(String txtName) {
        this.txtName = txtName;
    }

    public String getTxtNIC() {
        return txtNIC;
    }

    public void setTxtNIC(String txtNIC) {
        this.txtNIC = txtNIC;
    }

    public String getTxtDrivingLicenseNo() {
        return txtDrivingLicenseNo;
    }

    public void setTxtDrivingLicenseNo(String txtDrivingLicenseNo) {
        this.txtDrivingLicenseNo = txtDrivingLicenseNo;
    }

    public String getTxtAddress() {
        return txtAddress;
    }

    public void setTxtAddress(String txtAddress) {
        this.txtAddress = txtAddress;
    }

    public int getTxtContactNo() {
        return txtContactNo;
    }

    public void setTxtContactNo(int txtContactNo) {
        this.txtContactNo = txtContactNo;

    }

    @Override
    public String toString() {
        return txtName;
    }
}

