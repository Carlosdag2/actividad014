package com.empresa.actividad014;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HelloApplication extends Application {

    private TextField emailField;
    private PasswordField passwordField;
    private PasswordField confirmPasswordField;
    private DatePicker datePicker;
    private CheckBox privacyCheckBox;

    private String adminEmail = "admin@gmail.com";
    private String adminPassword = "123";

    private Stage primaryStage;
    private Scene loginScene;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("User Authentication App");

        // Registro de Usuario
        GridPane registrationGrid = new GridPane();
        registrationGrid.setPadding(new Insets(10));
        registrationGrid.setVgap(8);
        registrationGrid.setHgap(10);

        Label emailLabel = new Label("Correo:");
        GridPane.setConstraints(emailLabel, 0, 0);
        emailField = new TextField();
        GridPane.setConstraints(emailField, 1, 0);

        Label passwordLabel = new Label("Contraseña:");
        GridPane.setConstraints(passwordLabel, 0, 1);
        passwordField = new PasswordField();
        GridPane.setConstraints(passwordField, 1, 1);

        Label confirmPasswordLabel = new Label("Confirmar Contraseña:");
        GridPane.setConstraints(confirmPasswordLabel, 0, 2);
        confirmPasswordField = new PasswordField();
        GridPane.setConstraints(confirmPasswordField, 1, 2);

        Label dateLabel = new Label("Fecha de Alta:");
        GridPane.setConstraints(dateLabel, 0, 3);
        datePicker = new DatePicker();
        GridPane.setConstraints(datePicker, 1, 3);

        privacyCheckBox = new CheckBox("Aceptar privacidad");
        GridPane.setConstraints(privacyCheckBox, 1, 4);

        Button registerButton = new Button("Registrar");
        registerButton.setOnAction(e -> registerUser());
        GridPane.setConstraints(registerButton, 1, 5);

        registrationGrid.getChildren().addAll(emailLabel, emailField, passwordLabel, passwordField,
                confirmPasswordLabel, confirmPasswordField, dateLabel, datePicker, privacyCheckBox, registerButton);

        Scene registrationScene = new Scene(registrationGrid, 300, 200);

        // Inicio de Sesión
        VBox loginVBox = new VBox(10);
        loginVBox.setPadding(new Insets(10));
        Label loginLabel = new Label("Inicio de Sesión");
        TextField loginEmailField = new TextField();
        loginEmailField.setPromptText("Correo");
        PasswordField loginPasswordField = new PasswordField();
        loginPasswordField.setPromptText("Contraseña");
        Button loginButton = new Button("Iniciar Sesión");
        loginButton.setOnAction(e -> {
            if (loginEmailField.getText().equals(adminEmail) && loginPasswordField.getText().equals(adminPassword)) {
                showAlert("Inicio de sesión correcto");
            } else {
                showAlert("Inicio de sesión incorrecto");
            }
        });

        Button goToRegisterButton = new Button("Registrarse");
        goToRegisterButton.setOnAction(e -> primaryStage.setScene(registrationScene));
        loginVBox.getChildren().addAll(loginLabel, loginEmailField, loginPasswordField, loginButton, goToRegisterButton);

        loginScene = new Scene(loginVBox, 250, 200);

        primaryStage.setScene(loginScene);
        primaryStage.show();
    }

    private void registerUser() {
        String email = emailField.getText();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();
        String date = datePicker.getValue().toString();
        boolean privacyAccepted = privacyCheckBox.isSelected();

        if (!email.isEmpty() && !password.isEmpty() && !confirmPassword.isEmpty() && !date.isEmpty() && privacyAccepted) {
            if (password.equals(confirmPassword)) {
                showAlert("Usuario registrado");
                // Cambiar a la ventana de inicio de sesión
                primaryStage.setScene(loginScene);
            } else {
                showAlert("Las contraseñas no coinciden");
            }
        } else {
            showAlert("Por favor, completa todos los campos y acepta la privacidad.");
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

