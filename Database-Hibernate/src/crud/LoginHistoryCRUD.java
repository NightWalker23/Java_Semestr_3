package crud;

import database.LoginHistory;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class LoginHistoryCRUD
{
    public void addLog( LoginHistory lh )
    {
        Transaction trns = null;
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        try
        {
            trns = session.beginTransaction();
            session.save( lh );
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

    public void deleteLoginHistory( int lhid )
    {
        Transaction trns = null;
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        try
        {
            trns = session.beginTransaction();
            LoginHistory lh = ( LoginHistory ) session.load( LoginHistory.class, new Integer( lhid ) );
            session.delete( lh );
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

    public void updateLoginHistory( LoginHistory lh )
    {
        Transaction trns = null;
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        try
        {
            trns = session.beginTransaction();
            session.update( lh );
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

    public List<LoginHistory> getAlllogs()
    {
        List<LoginHistory> lh = new ArrayList<LoginHistory>();
        Transaction trns = null;
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        try
        {
            trns = session.beginTransaction();
            lh = session.createQuery( "from LoginHistory" ).list();
        }
        catch ( RuntimeException e ){e.printStackTrace();}
        finally
        {
            session.flush();
            session.close();
        }
        return lh;
    }

    public LoginHistory getLogById( int lhid )
    {
        LoginHistory lh = null;
        Transaction trns = null;
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        try
        {
            trns = session.beginTransaction();
            String queryString = "from LoginHistory where idLog = :id";
            Query query = session.createQuery( queryString );
            query.setInteger( "id", lhid );
            lh = ( LoginHistory ) query.uniqueResult();
        }
        catch ( RuntimeException e ){e.printStackTrace();}
        finally
        {
            session.flush();
            session.close();
        }
        return lh;
    }
}
