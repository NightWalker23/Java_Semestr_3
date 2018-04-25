package com.example.tomek.myfirstandroidapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class Edit_user extends Fragment
{
    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState )
    {
        View rootView = inflater.inflate( R.layout.edit_user, container, false );
        return rootView;
    }

    @Override
    public void onViewCreated( View view, Bundle savedInstanceState )
    {
        final EditText user_id = ( EditText ) getView().findViewById( R.id.editUserId );
        final EditText newUsername = ( EditText ) getView().findViewById( R.id.editNewUsername );
        final EditText newPassword = ( EditText ) getView().findViewById( R.id.editNewPassword );
        final TextView comunicat = ( TextView ) getView().findViewById( R.id.textUpdateError );
        final DatabaseManager db = new DatabaseManager( this.getContext() );

        Button button = ( Button ) view.findViewById( R.id.buttonUpdate );
        button.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick( View v )
            {
                int id = Integer.parseInt( user_id.getText().toString() );
                String username = newUsername.getText().toString();
                String password = newPassword.getText().toString();

                if( Validate.validate( username, password ) )
                {
                    List< User > list = db.getListOfUsers();
                    boolean isAvaible = true;
                    for( User el : list )
                    {
                        if( el.getUsername().equals( username ) )
                        {
                            if( el.getId_user() != id )
                            {
                                isAvaible = false;
                                break;
                            }
                        }
                    }

                    if( isAvaible )
                    {
                        db.updateUser( id, username, password );
                        comunicat.setVisibility( View.GONE );
                    }
                    else
                    {
                        comunicat.setText( "This username is already taken!" );
                        comunicat.setVisibility( View.VISIBLE );
                    }
                }
                else
                {
                    comunicat.setText( "Incorrect username or password!" );
                    comunicat.setVisibility( View.VISIBLE );
                }
            }
        } );
    }
}
