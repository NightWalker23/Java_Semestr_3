package com.example.tomek.myfirstandroidapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseManager extends SQLiteOpenHelper
{
    public DatabaseManager( Context context )
    {
        super( context, "users.db", null, 1 );
    }

    @Override
    public void onCreate( SQLiteDatabase db )
    {
        db.execSQL( "CREATE TABLE users (" +
                "id_user integer primary key autoincrement," +
                "username text," +
                "password text);" + "" );
    }

    @Override
    public void onUpgrade( SQLiteDatabase db, int oldVersion, int newVersion )
    {
    }

    public void createUser( User user )
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put( "username", user.getUsername() );
        values.put( "password", user.getPassword() );
        db.insertOrThrow( "users", null, values );
    }

    public User readUser( int id )
    {
        User user = new User();
        SQLiteDatabase db = getReadableDatabase();
        String[] columns = {"id_user", "username", "password"};
        String[] arguments = {id + ""};
        Cursor cursor = db.query( "users", columns, " id_user=?", arguments, null, null, null, null );
        if( cursor != null )
        {
            cursor.moveToFirst();
            user.setId_user( cursor.getInt( 0 ) );
            user.setUsername( cursor.getString( 1 ) );
            user.setPassword( cursor.getString( 2 ) );
        }
        return user;
    }

    public void updateUser( int id, String username, String password )
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put( "username", username );
        values.put( "password", password );
        String arguments[] = {id + ""};
        db.update( "users", values, "id_user=?", arguments );
    }

    public void deleteUser( int id )
    {
        SQLiteDatabase db = getWritableDatabase();
        String[] arguments = {"" + id};
        db.delete( "users", "id_user=?", arguments );
    }

    public List< User > getListOfUsers()
    {
        List< User > list = new ArrayList<>();
        String[] columns = {"id_user", "username", "password"};
        SQLiteDatabase db = getReadableDatabase();
        final Cursor cursor = db.query( "users", columns, null, null, null, null, null );

        while( cursor.moveToNext() )
        {
            list.add( new User()
            {{
                setId_user( cursor.getInt( 0 ) );
                setUsername( cursor.getString( 1 ) );
                setPassword( cursor.getString( 2 ) );
            }} );
        }
        return list;
    }
}
