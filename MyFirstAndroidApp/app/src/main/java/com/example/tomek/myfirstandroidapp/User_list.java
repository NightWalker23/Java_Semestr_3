package com.example.tomek.myfirstandroidapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class User_list extends Fragment
{
    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState )
    {
        View rootView = inflater.inflate( R.layout.user_list, container, false );
        return rootView;
    }

    @Override
    public void onViewCreated( View view, Bundle savedInstanceState )
    {
        TextView userList = ( TextView ) getView().findViewById( R.id.textUserList );
        DatabaseManager dbManager = new DatabaseManager( this.getContext() );
        List< User > list = dbManager.getListOfUsers();
        for( User el : list )
            userList.append( el.getId_user() + " " + el.getUsername() + " " + el.getPassword() + "\n" );
    }
}