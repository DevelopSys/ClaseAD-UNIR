module org.example.gestorligas {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.example.gestorligas to javafx.fxml;
    exports org.example.gestorligas;
}