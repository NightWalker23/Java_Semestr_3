package crud;

import database.Users;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UsersCRUD
{
    public void addUser( Users user )
    {
        Transaction trns = null;
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        try
        {
            trns = session.beginTransaction();
            session.save( user );
            session.getTransaction().commit();
        }
        catch ( RuntimeException e )
        {
            if ( trns != null )            
                trns.rollback();
            
            e.printStackTrace();
        }
        finally
        {
            session.flush();
            session.close();
        }
    }

    public void deleteUser( int userid )
    {
        Transaction trns = null;
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        try
        {
            trns = session.beginTransaction();
            Users user = ( Users ) session.load( Users.class, new Integer( userid ) );
            session.delete( user );
            session.getTransaction().commit();
        }
        catch ( RuntimeException e )
        {
            if ( trns != null )            
                trns.rollback();
            
            e.printStackTrace();
        }
        finally
        {
            session.flush();
            session.close();
        }
    }

    public void updateUser( Users user )
    {
        Transaction trns = null;
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        try
        {
            trns = session.beginTransaction();
            session.update( user );
            session.getTransaction().commit();
        }
        catch ( RuntimeException e )
        {
            if ( trns != null )            
                trns.rollback();
            
            e.printStackTrace();
        }
        finally
        {
            session.flush();
            session.close();
        }
    }

    public List<Users> getAllUsers()
    {
        List<Users> users = new ArrayList<Users>();
        Transaction trns = null;
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        try
        {
            trns = session.beginTransaction();
            users = session.createQuery( "from Users" ).list();
        }
        catch ( RuntimeException e ){e.printStackTrace();}
        finally
        {
            session.flush();
            session.close();
        }
        return users;
    }

    public Users getUserById( int userid )
    {
        Users user = null;
        Transaction trns = null;
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        try
        {
            trns = session.beginTransaction();
            String queryString = "from Users where idUser = :id";
            Query query = session.createQuery( queryString );
            query.setInteger( "id", userid );
            user = ( Users ) query.uniqueResult();
        }
        catch ( RuntimeException e ){e.printStackTrace();}
        finally
        {
            session.flush();
            session.close();
        }
        return user;
    }
}
