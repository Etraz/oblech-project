module com.example.oblechproject {
    requires javafx.controls;
    requires javafx.fxml;


    opens pl.lumido.oblechproject.gui to javafx.fxml;
    exports pl.lumido.oblechproject.gui;

    opens pl.lumido.oblechproject.gui.handCreation to javafx.fxml;
    exports pl.lumido.oblechproject.gui.handCreation;

    opens pl.lumido.oblechproject.engine.cards.hands to javafx.fxml;
    exports pl.lumido.oblechproject.engine.cards.hands;

}