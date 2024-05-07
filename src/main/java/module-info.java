module com.empresa.actividad014 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;

    opens com.empresa.actividad014 to javafx.fxml;
    exports com.empresa.actividad014;
}