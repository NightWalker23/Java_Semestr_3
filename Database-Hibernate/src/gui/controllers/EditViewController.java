package gui.controllers;

import crud.UsersCRUD;
import crud.Validate.UserValidate;
import database.Users;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;

public class EditViewController
{
    private MainViewController main;
    @FXML TextField newUsername;
    @FXML PasswordField newPassword;
    @FXML Label editComunicat;
    private Users user;

    void init( MainViewController main, Users user )
    {
        this.main = main;
        this.user = user;
    }

    @FXML
    public void edit()
    {
        String username = newUsername.getText();
        String password = newPassword.getText();
        if ( UserValidate.validateName( username ) && UserValidate.validatePassword( password ) )
        {
            user.setName( username );
            user.setPassword( password );
            UsersCRUD uc = new UsersCRUD();
            uc.updateUser( user );
            main.updateList();
            newUsername.clear();
            newPassword.clear();
            editComunicat.setTextFill( Paint.valueOf( "#00d815" ) );
            editComunicat.setText( "Data edited correctly!" );
        } else
        {
            editComunicat.setTextFill( Paint.valueOf( "#da0000" ) );
            editComunicat.setText( "Invalid username or password!" );
        }
    }
}
