package crud.Validate;

import database.Users;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidate
{
    public static boolean validateUser( Users user )
    {
        if ( validateName( user.getName() ) && validatePassword( user.getPassword() ) )
            return true;
        else
            return false;        
    }

    public static boolean validateName( String name )
    {
        Pattern pattern = Pattern.compile( "^[a-zA-Z0-9_-]{4,20}$" );
        Matcher matcher = pattern.matcher( name );
        return matcher.find();
    }

    public static boolean validatePassword( String password )
    {
        Pattern pattern = Pattern.compile( "^[a-zA-Z0-9_-]{4,20}$" );
        Matcher matcher = pattern.matcher( password );
        return matcher.find();
    }
}
