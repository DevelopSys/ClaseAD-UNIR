module org.example.gestorligas {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;
    requires org.hibernate.orm.core;
    requires jakarta.persistence;
    requires java.naming;
    requires static lombok;

    opens org.example.gestorligas to javafx.fxml,mysql.connector.j, jakarta.persistence, org.hibernate.orm.core;
    exports org.example.gestorligas;

    opens org.example.gestorligas.model to jakarta.persistence, org.hibernate.orm.core;
    exports org.example.gestorligas.model;

    opens org.example.gestorligas.controller to javafx.fxml, mysql.connector.j, jakarta.persistence, org.hibernate.orm.core;
    exports org.example.gestorligas.controller;

    exports org.example.gestorligas.database;
    opens org.example.gestorligas.database to jakarta.persistence, javafx.fxml, mysql.connector.j, org.hibernate.orm.core;

    exports org.example.gestorligas.dao;
    opens org.example.gestorligas.dao to jakarta.persistence, javafx.fxml, mysql.connector.j, org.hibernate.orm.core;
}