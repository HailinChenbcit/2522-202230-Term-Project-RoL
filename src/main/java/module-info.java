module ca.bcit.comp2522.termproject.rol {
    requires javafx.controls;
    requires javafx.fxml;


    opens ca.bcit.comp2522.termproject.rol to javafx.fxml;
    exports ca.bcit.comp2522.termproject.rol;
}