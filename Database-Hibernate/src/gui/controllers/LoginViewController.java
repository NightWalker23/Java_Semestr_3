package gui.controllers;

import crud.LoginHistoryCRUD;
import crud.UsersCRUD;
import crud.Validate.UserValidate;
import database.LoginHistory;
import database.Users;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

public class LoginViewController
{
    @FXML TextField loginUsername, registerUsername;
    @FXML PasswordField loginPassword, registerPassword;
    @FXML Label loginComunicat, registerComunicat;
    @FXML SplitMenuButton registerRole;

    @FXML
    public void login( ActionEvent event )
    {
        String username = loginUsername.getText();
        String password = loginPassword.getText();
        Users tmpuser = new Users( username, password, 2 );
        if ( UserValidate.validateUser( tmpuser ) )
        {
            UsersCRUD uc = new UsersCRUD();
            List<Users> usersList = uc.getAllUsers();
            boolean isCorrect = false;
            for ( Users el : usersList )
            {
                if ( el.getName().equals( tmpuser.getName() ) )
                {
                    if ( el.getPassword().equals( password ) )
                    {
                        LoginHistoryCRUD lhc = new LoginHistoryCRUD();
                        lhc.addLog( new LoginHistory( el.getIdUser(), new Date() ) );
                        isCorrect = true;
                        loginComunicat.setText( "" );
                        
                        ( ( Node ) event.getSource() ).getScene().getWindow().hide();
                        FXMLLoader fxmlLoader = new FXMLLoader( getClass().getResource( "/gui/views/MainView.fxml" ) );
                        Parent parent = null;
                        try{parent = fxmlLoader.load();}
                        catch ( IOException e ){e.printStackTrace();}
                        MainViewController controller = fxmlLoader.getController();
                        controller.init( el );
                        Stage stage = new Stage();
                        Scene scene = new Scene( parent );
                        stage.setScene( scene );
                        stage.setTitle( "Java - Baza Danych" );
                        stage.show();
                    }
                }
            }
            if ( !isCorrect )
            {
                loginComunicat.setTextFill( Paint.valueOf( "#da0000" ) );
                loginComunicat.setText( "Invalid username or password!" );
            }
        } else
        {
            loginComunicat.setTextFill( Paint.valueOf( "#da0000" ) );
            loginComunicat.setText( "Invalid username or password!" );
        }
        //NewHibernateUtil.getSessionFactory().close();
    }

    @FXML
    public void register()
    {
        String username = registerUsername.getText();
        String password = registerPassword.getText();
        String role = registerRole.getText();
        int id_role;
        if ( role == "ADMIN" )
            id_role = 1;
        else
            id_role = 2;
        
        Users user = new Users( username, password, id_role );
        if ( UserValidate.validateUser( user ) )
        {
            UsersCRUD uc = new UsersCRUD();
            List<Users> usersList = uc.getAllUsers();
            boolean isUsed = false;
            for ( Users el : usersList )
                if ( el.getName().equals( user.getName() ) )
                    isUsed = true;
                            
            if ( !isUsed )
            {
                uc.addUser( user );
                registerComunicat.setTextFill( Paint.valueOf( "#00d815" ) );
                registerComunicat.setText( "New account has been added!" );
            } else
            {
                registerComunicat.setTextFill( Paint.valueOf( "#da0000" ) );
                registerComunicat.setText( "This username is already in use!" );
            }
        } else
        {
            registerComunicat.setTextFill( Paint.valueOf( "#da0000" ) );
            registerComunicat.setText( "Invalid username or password!" );
        }
    }

    @FXML
    public void adminClicked()
    {
        registerRole.setText( "ADMIN" );
    }

    @FXML
    public void userClicked()
    {
        registerRole.setText( "USER" );
    }
}
