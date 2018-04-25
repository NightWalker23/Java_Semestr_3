package crud;

import database.Roles;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class RolesCRUD
{
    public void addRole( Roles role )
    {
        Transaction trns = null;
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        try
        {
            trns = session.beginTransaction();
            session.save( role );
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

    public void deleteRole( int roleid )
    {
        Transaction trns = null;
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        try
        {
            trns = session.beginTransaction();
            Roles role = ( Roles ) session.load( Roles.class, new Integer( roleid ) );
            session.delete( role );
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

    public void updateRole( Roles role )
    {
        Transaction trns = null;
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        try
        {
            trns = session.beginTransaction();
            session.update( role );
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

    public List<Roles> getAllRoles()
    {
        List<Roles> role = new ArrayList<Roles>();
        Transaction trns = null;
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        try
        {
            trns = session.beginTransaction();
            role = session.createQuery( "from Roles" ).list();
        }
        catch ( RuntimeException e ){e.printStackTrace();}
        finally
        {
            session.flush();
            session.close();
        }
        return role;
    }

    public Roles getRoleById( int roleid )
    {
        Roles role = null;
        Transaction trns = null;
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        try
        {
            trns = session.beginTransaction();
            String queryString = "from Roles where idRole = :id";
            Query query = session.createQuery( queryString );
            query.setInteger( "id", roleid );
            role = ( Roles ) query.uniqueResult();
        }
        catch ( RuntimeException e ){e.printStackTrace();}
        finally
        {
            session.flush();
            session.close();
        }
        return role;
    }
}
