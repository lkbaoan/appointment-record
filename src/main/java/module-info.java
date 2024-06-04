module com.example.appointmentrecord {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.example.appointmentrecord to javafx.fxml;
    exports com.example.appointmentrecord;
}