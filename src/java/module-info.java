module example.proyectofinal {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.naming;
    requires com.google.gson;

    opens example.proyectofinal to javafx.fxml;
    exports example.proyectofinal;
    exports example.proyectofinal.IA;
    opens example.proyectofinal.IA to javafx.fxml;
}