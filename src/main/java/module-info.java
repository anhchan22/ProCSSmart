module org.example.procssmart {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.desktop;

    opens org.example.procssmart to javafx.fxml;
    exports org.example.procssmart;
}