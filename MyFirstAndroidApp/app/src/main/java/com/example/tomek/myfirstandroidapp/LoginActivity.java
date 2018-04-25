package com.example.tomek.myfirstandroidapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class LoginActivity extends AppCompatActivity
{
    DatabaseManager dbManager;
    EditText editUsername;
    EditText editPassword;
    TextView error;

    @Override
    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_login );

        dbManager = new DatabaseManager( this );
        editUsername = ( EditText ) findViewById( R.id.fieldUsername );
        editPassword = ( EditText ) findViewById( R.id.filedPassword );
        error = ( TextView ) findViewById( R.id.textError );
    }

    public void click_login( View view )
    {
        String username = editUsername.getText().toString();
        String password = editPassword.getText().toString();
        List< User > userList = dbManager.getListOfUsers();
        boolean match = false;

        if( Validate.validate( username, password ) && !userList.isEmpty() )
        {
            for( User el : userList )
            {
                if( el.getUsername().equals( username ) && el.getPassword().equals( password ) )
                {
                    match = true;
                    Intent intent = new Intent( getApplicationContext(), MainActivity.class );
                    startActivity( intent );
                    finish();
                }
            }

            if( !match )
            {
                error.setText( "Incorrect username or password!" );
                error.setVisibility( View.VISIBLE );
            }
        } else
        {
            error.setText( "Incorrect username or password!" );
            error.setVisibility( View.VISIBLE );
        }
    }

    public void click_register( View view )
    {
        String username = editUsername.getText().toString();
        String password = editPassword.getText().toString();

        if( Validate.validate( username, password ) )
        {
            List< User > list = dbManager.getListOfUsers();
            boolean isAvaible = true;
            for( User el : list )
            {
                if( el.getUsername().equals( username ) )
                {
                    isAvaible = false;
                    break;
                }
            }

            if( isAvaible )
            {
                User user = new User( username, password );
                dbManager.createUser( user );
                error.setVisibility( View.GONE );
            }else
            {
                error.setText( "This username is already taken!" );
                error.setVisibility( View.VISIBLE );
            }
        }else
        {
            error.setText( "Incorrect username or password!" );
            error.setVisibility( View.VISIBLE );
        }

    }
}
