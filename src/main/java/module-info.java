module com.mycompany.journeytounemployment {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires java.desktop;

    opens com.mycompany.journeytounemployment to javafx.fxml;
    exports com.mycompany.journeytounemployment;
}
