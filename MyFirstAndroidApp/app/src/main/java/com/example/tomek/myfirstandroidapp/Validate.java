package com.example.tomek.myfirstandroidapp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validate
{
    public static boolean validate( String username, String password )
    {
        Pattern p = Pattern.compile( "^[a-zA-Z0-9_-]{4,20}$" );
        Matcher matcher_u = p.matcher( username );
        Matcher matcher_p = p.matcher( password );

        return ( matcher_u.matches() && matcher_p.matches() );
    }
}
