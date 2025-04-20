module com.fx.loginmodule {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.fx.loginmodule to javafx.fxml;
    exports com.fx.loginmodule;
}