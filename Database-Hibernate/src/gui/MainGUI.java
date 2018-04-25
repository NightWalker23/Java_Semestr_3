package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainGUI extends Application
{
    @Override
    public void start( Stage primaryStage ) throws Exception
    {
        Parent root = FXMLLoader.load( getClass().getResource( "/gui/views/LoginView.fxml" ) );
        primaryStage.setTitle( "Login" );
        primaryStage.setScene( new Scene( root, 350, 500 ) );
        primaryStage.show();
        primaryStage.setResizable( false );
    }

    public static void main( String[] args )
    {
        launch( args );
    }
}
