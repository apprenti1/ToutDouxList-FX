module com.toutdouxlistfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.apache.commons.lang3;
    requires jakarta.mail;


    opens model.todolist to javafx.base;
    opens application to javafx.fxml;
    exports application;
    opens graphicController to javafx.fxml;
    exports graphicController;
}